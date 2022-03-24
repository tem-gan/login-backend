package com.example.login.demo.repository;

import com.example.login.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
}
