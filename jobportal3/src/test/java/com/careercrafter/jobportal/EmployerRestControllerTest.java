package com.careercrafter.jobportal;

import com.careercrafter.jobportal.entity.Company;
import com.careercrafter.jobportal.entity.Employer;
import com.careercrafter.jobportal.entity.User;
import com.careercrafter.jobportal.repo.CompanyRepo;
import com.careercrafter.jobportal.repo.EmployerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployerRestControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private EmployerRepo employerRepo;
    @Autowired private CompanyRepo companyRepo;

    private static Long employerId;
    private Company company;

    @BeforeEach
    public void setup() {
        company = new Company();
        company.setCompanyName("ABC Corp");
        company.setIndustry("IT");
        company.setLocation("Chennai");
        companyRepo.save(company);
    }

    @Test @Order(1)
    public void testCreateEmployer() throws Exception {
        Employer emp = new Employer();
        emp.setFullName("Emp A");
        emp.setEmail("emp@example.com");
        emp.setPassword("1234");
        emp.setRole(User.Role.EMPLOYER);
        emp.setPhone("9876543210");
        emp.setDesignation("Manager");
        emp.setCompany(company);

        MvcResult result = mockMvc.perform(post("/api/employers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(emp)))
            .andExpect(status().isOk()).andReturn();

        employerId = objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();
    }

   

    @Test @Order(2)
    public void testUpdateEmployer() throws Exception {
        Employer updated = new Employer();
        updated.setFullName("Updated Emp");
        updated.setEmail("emp@example.com");
        updated.setPassword("1234");
        updated.setRole(User.Role.EMPLOYER);
        updated.setPhone("9999999999");
        updated.setDesignation("Director");
        updated.setCompany(company);

        mockMvc.perform(put("/api/employers/" + employerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fullName").value("Updated Emp"));
    }

    @Test @Order(3)
    public void testDeleteEmployer() throws Exception {
        mockMvc.perform(delete("/api/employers/" + employerId))
            .andExpect(status().isOk());
    }
}
