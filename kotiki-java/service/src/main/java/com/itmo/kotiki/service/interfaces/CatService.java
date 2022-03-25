package com.itmo.kotiki.service.interfaces;

import com.itmo.kotiki.dao.CatDAOImpl;
import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.Color;
import com.itmo.kotiki.entity.HumansEntity;

import java.sql.Date;
import java.util.List;

public interface CatService {
    void saveCat(String nameOfCat, Date birthday, Color color, String breed);

    void deleteCat(int idOfCat);

    String getName(int idOfCat);

    Color getColor(int idOfCat);

    Date getBirthday(int idOfCat);

    String getBreed(int idOfCat);

    HumansEntity getHuman(int idOfCat);

    List<CatsEntity> getAllCats();

    void setName(int idOfCat, String name);

    void setBirthday(int idOfCat, Date birthday);

    void setColor(int idOfCat, Color color);

    void setBreed(int idOfCat, String breed);

    void setHuman(int idOfCat, int idOfHuman);

    void changeCatDAO(CatDAOImpl catDAO);
}
