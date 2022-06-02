package com.itmo.kotiki.rabbit;

import com.itmo.kotiki.service.CatsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    @Autowired
    CatsService catsService;

    @RabbitListener(queues = "GetCat")
    public String getCat(int id) {
        return catsService.findCat(id).toString();
    }

    @RabbitListener(queues = "CatName")
    public String getCatName(int id) {
        return catsService.getName(id);
    }

    @RabbitListener(queues = "CatName")
    public String getCatName(int id, int humanId) {
        if (catsService.findCat(id).getHumanByHumansId().getHumanId() != humanId)
            return null;
        return catsService.getName(id);
    }

    @RabbitListener(queues = "CatBirthday")
    public String getCatBirthday(int id) {
        return catsService.getBirthday(id).toString();
    }

    @RabbitListener(queues = "CatBirthday")
    public String getCatBirthday(int id, int humanId) {
        if (catsService.findCat(id).getHumanByHumansId().getHumanId() != humanId)
            return null;
        return catsService.getBirthday(id).toString();
    }

    @RabbitListener(queues = "CatColor")
    public String getCatColor(int id) {
        return catsService.getColor(id).toString();
    }

    @RabbitListener(queues = "CatColor")
    public String getCatColor(int id, int humanId) {
        if (catsService.findCat(id).getHumanByHumansId().getHumanId() != humanId)
            return null;
        return catsService.getColor(id).toString();
    }

    @RabbitListener(queues = "CatBreed")
    public String getCatBreed(int id) {
        return catsService.getBreed(id);
    }

    @RabbitListener(queues = "CatBreed")
    public String getCatBreed(int id, int humanId) {
        if (catsService.findCat(id).getHumanByHumansId().getHumanId() != humanId)
            return null;
        return catsService.getBreed(id);
    }

    @RabbitListener(queues = "DeleteCat")
    public void deleteCats(int id) {
        catsService.delete(id);
    }
}

