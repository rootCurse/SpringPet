package com.itmo.kotiki.dataAccessObject.repository;

import com.itmo.kotiki.dataAccessObject.entity.CatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatsRepository extends JpaRepository<CatsEntity, Integer> {
}