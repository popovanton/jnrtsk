package ru.antonpopov.juniortask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonpopov.juniortask.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String userName);
}
