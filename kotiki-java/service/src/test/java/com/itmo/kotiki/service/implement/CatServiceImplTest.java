package com.itmo.kotiki.service.implement;

import com.itmo.kotiki.dao.CatDAOImpl;
import com.itmo.kotiki.dao.HumanDAOImpl;
import com.itmo.kotiki.entity.Color;
import com.itmo.kotiki.entity.CatsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatServiceImplTest {

    @Test
    void getName() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catService = new CatServiceImpl();
        var catId = cat.getCatId();
        CatDAOImpl catDAO = mock(CatDAOImpl.class);
        when(catDAO.findById(catId)).thenReturn(cat);
        catService.changeCatDAO(catDAO);
        String nameForAssert = catService.getName(catId);
        Assertions.assertEquals("Ivan", nameForAssert);
    }

    @Test
    void getColor() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catService = new CatServiceImpl();
        var catId = cat.getCatId();
        CatDAOImpl catDAO = mock(CatDAOImpl.class);
        when(catDAO.findById(catId)).thenReturn(cat);
        catService.changeCatDAO(catDAO);
        Color color = catService.getColor(catId);
        Assertions.assertEquals(Color.BLACK, color);
    }

    @Test
    void getBirthday() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catService = new CatServiceImpl();
        var catId = cat.getCatId();
        CatDAOImpl catDAO = mock(CatDAOImpl.class);
        when(catDAO.findById(catId)).thenReturn(cat);
        catService.changeCatDAO(catDAO);
        var dateForAssert = catService.getBirthday(catId);
        Assertions.assertEquals(new Date(1241225245), dateForAssert);
    }

    @Test
    void getBreed() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catService = new CatServiceImpl();
        var catId = cat.getCatId();
        CatDAOImpl catDAO = mock(CatDAOImpl.class);
        when(catDAO.findById(catId)).thenReturn(cat);
        catService.changeCatDAO(catDAO);
        var breedForAssert = catService.getBreed(catId);
        Assertions.assertEquals("scotland", breedForAssert);
    }

    @Test
    void setName() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catService = new CatServiceImpl();
        var catId = cat.getCatId();
        CatDAOImpl catDAO = mock(CatDAOImpl.class);
        when(catDAO.findById(catId)).thenReturn(cat);
        catService.changeCatDAO(catDAO);
        catService.setName(catId, "Petr");
        var nameForAssert = catService.getName(catId);
        Assertions.assertEquals("Petr", nameForAssert);
    }

    @Test
    void setBirthday() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catService = new CatServiceImpl();
        var catId = cat.getCatId();
        CatDAOImpl catDAO = mock(CatDAOImpl.class);
        when(catDAO.findById(catId)).thenReturn(cat);
        catService.changeCatDAO(catDAO);
        catService.setBirthday(catId, new Date(1));
        var dateForAssert = catService.getBirthday(catId);
        Assertions.assertEquals(new Date(1), dateForAssert);
    }

    @Test
    void setColor() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catService = new CatServiceImpl();
        var catId = cat.getCatId();
        CatDAOImpl catDAO = mock(CatDAOImpl.class);
        when(catDAO.findById(catId)).thenReturn(cat);
        catService.changeCatDAO(catDAO);
        catService.setColor(catId, Color.WHITE);
        var colorForAssert = catService.getColor(catId);
        Assertions.assertEquals(Color.WHITE, colorForAssert);
    }

    @Test
    void setBreed() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catService = new CatServiceImpl();
        var catId = cat.getCatId();
        CatDAOImpl catDAO = mock(CatDAOImpl.class);
        when(catDAO.findById(catId)).thenReturn(cat);
        catService.changeCatDAO(catDAO);
        catService.setBreed(catId, "british");
        var breedForAssert = catService.getBreed(catId);
        Assertions.assertEquals("british", breedForAssert);
    }
}