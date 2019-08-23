package ru.antonpopov.juniortask.service;

import ru.antonpopov.juniortask.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User save(User user);

    User findById(Long id);

}
