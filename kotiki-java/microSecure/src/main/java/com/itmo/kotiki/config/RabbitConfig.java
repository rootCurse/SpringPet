package com.itmo.kotiki.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    @Bean(name = "GetCat")
    public Queue catQueue() {
        return new Queue("GetCat");
    }

    @Bean(name = "CatName")
    public Queue catNameQueue() {
        return new Queue("CatName");
    }

    @Bean(name = "CatBirthday")
    public Queue catBirthdayQueue() {
        return new Queue("CatBirthday");
    }

    @Bean(name = "CatColor")
    public Queue catColorQueue() {
        return new Queue("CatColor");
    }

    @Bean(name = "CatBreed")
    public Queue catBreedQueue() {
        return new Queue("CatBreed");
    }

    @Bean(name = "DeleteCat")
    public Queue catDeleteQueue() {
        return new Queue("DeleteCat");
    }

    @Bean(name = "HumanName")
    public Queue humanNameQueue() {
        return new Queue("HumanName");
    }

    @Bean(name = "HumanBirthday")
    public Queue humanBirthdayQueue() {
        return new Queue("HumanBirthday");
    }
}
