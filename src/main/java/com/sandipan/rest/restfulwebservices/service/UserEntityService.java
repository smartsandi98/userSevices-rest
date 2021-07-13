package com.sandipan.rest.restfulwebservices.service;

import com.sandipan.rest.restfulwebservices.entity.UserEntity;
import com.sandipan.rest.restfulwebservices.repo.UserDAORepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService {

    private final UserDAORepo dao;

    @Autowired
    public UserEntityService(UserDAORepo dao) {
        this.dao = dao;
    }

    public List<UserEntity> findAll() {
        return dao.findAll();
    }

    public UserEntity addUser(UserEntity user) {
        return dao.save(user);
    }

    public Optional<UserEntity> findOne(int id) {
        return dao.findById(id);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

}
