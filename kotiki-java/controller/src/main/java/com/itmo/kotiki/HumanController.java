package com.itmo.kotiki;


import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.repo.HumanRepository;
import com.itmo.kotiki.service.HumanService;
import com.itmo.kotiki.service.HumanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class HumanController {

    private HumanService humanService;

    @Autowired
    public HumanController(HumanRepository humanRepository){
        this.humanService = new HumanServiceImpl(humanRepository);
    }

    @PostMapping(value = "/saveHuman", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveNewCat(@RequestParam String name, Long birthday) {
        HumansEntity human = new HumansEntity(name, new Date(birthday));
        humanService.save(human);
    }

    @GetMapping("/humanName")
    public String getHumanName(@RequestParam int id) {
        return String.format(humanService.getName(id));
    }

    @GetMapping("/humanBirthday")
    public String getHumanBirthday(@RequestParam int id) {
        return String.format(humanService.getBirthday(id).toString());
    }
}
