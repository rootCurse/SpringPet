package com.itmo.kotiki;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.Color;
import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.repo.CatsRepository;
import com.itmo.kotiki.repo.HumanRepository;
import com.itmo.kotiki.service.CatsService;
import com.itmo.kotiki.service.CatsServiceImpl;
import com.itmo.kotiki.service.HumanService;
import com.itmo.kotiki.service.HumanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController("ServiceController")
public class ServiceController {
    private HumanService humanService;
    private CatsService catsService;

    @Autowired
    public ServiceController(HumanRepository humanRepository, CatsRepository catsRepository){
        this.humanService = new HumanServiceImpl(humanRepository);
        this.catsService = new CatsServiceImpl(catsRepository);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/getCatName")
    public String getCatName(@RequestParam int id){
        return String.format(catsService.getName(id));
    }

    @GetMapping("/getCatBirthday")
    public String getCatBirthday(@RequestParam int id) {
        return String.format(catsService.getBirthday(id).toString());
    }

    @GetMapping("/getCatBreed")
    public String getCatBreed(@RequestParam int id) {
        return String.format(catsService.getBreed(id));
    }

    @GetMapping("/getCatColor")
    public String getCatColor(@RequestParam int id) {
        return String.format(catsService.getColor(id).getCode());
    }

    @GetMapping("/getCatHuman")
    public HumansEntity getCatHuman(@RequestParam int id) {
        return catsService.getHuman(id);
    }

    @GetMapping("/getCat")
    public CatsEntity getCat(@RequestParam int id){
        return catsService.findCat(id);
    }

    @GetMapping("/getAllCats")
    public List<CatsEntity> getAllCat(){
        return catsService.getAll();
    }

    @PostMapping(value = "/saveNewCat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveNewCat(@RequestParam String name, String breed, Long birthday, Color color) {
        CatsEntity cat = new CatsEntity(name, new Date(birthday), color, breed);
        catsService.save(cat);
    }

    @PostMapping(value = "/saveNewHuman", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveNewCat(@RequestParam String name, Long birthday) {
        HumansEntity human = new HumansEntity(name, new Date(birthday));
        humanService.save(human);
    }

    @DeleteMapping("/deleteOneCat")
    public void deleteOneCat(int id){
        catsService.delete(id);
    }

    @GetMapping("/getHumanName")
    public String getHumanName(@RequestParam int id) {
        return String.format(humanService.getName(id));
    }

    @GetMapping("/getHumanBirthday")
    public String getHumanBirthday(@RequestParam int id) {
        return String.format(humanService.getBirthday(id).toString());
    }

}
