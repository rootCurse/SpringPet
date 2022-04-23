package com.itmo.kotiki.repo;

import com.itmo.kotiki.entity.HumansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("HumanRepository")
public interface HumanRepository extends JpaRepository<HumansEntity, Integer> {
}
