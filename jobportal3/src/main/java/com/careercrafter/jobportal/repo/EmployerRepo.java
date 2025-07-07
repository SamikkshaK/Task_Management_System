package com.careercrafter.jobportal.repo;

import com.careercrafter.jobportal.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepo extends JpaRepository<Employer, Long> {
    Employer findByEmail(String email);
}
