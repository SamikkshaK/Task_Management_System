package com.careercrafter.jobportal.repo;

import com.careercrafter.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepo extends JpaRepository<Job, Long> {
	List<Job> findAll();

	List<Job> findByEmployerEmail(String email);
 
}
