package com.itmo.kotiki.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate template;

    public String sendCat(int id) {
        return template.convertSendAndReceive("GetCat", id).toString();
    }

    public String sendCatName(int id) {
        return template.convertSendAndReceive("CatName", id).toString();
    }

    public String sendCatName(int id, int humanID) {
        return template.convertSendAndReceive("CatName", id).toString();
    }

    public String sendCatBirthday(int id) {
        return template.convertSendAndReceive("CatBirthday", id).toString();
    }

    public String sendCatBirthday(int id, int humanID) {
        return template.convertSendAndReceive("CatBirthday", id).toString();
    }

    public String sendCatColor(int id) {
        return template.convertSendAndReceive("CatColor", id).toString();
    }

    public String sendCatColor(int id, int humanID) {
        return template.convertSendAndReceive("CatColor", id).toString();
    }

    public String sendCatBreed(int id) {
        return template.convertSendAndReceive("CatBreed", id).toString();
    }

    public String sendCatBreed(int id, int humanID) {
        return template.convertSendAndReceive("CatBreed", id).toString();
    }

    public String sendDeleteCat(int id) {
        return template.convertSendAndReceive("DeleteCat", id).toString();
    }

    public String sendHumanName(int id) {
        return template.convertSendAndReceive("HumanName", id).toString();
    }

    public String sendHumanBirthday(int id) {
        return template.convertSendAndReceive("HumanBirthday", id).toString();
    }
}
