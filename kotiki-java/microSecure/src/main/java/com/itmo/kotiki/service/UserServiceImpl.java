package com.itmo.kotiki.service;

import com.itmo.kotiki.dataAccessObject.entity.HumansEntity;
import com.itmo.kotiki.dataAccessObject.entity.UsersEntity;
import com.itmo.kotiki.dataAccessObject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UsersEntity usersEntity) {
        userRepository.save(usersEntity);
    }

    @Override
    public String getLogin(int id) {
        return userRepository.getById(id).getLogin();
    }

    @Override
    public String getPassword(int id) {
        return userRepository.getById(id).getPassword();
    }

    @Override
    public String getRole(int id) {
        return userRepository.getById(id).getRole();
    }

    @Override
    public UsersEntity getUser(int id) {
        return userRepository.getById(id);
    }

    @Override
    public UsersEntity getUser(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public HumansEntity getHuman(int id) {
        return userRepository.getById(id).getHumansByHumanId();
    }

    @Override
    public void delete(int id) {
        userRepository.delete(userRepository.getById(id));
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
