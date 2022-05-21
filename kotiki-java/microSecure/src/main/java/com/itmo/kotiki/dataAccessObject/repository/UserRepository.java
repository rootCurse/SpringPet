package com.itmo.kotiki.dataAccessObject.repository;

import com.itmo.kotiki.dataAccessObject.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Integer> {
    Optional<UsersEntity> findByUsername(String username);
}
