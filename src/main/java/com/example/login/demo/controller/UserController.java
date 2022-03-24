package com.example.login.demo.controller;

import com.example.login.demo.entity.User;
import com.example.login.demo.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService service;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody User user, HttpServletRequest req) {
        String salt  = Encryption.getSalt(30);
        user.setIv(salt);
        String securePassword = Encryption.generateSecurePassword(user.getPassword(), user.getIv());
        user.setPassword(securePassword);

        return ResponseEntity.ok(service.create(user, req));
    }
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> read(@PathVariable("id") Long id, HttpServletRequest req) {

        return ResponseEntity.ok(service.read(id, req));
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody User user, HttpServletRequest req) {
        return ResponseEntity.ok(service.update(user, req));
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id, HttpServletRequest req) {
        service.delete(id, req);
        return ResponseEntity.ok(null);
    }
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<Object> delete(HttpServletRequest req) {
        return ResponseEntity.ok(service.getAll(req));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody User user, HttpServletRequest req) throws Exception {
        try {

            return ResponseEntity.ok(service.login(user, req));
        } catch (NotFoundException exception){
            throw new NotFoundException(exception.getMessage());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @RequestMapping(value = "/method", method = RequestMethod.POST)
    public ResponseEntity<Object> method(@RequestBody User user, HttpServletRequest req) throws Exception {
        try {

            User user1=service.methodHelper(user);
            boolean passwordMatch = Encryption.verifyUserPassword(user.getPassword(),user1.getPassword(),user1.getIv());
            if(passwordMatch){
                return ResponseEntity.ok(service.method(user, req));
            }
            return (ResponseEntity<Object>) ResponseEntity.status(400);
        } catch (NotFoundException exception){
            throw new NotFoundException(exception.getMessage());
        }
    }
    @RequestMapping(value="print")
    public String print(){
        return "hi";
    }

}
