package com.careercrafter.jobportal;

import com.careercrafter.jobportal.entity.JobSeeker;
import com.careercrafter.jobportal.entity.Resume;
import com.careercrafter.jobportal.repo.EmployerRepo;
import com.careercrafter.jobportal.repo.JobSeekerRepo;
import com.careercrafter.jobportal.repo.ResumeRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ResumeRestControllerTest {

    @Autowired
    private ResumeRepo resumeRepo;

    @Autowired
    private JobSeekerRepo jobSeekerRepo;

    @Autowired
    private EmployerRepo employerRepo;

    @BeforeEach
    public void setup() {
        // delete child tables first to avoid foreign key violations
        resumeRepo.deleteAll();
        employerRepo.deleteAll(); // If Employer also inherits from User
        jobSeekerRepo.deleteAll(); // JobSeeker also inherits from User
    }

    @Test
    public void testCreateAndFetchResume() {
        // 1. Create and save JobSeeker
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setFullName("Samikksha K");
        jobSeeker.setEmail("samikksha@example.com");
        jobSeeker.setPassword("secret123");
        //jobSeeker.setRole("JOB_SEEKER");
        jobSeeker.setEducation("B.E. CSE");
        jobSeeker.setExperience("1 year");
        jobSeeker.setResumeLink("http://example.com/resume.pdf");

        JobSeeker savedJobSeeker = jobSeekerRepo.save(jobSeeker);

        // 2. Create Resume linked to saved JobSeeker
        Resume resume = new Resume();
        resume.setDescription("Spring Boot Resume");
        resume.setFileType("PDF");
        resume.setFileUrl("http://example.com/resume.pdf");
        resume.setJobSeeker(savedJobSeeker);

        Resume savedResume = resumeRepo.save(resume);

        // 3. Fetch and assert
        List<Resume> allResumes = resumeRepo.findAll();
        assertThat(allResumes).isNotEmpty();
        assertThat(allResumes.get(0).getJobSeeker().getEmail()).isEqualTo("samikksha@example.com");
    }
}
