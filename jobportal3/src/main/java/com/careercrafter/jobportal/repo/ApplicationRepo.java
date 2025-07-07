package com.careercrafter.jobportal.repo;

import com.careercrafter.jobportal.entity.Application;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepo extends JpaRepository<Application, Long> {
    boolean existsByJobIdAndSeekerEmail(Long jobId, String seekerEmail);
    List<Application> findBySeekerEmail(String seekerEmail);
    List<Application> findByJob_EmployerEmail(String email);
    @Modifying
    @Query("DELETE FROM Application a WHERE a.job.id = :jobId")
    void deleteByJobId(@Param("jobId") Long jobId);
	List<Application> findByJob_Id(Long jobId);

}
