package com.careercrafter.jobportal;

import com.careercrafter.jobportal.entity.Company;
import com.careercrafter.jobportal.entity.Job;
import com.careercrafter.jobportal.repo.CompanyRepo;
import com.careercrafter.jobportal.repo.JobRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class JobRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Test
    public void testCreateJobAndFetchAll() throws Exception {
        // Create a test company
        Company company = new Company();
        company.setCompanyName("TestCo");
        company.setIndustry("Tech");
        company.setLocation("Chennai");
        Company savedCompany = companyRepo.save(company);

        String requestBody = String.format("""
        {
            "title": "Backend Developer",
            "description": "Build APIs using Spring Boot",
            "location": "Remote",
            "salary": "120000",
            "employerEmail": "hr@testco.com",
            "company": { "companyId": %d }
        }
        """, savedCompany.getCompanyId());

        // Save Job
        mockMvc.perform(post("/api/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Backend Developer")))
                .andExpect(jsonPath("$.location", is("Remote")));

        // Verify it exists
        mockMvc.perform(get("/api/jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Backend Developer")));
    }
}
