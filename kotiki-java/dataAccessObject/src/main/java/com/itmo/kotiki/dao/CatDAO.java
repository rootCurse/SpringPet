package com.itmo.kotiki.dao;

import com.itmo.kotiki.entity.CatsEntity;

import java.util.List;

public interface CatDAO {
    CatsEntity findById(int id);

    void save(CatsEntity cat);

    void update(CatsEntity cat);

    void delete(CatsEntity cat);

    List<CatsEntity> getAll();
}
