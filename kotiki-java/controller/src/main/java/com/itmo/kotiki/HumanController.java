package com.itmo.kotiki;


import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.repo.CatsRepository;
import com.itmo.kotiki.repo.HumanRepository;
import com.itmo.kotiki.repo.UserRepository;
import com.itmo.kotiki.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Set;

@RestController
public class HumanController {

    private HumanService humanService;
    private UserService userService;
    private CatsService catsService;

    @Autowired
    public HumanController(HumanRepository humanRepository, UserRepository userRepository, CatsRepository catsRepository) {
        this.humanService = new HumanServiceImpl(humanRepository);
        this.userService = new UserServiceImpl(userRepository);
        this.catsService = new CatsServiceImpl(catsRepository);
    }

    @Secured(value = "ROLE_ADMIN")
    @PostMapping(value = "/saveHuman", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveNewHuman(@RequestParam String name, Long birthday) {
        HumansEntity human = new HumansEntity(name, new Date(birthday));
        humanService.save(human);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/humanName")
    public String getHumanName(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        if (user.getRole() != "ROLE_ADMIN")
            return String.format(user.getHumansByHumanId().getName());
        return String.format(humanService.getName(id));

    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/humanBirthday")
    public String getHumanBirthday(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        if (user.getRole() != "ROLE_ADMIN")
            return String.format(user.getHumansByHumanId().getBirthday().toString());
        return String.format(humanService.getBirthday(id).toString());
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/myCats")
    public Set<CatsEntity> getMyCats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUser(authentication.getName()).getHumansByHumanId().getCatsByHumanId();
    }

    @Secured("ROLE_USER")
    @GetMapping("/getNameOfMyCat")
    public String getCatName(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format(catsService.getName(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()));
    }

    @Secured("ROLE_USER")
    @GetMapping("/getBirthdayOfMyCat")
    public String getCatBirthday(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format(catsService.getBirthday(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()).toString());
    }

    @Secured("ROLE_USER")
    @GetMapping("/getBreedOfMyCat")
    public String getCatBreed(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format(catsService.getBreed(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()));
    }

    @Secured("ROLE_USER")
    @GetMapping("/getColorOfMyCat")
    public String getCatColor(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format(catsService.getColor(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()).getCode());
    }
}
