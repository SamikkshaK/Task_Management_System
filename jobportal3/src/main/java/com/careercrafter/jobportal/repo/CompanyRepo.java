package com.careercrafter.jobportal.repo;

import com.careercrafter.jobportal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {}
