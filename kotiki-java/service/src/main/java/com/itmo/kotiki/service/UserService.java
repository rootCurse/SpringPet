package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.entity.UsersEntity;

import java.util.List;

public interface UserService {
    void save(UsersEntity usersEntity);

    String getLogin(int id);

    String getPassword(int id);

    String getRole(int id);

    UsersEntity getUser(int id);

    UsersEntity getUser(String username);

    HumansEntity getHuman(int id);

    void delete(int id);

    List<UsersEntity> getAllUsers();
}
