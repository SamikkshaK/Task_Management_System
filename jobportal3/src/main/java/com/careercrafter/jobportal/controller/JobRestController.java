package com.careercrafter.jobportal.controller;

import com.careercrafter.jobportal.entity.Job;
import com.careercrafter.jobportal.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobRestController {

    @Autowired private JobRepo jobRepo;

    @PostMapping
    public Job create(@RequestBody Job job) {
        return jobRepo.save(job);
    }

    @GetMapping
    public List<Job> getAll() {
        return jobRepo.findAll();
    }

    @GetMapping("/{id}")
    public Job getById(@PathVariable Long id) {
        return jobRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Job update(@PathVariable Long id, @RequestBody Job updated) {
        Job j = jobRepo.findById(id).orElseThrow();
        j.setTitle(updated.getTitle());
        j.setDescription(updated.getDescription());
        j.setLocation(updated.getLocation());
        j.setSalary(updated.getSalary());
        j.setEmployerEmail(updated.getEmployerEmail());
        return jobRepo.save(j);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jobRepo.deleteById(id);
    }
}
