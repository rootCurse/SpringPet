package com.itmo.kotiki.service.implement;

import com.itmo.kotiki.dao.HumanDAOImpl;
import com.itmo.kotiki.entity.HumansEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HumanServiceImplTest {

    @Test
    void getName() {
        var human = new HumansEntity("Ivan", new Date(1241225245));
        var humanService = new HumanServiceImpl();
        var idOfHuman = human.getHumanId();
        HumanDAOImpl humanDAO = mock(HumanDAOImpl.class);
        when(humanDAO.findById(idOfHuman)).thenReturn(human);
        humanService.changeHumanDAO(humanDAO);
        String nameForAssert = humanService.getName(idOfHuman);
        Assertions.assertEquals("Ivan", nameForAssert);
    }

    @Test
    void getBirthday() {
        Date date = new Date(1241225245);
        var human = new HumansEntity("Ivan", date);
        var humanService = new HumanServiceImpl();
        var idOfHuman = human.getHumanId();
        HumanDAOImpl humanDAO = mock(HumanDAOImpl.class);
        when(humanDAO.findById(idOfHuman)).thenReturn(human);
        humanService.changeHumanDAO(humanDAO);
        var dateForAssert = humanService.getBirthday(idOfHuman);
        Assertions.assertEquals(date, dateForAssert);
    }

    @Test
    void setBirthday() {
        Date date = new Date(1241225245);
        var human = new HumansEntity("Ivan", new Date(1));
        var humanService = new HumanServiceImpl();
        var idOfHuman = human.getHumanId();
        HumanDAOImpl humanDAO = mock(HumanDAOImpl.class);
        when(humanDAO.findById(idOfHuman)).thenReturn(human);
        humanService.changeHumanDAO(humanDAO);
        humanService.setBirthday(idOfHuman, date);
        var dateForAssert = humanService.getBirthday(idOfHuman);
        Assertions.assertEquals(date, dateForAssert);
    }

    @Test
    void setName() {
        var name = "qwerty";
        var human = new HumansEntity("Ivan", new Date(1));
        var humanService = new HumanServiceImpl();
        var idOfHuman = human.getHumanId();
        HumanDAOImpl humanDAO = mock(HumanDAOImpl.class);
        when(humanDAO.findById(idOfHuman)).thenReturn(human);
        humanService.changeHumanDAO(humanDAO);
        humanService.setName(idOfHuman, name);
        var nameForAssert = humanService.getName(idOfHuman);
        Assertions.assertEquals(name, nameForAssert);
    }
}