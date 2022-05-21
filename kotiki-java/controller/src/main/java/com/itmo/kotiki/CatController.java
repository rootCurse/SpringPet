package com.itmo.kotiki;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.Color;
import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.repo.CatsRepository;
import com.itmo.kotiki.repo.UserRepository;
import com.itmo.kotiki.service.CatsService;
import com.itmo.kotiki.service.CatsServiceImpl;
import com.itmo.kotiki.service.UserService;
import com.itmo.kotiki.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController("ServiceController")
@Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
public class CatController {
    private CatsService catsService;
    private UserService userService;

    @Autowired
    public CatController(CatsRepository catsRepository, UserRepository userRepository) {
        this.catsService = new CatsServiceImpl(catsRepository);
        this.userService = new UserServiceImpl(userRepository);
    }

    @GetMapping("/catName")
    public String getCatName(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userService.getUser(authentication.getName()).getRole() != "ROLE_ADMIN")
            return String.format(catsService.getBirthday(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()).toString());
        return String.format(catsService.getName(id));
    }

    @GetMapping("/catBirthday")
    public String getCatBirthday(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userService.getUser(authentication.getName()).getRole() != "ROLE_ADMIN")
            return String.format(catsService.getBirthday(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()).toString());
        return String.format(catsService.getBirthday(id).toString());
    }

    @GetMapping("/catBreed")
    public String getCatBreed(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userService.getUser(authentication.getName()).getRole() != "ROLE_ADMIN")
            return String.format(catsService.getBreed(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()));
        return String.format(catsService.getBreed(id));
    }

    @GetMapping("/catColor")
    public String getCatColor(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userService.getUser(authentication.getName()).getRole() != "ROLE_ADMIN")
            return String.format(catsService.getColor(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()).getCode());
        return String.format(catsService.getColor(id).getCode());
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/catHuman")
    public HumansEntity getCatHuman(@RequestParam int id) {
        return catsService.getHuman(id);
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/cat")
    public CatsEntity getCat(@RequestParam int id) {
        return catsService.findCat(id);
    }

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/allCats")
    public List<CatsEntity> getAllCat() {
        return catsService.getAll();
    }

    @Secured(value = "ROLE_ADMIN")
    @PostMapping(value = "/saveCat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveNewCat(@RequestParam String name, String breed, Long birthday, Color color) {
        CatsEntity cat = new CatsEntity(name, new Date(birthday), color, breed);
        catsService.save(cat);
    }

    @Secured(value = "ROLE_ADMIN")
    @DeleteMapping("/oneCat")
    public void deleteOneCat(int id) {
        catsService.delete(id);
    }


}
