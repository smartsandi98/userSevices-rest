package com.sandipan.rest.restfulwebservices.service;

import com.sandipan.rest.restfulwebservices.dao.UserDAO;
import com.sandipan.rest.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO dao;

    @Autowired
    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public List<User> findAll() {
        return dao.findAll();
    }

    public User addUser(User user) {
        return dao.save(user);
    }

    public User findOne(int id) {
        return dao.findOne(id);
    }

    public User deleteById(int id) {
        return dao.deleteById(id);
    }
}
