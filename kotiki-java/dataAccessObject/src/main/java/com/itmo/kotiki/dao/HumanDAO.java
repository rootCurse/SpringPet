package com.itmo.kotiki.dao;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.HumansEntity;

import java.util.List;
import java.util.Set;

public interface HumanDAO {
    public HumansEntity findById(int humanId);
    public void save(HumansEntity human);
    public void update(HumansEntity human);
    public void delete(HumansEntity human);
    public Set<CatsEntity> findCatsOFHuman(int humanId);
    public List<HumansEntity> getAll();
}
