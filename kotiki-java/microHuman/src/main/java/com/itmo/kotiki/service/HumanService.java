package com.itmo.kotiki.service;

import com.itmo.kotiki.dataAccessObject.entity.CatsEntity;
import com.itmo.kotiki.dataAccessObject.entity.HumansEntity;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface HumanService {
    String getName(int id);

    void save(HumansEntity human);

    Date getBirthday(int id);

    Set<CatsEntity> getCatsOfHuman(int id);

    HumansEntity getHuman(int id);

    List<HumansEntity> getAllHumans();
}
