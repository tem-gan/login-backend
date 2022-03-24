package com.example.login.demo.serviceImp;

import com.example.login.demo.entity.User;
import com.example.login.demo.repository.UserRepository;
import com.example.login.demo.service.UserService;
import javassist.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;



    public User create(User user, HttpServletRequest req) {
        return repository.save(user);
    }


    public User read(Long id, HttpServletRequest req) {
        return repository.findById(id).orElse(null);
    }


    public User update(User user, HttpServletRequest req) {
        return repository.save(user);
    }


    public void delete(Long id, HttpServletRequest req) {
        repository.deleteById(id);
    }


    public List<User> getAll(HttpServletRequest req) {
        List<User> user = repository.findAll();

        return user;
    }


    public User login(User user, HttpServletRequest req) throws NotFoundException {
        System.out.println(user.getUsername() +"    "+ user.getPassword());
        User user1 = repository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow(() -> new NotFoundException("User oldsongui"));

        return user1;
    }

   //find user by username
    public User method(User user, HttpServletRequest req) throws NotFoundException {
        User user1 = repository.findByUsername(user.getUsername()).orElseThrow(()->new NotFoundException("Username oldsoongui"));
        return user1;
    }


    public User methodHelper(User user) throws NotFoundException {
        User user1 = repository.findByUsername(user.getUsername()).orElseThrow(()->new NotFoundException("..."));
        return user1;


    }
}
