package ru.antonpopov.juniortask.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.antonpopov.juniortask.model.User;
import ru.antonpopov.juniortask.service.UserService;

import java.util.List;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users/")
    public List<User> getAllUsers() {
        logger.info("All users requested");
        return userService.getAllUsers();
    }

    @RequestMapping("/users")
    public List<User> error(RuntimeException e) {
        throw e;
    }

    @RequestMapping(value = "/users/save/userName={userName}", method = RequestMethod.POST)
    public User saveUser(@PathVariable String userName) {
        logger.info("POST recieved to save user with name " + userName);
        User user = new User();
        user.setUserName(userName);
        return userService.save(user);
    }
}
