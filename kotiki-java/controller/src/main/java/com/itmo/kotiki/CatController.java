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
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController("ServiceController")
@Secured(value = "ROLE_ADMIN")
public class CatController {
    private CatsService catsService;

    @Autowired
    public CatController( CatsRepository catsRepository){
        this.catsService = new CatsServiceImpl(catsRepository);
    }

    @GetMapping("/catName")
    public String getCatName(@RequestParam int id){
        return String.format(catsService.getName(id));
    }

    @GetMapping("/catBirthday")
    public String getCatBirthday(@RequestParam int id) {
        return String.format(catsService.getBirthday(id).toString());
    }

    @GetMapping("/catBreed")
    public String getCatBreed(@RequestParam int id) {
        return String.format(catsService.getBreed(id));
    }

    @GetMapping("/catColor")
    public String getCatColor(@RequestParam int id) {
        return String.format(catsService.getColor(id).getCode());
    }

    @GetMapping("/catHuman")
    public HumansEntity getCatHuman(@RequestParam int id) {
        return catsService.getHuman(id);
    }

    @GetMapping("/cat")
    public CatsEntity getCat(@RequestParam int id){
        return catsService.findCat(id);
    }

    @GetMapping("/allCats")
    public List<CatsEntity> getAllCat(){
        return catsService.getAll();
    }

    @PostMapping(value = "/saveCat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveNewCat(@RequestParam String name, String breed, Long birthday, Color color) {
        CatsEntity cat = new CatsEntity(name, new Date(birthday), color, breed);
        catsService.save(cat);
    }

    @DeleteMapping("/oneCat")
    public void deleteOneCat(int id){
        catsService.delete(id);
    }



}
