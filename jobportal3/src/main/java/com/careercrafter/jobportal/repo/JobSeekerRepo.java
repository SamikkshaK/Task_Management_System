package com.careercrafter.jobportal.repo;

import com.careercrafter.jobportal.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerRepo extends JpaRepository<JobSeeker, Long> {
    JobSeeker findByEmail(String email);
}
