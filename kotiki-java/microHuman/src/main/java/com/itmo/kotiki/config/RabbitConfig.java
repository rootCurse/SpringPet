package com.itmo.kotiki.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    @Bean(name = "HumanName")
    public Queue humanNameQueue() {
        return new Queue("HumanName");
    }

    @Bean(name = "HumanBirthday")
    public Queue humanBirthdayQueue() {
        return new Queue("HumanBirthday");
    }
}
