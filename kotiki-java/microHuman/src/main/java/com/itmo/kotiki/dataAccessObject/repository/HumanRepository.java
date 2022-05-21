package com.itmo.kotiki.dataAccessObject.repository;

import com.itmo.kotiki.dataAccessObject.entity.HumansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("HumanRepository")
public interface HumanRepository extends JpaRepository<HumansEntity, Integer> {
}
