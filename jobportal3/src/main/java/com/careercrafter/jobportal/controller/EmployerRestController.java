package com.careercrafter.jobportal.controller;


import com.careercrafter.jobportal.entity.Employer;
import com.careercrafter.jobportal.repo.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerRestController {

    @Autowired private EmployerRepo employerRepo;

    @PostMapping
    public Employer create(@RequestBody Employer emp) {
        return employerRepo.save(emp);
    }

    @GetMapping
    public List<Employer> getAll() {
        return employerRepo.findAll();
    }

    @GetMapping("/{id}")
    public Employer getById(@PathVariable Long id) {
        return employerRepo.findById(id).orElseThrow();
    }
    


    @PutMapping("/{id}")
    public Employer update(@PathVariable Long id, @RequestBody Employer updated) {
        Employer emp = employerRepo.findById(id).orElseThrow();
        emp.setFullName(updated.getFullName());
        emp.setEmail(updated.getEmail());
        emp.setPassword(updated.getPassword());
        emp.setRole(updated.getRole());
        emp.setDesignation(updated.getDesignation());
        emp.setPhone(updated.getPhone());
        emp.setCompany(updated.getCompany());
        return employerRepo.save(emp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employerRepo.deleteById(id);
    }
}
