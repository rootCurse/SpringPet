package com.itmo.kotiki.Main;


import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.Color;
import com.itmo.kotiki.repo.CatsRepository;
import com.itmo.kotiki.repo.HumanRepository;
import com.itmo.kotiki.service.CatsService;
import com.itmo.kotiki.service.HumanService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.itmo.kotiki.repo", "com.itmo.kotiki.entity", "com.itmo.kotiki.service"})
@EnableJpaRepositories(basePackages = {"com.itmo.kotiki.repo"})
@EntityScan(basePackages = {"com.itmo.kotiki."})
@ComponentScan({"com.itmo.kotiki.repo", "com.itmo.kotiki.entity", "com.itmo.kotiki.service"})
@RestController
public class KotikiApplication {
	static HumanService humanService;
	static CatsService catsService;


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KotikiApplication.class);
		catsService = new CatsService(context.getBean(CatsRepository.class));
		humanService = new HumanService(context.getBean(HumanRepository.class));
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
	public String getCatHuman(@RequestParam int id) {
		String temp = catsService.getHuman(id) != null ? catsService.getHuman(id).getName() : "The cat has no owner";
		return String.format(temp);
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
