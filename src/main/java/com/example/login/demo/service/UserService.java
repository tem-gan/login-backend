package com.example.login.demo.service;

import com.example.login.demo.entity.User;
import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User create(User user, HttpServletRequest req);

    User read(Long id, HttpServletRequest req);

    User update(User user, HttpServletRequest req);

    void delete(Long id, HttpServletRequest req);

    List<User> getAll(HttpServletRequest req);

    User login(User user, HttpServletRequest req) throws NotFoundException;

    User method(User user, HttpServletRequest req) throws NotFoundException;

    User methodHelper(User user) throws NotFoundException;
}
