package com.itmo.kotiki;

import com.itmo.kotiki.entity.Role;
import com.itmo.kotiki.entity.UsersEntity;
import com.itmo.kotiki.repo.UserRepository;
import com.itmo.kotiki.service.UserService;
import com.itmo.kotiki.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Secured(value = "ROLE_ADMIN")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserRepository userRepository) {
        userService = new UserServiceImpl(userRepository);
    }

    @DeleteMapping("/oneUser")
    public void deleteOneUser(@RequestParam int id) {
        userService.delete(id);
    }

    @PostMapping("/signUp")
    public void signUp(@RequestParam String login, String password, Role role, int humanId) {
        UsersEntity user = new UsersEntity(login, password, role, userService.getHuman(humanId));
        userService.save(user);
    }

    @GetMapping("/allUsers")
    public List<UsersEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user")
    public UsersEntity getUser(@RequestParam int id) {
        return userService.getUser(id);
    }
}
