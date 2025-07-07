package com.careercrafter.jobportal.controller;

import com.careercrafter.jobportal.entity.JobSeeker;
import com.careercrafter.jobportal.entity.Resume;
import com.careercrafter.jobportal.repo.JobSeekerRepo;
import com.careercrafter.jobportal.repo.ResumeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumeRestController {

    @Autowired private ResumeRepo resumeRepo;
    @Autowired private JobSeekerRepo jobSeekerRepo;

    @PostMapping
    public Resume create(@RequestBody Resume resume) {
        // Optional: Validate JobSeeker exists
        Long seekerId = resume.getJobSeeker().getId();
        JobSeeker jobSeeker = jobSeekerRepo.findById(seekerId).orElseThrow();
        resume.setJobSeeker(jobSeeker);
        return resumeRepo.save(resume);
    }

    @GetMapping
    public List<Resume> getAll() {
        return resumeRepo.findAll();
    }

    @GetMapping("/{id}")
    public Resume getById(@PathVariable Long id) {
        return resumeRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Resume update(@PathVariable Long id, @RequestBody Resume updated) {
        Resume resume = resumeRepo.findById(id).orElseThrow();
        resume.setFileUrl(updated.getFileUrl());
        resume.setFileType(updated.getFileType());
        resume.setDescription(updated.getDescription());
        resume.setJobSeeker(updated.getJobSeeker());
        return resumeRepo.save(resume);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        resumeRepo.deleteById(id);
    }
}

