package ru.antonpopov.juniortask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.antonpopov.juniortask.dao.UserRepository;
import ru.antonpopov.juniortask.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final Lock lock = new ReentrantLock();

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        lock.lock();
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        lock.unlock();
        return userList;
    }

    @Override
    public User save(User user) {
        User savedUser = null;
        if (userRepository.findUserByUserName(user.getUserName()) == null) {
            lock.lock();
            try {
                savedUser = userRepository.save(user);
            } finally {
                lock.unlock();
            }
        }
        return savedUser;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        lock.lock();
        try {
            user = userRepository.findById(id).orElse(null);
        } finally {
            lock.unlock();
        }
        return user;
    }
}
