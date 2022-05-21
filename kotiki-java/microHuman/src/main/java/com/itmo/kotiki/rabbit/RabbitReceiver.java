package com.itmo.kotiki.rabbit;

import com.itmo.kotiki.service.HumanService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    @Autowired
    HumanService humanService;

    @RabbitListener(queues = "HumanName")
    public String getHumanName(int id) {
        return humanService.getName(id);
    }

    @RabbitListener(queues = "HumanBirthday")
    public String getHumanBirthday(int id) {
        return humanService.getBirthday(id).toString();
    }
}
