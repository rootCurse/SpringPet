package com.itmo.kotiki.dao;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.HumansEntity;

import java.util.List;
import java.util.Set;

public interface HumanDAO {
    HumansEntity findById(int humanId);

    void save(HumansEntity human);

    void update(HumansEntity human);

    void delete(HumansEntity human);

    Set<CatsEntity> findCatsOFHuman(int humanId);

    List<HumansEntity> getAll();
}
