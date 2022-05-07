package com.itmo.kotiki.repo;

import com.itmo.kotiki.entity.CatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("catsDAO")
public interface CatsRepository extends JpaRepository<CatsEntity, Integer> {
}
