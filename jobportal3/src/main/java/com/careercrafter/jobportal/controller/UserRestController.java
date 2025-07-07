package com.careercrafter.jobportal.controller;

import com.careercrafter.jobportal.entity.User;
import com.careercrafter.jobportal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired private UserRepo userRepo;

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User updated) {
        User user = userRepo.findById(id).orElseThrow();
        user.setFullName(updated.getFullName());
        user.setEmail(updated.getEmail());
        user.setPassword(updated.getPassword());
        user.setRole(updated.getRole());
        return userRepo.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
    
}

