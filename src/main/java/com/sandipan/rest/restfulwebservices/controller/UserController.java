package com.sandipan.rest.restfulwebservices.controller;

import com.sandipan.rest.restfulwebservices.exception.UserException;
import com.sandipan.rest.restfulwebservices.model.User;
import com.sandipan.rest.restfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null){
            throw new UserException("id + " + id);
        }
        //Creating HATEOAS entity model to return data and action items
        EntityModel<User> model = EntityModel.of(user);

        //for building link using HATEOAS using WebMvcLinkBuilder
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.
                linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                        .retrieveAllUsers());
        model.add(linkToUsers.withRel("All-Users"));
        return model;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if (user == null){
            throw new UserException("id + " + id);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        service.addUser(user);
       URI uriOfResourceLocation = ServletUriComponentsBuilder.fromCurrentRequest()
               .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uriOfResourceLocation).build();
    }

}
