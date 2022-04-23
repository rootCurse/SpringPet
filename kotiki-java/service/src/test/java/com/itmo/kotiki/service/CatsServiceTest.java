package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.Color;
import com.itmo.kotiki.repo.CatsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatsServiceTest {

    @Test
    void getName() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catId = cat.getCatId();
        CatsRepository catsRepository = mock(CatsRepository.class);
        when(catsRepository.getReferenceById(catId)).thenReturn(cat);
        CatsService catService = new CatsService(catsRepository);
        String nameForAssert = catService.getName(catId);
        Assertions.assertEquals("Ivan", nameForAssert);
    }

    @Test
    void getBirthday() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catId = cat.getCatId();
        CatsRepository catsRepository = mock(CatsRepository.class);
        when(catsRepository.getReferenceById(catId)).thenReturn(cat);
        CatsService catService = new CatsService(catsRepository);
        var dateForAssert = catService.getBirthday(catId);
        Assertions.assertEquals(new Date(1241225245), dateForAssert);
    }

    @Test
    void getBreed() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catId = cat.getCatId();
        CatsRepository catsRepository = mock(CatsRepository.class);
        when(catsRepository.getReferenceById(catId)).thenReturn(cat);
        CatsService catService = new CatsService(catsRepository);
        var breedForAssert = catService.getBreed(catId);
        Assertions.assertEquals("scotland", breedForAssert);
    }

    @Test
    void getColor() {
        var cat = new CatsEntity("Ivan", new Date(1241225245), Color.BLACK, "scotland");
        var catId = cat.getCatId();
        CatsRepository catsRepository = mock(CatsRepository.class);
        when(catsRepository.getReferenceById(catId)).thenReturn(cat);
        var catService = new CatsService(catsRepository);
        Color color = catService.getColor(catId);
        Assertions.assertEquals(Color.BLACK, color);
    }
}