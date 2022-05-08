package com.codewars.practice.controller;

import com.codewars.practice.model.User;
import com.codewars.practice.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Controller{
    private final UserRepository repository;
    @GetMapping("/")
    public List<User> getAll(){
        return repository.findAll();
    }
    @PostMapping("/")
    public User addUser(@RequestBody User user){
        try{
            return repository.save(user);

        } catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the entity in the request is null", e);
        }
    }
    @DeleteMapping("/")
    public void deleteUserById(@PathVariable String id){
        try{
            repository.deleteById(id);
        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the id wasn't found in our database!", e);
        }
    }
}