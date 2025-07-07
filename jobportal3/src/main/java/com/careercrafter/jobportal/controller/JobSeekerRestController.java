package com.careercrafter.jobportal.controller;

import com.careercrafter.jobportal.entity.JobSeeker;
import com.careercrafter.jobportal.repo.JobSeekerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seekers")
public class JobSeekerRestController {

    @Autowired private JobSeekerRepo jobSeekerRepo;

    @PostMapping
    public JobSeeker create(@RequestBody JobSeeker seeker) {
        return jobSeekerRepo.save(seeker);
    }

    @GetMapping
    public List<JobSeeker> getAll() {
        return jobSeekerRepo.findAll();
    }

    @GetMapping("/{id}")
    public JobSeeker getById(@PathVariable Long id) {
        return jobSeekerRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public JobSeeker update(@PathVariable Long id, @RequestBody JobSeeker updated) {
        JobSeeker seeker = jobSeekerRepo.findById(id).orElseThrow();
        seeker.setFullName(updated.getFullName());
        seeker.setEmail(updated.getEmail());
        seeker.setPassword(updated.getPassword());
        seeker.setRole(updated.getRole());
        seeker.setEducation(updated.getEducation());
        seeker.setExperience(updated.getExperience());
        seeker.setResumeLink(updated.getResumeLink());
        return jobSeekerRepo.save(seeker);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jobSeekerRepo.deleteById(id);
    }
}
