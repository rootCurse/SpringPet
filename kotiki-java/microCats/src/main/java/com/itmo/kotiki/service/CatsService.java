package com.itmo.kotiki.service;

import com.itmo.kotiki.dataAccessObject.entity.CatsEntity;
import com.itmo.kotiki.dataAccessObject.entity.Color;
import com.itmo.kotiki.dataAccessObject.entity.HumansEntity;

import java.sql.Date;
import java.util.List;

public interface CatsService {

    void save(CatsEntity cats);

    CatsEntity findCat(int id);

    String getName(int id);

    String getName(int id, int humanId);

    Date getBirthday(int id);

    Date getBirthday(int id, int humanId);

    String getBreed(int id);

    String getBreed(int id, int humanId);

    Color getColor(int id);

    Color getColor(int id, int humanId);

    HumansEntity getHuman(int id);

    List<CatsEntity> getAll();

    void delete(int id);
}
