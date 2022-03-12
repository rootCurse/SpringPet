package com.itmo.kotiki.service.interfaces;

import com.itmo.kotiki.dao.HumanDAOImpl;
import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.HumansEntity;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface HumanService {
    void saveHuman(String name, Date birthday);
    void deleteHuman(int idOfHuman);
    List<HumansEntity> getAllHumans();
    String getName(int idOfHuman);
    Date getBirthday(int idOfHuman);
    Set<CatsEntity> getCatsOfHuman(int idOfHuman);
    void setName(int idOfHuman, String name);
    void setBirthday(int idOfHuman, Date birthday);
    void changeHumanDAO(HumanDAOImpl humanDAO);
}
