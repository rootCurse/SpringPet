package com.itmo.kotiki.dao;

import com.itmo.kotiki.entity.CatsEntity;

import java.util.List;

public interface CatDAO {
    public CatsEntity findById(int id);
    public void save(CatsEntity cat);
    public void update(CatsEntity cat);
    public void delete(CatsEntity cat);
    public List<CatsEntity> getAll();
}
