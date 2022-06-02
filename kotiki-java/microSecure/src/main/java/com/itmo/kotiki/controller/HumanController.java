package com.itmo.kotiki.controller;

import com.itmo.kotiki.dataAccessObject.entity.CatsEntity;
import com.itmo.kotiki.dataAccessObject.repository.UserRepository;
import com.itmo.kotiki.rabbit.RabbitSender;
import com.itmo.kotiki.service.UserService;
import com.itmo.kotiki.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class HumanController {

    @Autowired
    private RabbitSender rabbitSender;

    private final UserService userService;


    @Autowired
    public HumanController(UserRepository userRepository) {
        this.userService = new UserServiceImpl(userRepository);
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/humanName")
    public String getHumanName(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        if (user.getRole() != "ROLE_ADMIN")
            return String.format(user.getHumansByHumanId().getName());
        return String.format(rabbitSender.sendHumanName(id));

    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/humanBirthday")
    public String getHumanBirthday(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.getUser(authentication.getName());
        if (user.getRole() != "ROLE_ADMIN")
            return String.format(user.getHumansByHumanId().getBirthday().toString());
        return String.format(rabbitSender.sendHumanBirthday(id));
    }

    @Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/myCats")
    public Set<CatsEntity> getMyCats() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUser(authentication.getName()).getHumansByHumanId().getCatsByHumanId();
    }
}
