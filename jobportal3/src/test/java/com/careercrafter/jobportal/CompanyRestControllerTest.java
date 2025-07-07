package com.careercrafter.jobportal;

import com.careercrafter.jobportal.entity.Company;
import com.careercrafter.jobportal.repo.CompanyRepo;
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
public class CompanyRestControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private CompanyRepo companyRepo;

    private static Long companyId;

    @Test @Order(1)
    public void testCreateCompany() throws Exception {
        Company company = new Company();
        company.setCompanyName("OpenAI Ltd");
        company.setLocation("San Francisco");
        company.setIndustry("AI Research");

        MvcResult result = mockMvc.perform(post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(company)))
            .andExpect(status().isOk())
            .andReturn();

        companyId = objectMapper.readTree(result.getResponse().getContentAsString()).get("companyId").asLong();
    }

    @Test @Order(2)
    public void testGetCompanyById() throws Exception {
        mockMvc.perform(get("/api/companies/" + companyId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.companyName").value("OpenAI Ltd"));
    }

    @Test @Order(3)
    public void testUpdateCompany() throws Exception {
        Company updated = new Company();
        updated.setCompanyName("OpenAI Global");
        updated.setLocation("USA");
        updated.setIndustry("Advanced AI");

        mockMvc.perform(put("/api/companies/" + companyId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.industry").value("Advanced AI"));
    }

    @Test @Order(4)
    public void testDeleteCompany() throws Exception {
        mockMvc.perform(delete("/api/companies/" + companyId))
            .andExpect(status().isOk());
    }
}
