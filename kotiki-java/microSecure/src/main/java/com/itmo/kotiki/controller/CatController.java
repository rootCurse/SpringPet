package com.itmo.kotiki.controller;

import com.itmo.kotiki.rabbit.RabbitSender;
import com.itmo.kotiki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("ServiceController")
@Secured(value = {"ROLE_ADMIN", "ROLE_USER"})
public class CatController {
    @Autowired
    UserService userService;
    @Autowired
    RabbitSender rabbitSender;

    @GetMapping("/catName")
    public String getCatName(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userService.getUser(authentication.getName()).getRole() != "ROLE_ADMIN")
            return String.format(rabbitSender.sendCatName(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()));
        return String.format(rabbitSender.sendCatName(id));
    }

    @GetMapping("/catBirthday")
    public String getCatBirthday(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userService.getUser(authentication.getName()).getRole() != "ROLE_ADMIN")
            return String.format(rabbitSender.sendCatBirthday(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()));
        return String.format(rabbitSender.sendCatBirthday(id));
    }

    @GetMapping("/catBreed")
    public String getCatBreed(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userService.getUser(authentication.getName()).getRole() != "ROLE_ADMIN")
            return String.format(rabbitSender.sendCatBreed(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()));
        return String.format(rabbitSender.sendCatBreed(id));
    }

    @GetMapping("/catColor")
    public String getCatColor(@RequestParam int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userService.getUser(authentication.getName()).getRole() != "ROLE_ADMIN")
            return String.format(rabbitSender.sendCatColor(id, userService.getUser(authentication.getName()).getHumansByHumanId().getHumanId()));
        return String.format(rabbitSender.sendCatColor(id));
    }


    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/cat")
    public String getCat(@RequestParam int id) {
        return rabbitSender.sendCat(id);
    }

    @Secured(value = "ROLE_ADMIN")
    @DeleteMapping("/oneCat")
    public void deleteOneCat(int id) {
        rabbitSender.sendDeleteCat(id);
    }


}
