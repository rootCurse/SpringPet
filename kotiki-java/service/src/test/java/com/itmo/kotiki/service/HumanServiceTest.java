package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.repo.HumanRepository;
import com.itmo.kotiki.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HumanServiceTest {

    @Test
    void getName() {
        var human = new HumansEntity("Ivan", new Date(1241225245));
        var idOfHuman = human.getHumanId();
        HumanRepository humanRepository = mock(HumanRepository.class);
        when(humanRepository.getById(idOfHuman)).thenReturn(human);
        var humanService = new HumanServiceImpl(humanRepository);
        String nameForAssert = humanService.getName(idOfHuman);
        Assertions.assertEquals("Ivan", nameForAssert);
    }

    @Test
    void getBirthday() {
        Date date = new Date(1241225245);
        var human = new HumansEntity("Ivan", date);
        var idOfHuman = human.getHumanId();
        HumanRepository humanRepository = mock(HumanRepository.class);
        when(humanRepository.getById(idOfHuman)).thenReturn(human);
        var humanService = new HumanServiceImpl(humanRepository);
        var dateForAssert = humanService.getBirthday(idOfHuman);
        Assertions.assertEquals(date, dateForAssert);
    }
}