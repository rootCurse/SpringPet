package com.itmo.kotiki;

import com.itmo.kotiki.repo.CatsRepository;
import com.itmo.kotiki.repo.HumanRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class KotikiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(KotikiApplication.class);
		ServiceController controller = new ServiceController(context.getBean(HumanRepository.class), context.getBean(CatsRepository.class));
	}

}
