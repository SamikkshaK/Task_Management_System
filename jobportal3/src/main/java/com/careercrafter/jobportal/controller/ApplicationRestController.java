package com.careercrafter.jobportal.controller;

import com.careercrafter.jobportal.entity.Application;
import com.careercrafter.jobportal.repo.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationRestController {

    @Autowired private ApplicationRepo applicationRepo;

    @PostMapping
    public Application create(@RequestBody Application app) {
        return applicationRepo.save(app);
    }

    @GetMapping
    public List<Application> getAll() {
        return applicationRepo.findAll();
    }

    @GetMapping("/{id}")
    public Application getById(@PathVariable Long id) {
        return applicationRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Application update(@PathVariable Long id, @RequestBody Application updated) {
        Application a = applicationRepo.findById(id).orElseThrow();
        a.setStatus(updated.getStatus());
        a.setJob(updated.getJob());
        a.setJobSeeker(updated.getJobSeeker());
        return applicationRepo.save(a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        applicationRepo.deleteById(id);
    }


    
}
