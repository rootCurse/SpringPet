package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.Color;
import com.itmo.kotiki.entity.HumansEntity;

import java.sql.Date;
import java.util.List;

public interface CatsService {
    public void save(CatsEntity cats);
    public CatsEntity findCat(int id);
    public String getName(int id);
    public Date getBirthday(int id);
    public String getBreed(int id);
    public Color getColor(int id);
    public HumansEntity getHuman(int id);
    public List<CatsEntity> getAll();
    public void delete(int id);
}
