package com.careercrafter.jobportal.repo;

import com.careercrafter.jobportal.entity.JobSeeker;
import com.careercrafter.jobportal.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepo extends JpaRepository<Resume, Long> {
    Resume findTopByJobSeekerOrderByIdDesc(JobSeeker seeker);
}
