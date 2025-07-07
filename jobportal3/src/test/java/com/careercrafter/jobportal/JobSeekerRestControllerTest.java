package com.careercrafter.jobportal;

import com.careercrafter.jobportal.entity.JobSeeker;
import com.careercrafter.jobportal.entity.User;
import com.careercrafter.jobportal.repo.JobSeekerRepo;
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
public class JobSeekerRestControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private JobSeekerRepo jobSeekerRepo;

    private static Long seekerId;

    @Test @Order(1)
    public void testCreateJobSeeker() throws Exception {
        JobSeeker seeker = new JobSeeker();
        seeker.setFullName("Samikksha");
        seeker.setEmail("sam@example.com");
        seeker.setPassword("1234");
        seeker.setRole(User.Role.JOB_SEEKER);
        seeker.setEducation("B.Tech");
        seeker.setExperience("1 year");
        seeker.setResumeLink("http://resume.test");

        MvcResult result = mockMvc.perform(post("/api/seekers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(seeker)))
            .andExpect(status().isOk()).andReturn();

        seekerId = objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();
    }

    @Test @Order(2)
    public void testGetJobSeekerById() throws Exception {
        mockMvc.perform(get("/api/seekers/" + seekerId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fullName").value("Samikksha"));
    }

    @Test @Order(3)
    public void testUpdateJobSeeker() throws Exception {
        JobSeeker updated = new JobSeeker();
        updated.setFullName("Samikksha K");
        updated.setEmail("sam@example.com");
        updated.setPassword("newpass");
        updated.setRole(User.Role.JOB_SEEKER);
        updated.setEducation("MCA");
        updated.setExperience("2 years");
        updated.setResumeLink("http://resume.updated");

        mockMvc.perform(put("/api/seekers/" + seekerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.education").value("MCA"));
    }

    @Test @Order(4)
    public void testDeleteJobSeeker() throws Exception {
        mockMvc.perform(delete("/api/seekers/" + seekerId))
            .andExpect(status().isOk());
    }
}
