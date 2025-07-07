package com.careercrafter.jobportal;

import com.careercrafter.jobportal.entity.*;
import com.careercrafter.jobportal.repo.*;
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
public class ApplicationRestControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Autowired private JobRepo jobRepo;
    @Autowired private JobSeekerRepo jobSeekerRepo;
    @Autowired private ApplicationRepo applicationRepo;

    private static Long applicationId;
    private Job job;
    private JobSeeker seeker;

    @BeforeEach
    public void setup() {
        job = new Job();
        job.setTitle("Integration Job");
        job.setDescription("Testing description");
        job.setLocation("Remote");
        job.setSalary("12000");
        job = jobRepo.save(job);

        seeker = new JobSeeker();
        seeker.setFullName("Test Seeker");
        seeker.setEmail("testseeker@example.com");
        seeker.setPassword("password");
        seeker.setRole(User.Role.JOB_SEEKER);
        seeker.setEducation("MCA");
        seeker.setExperience("3 years");
        seeker.setResumeLink("http://resume.test");
        seeker = jobSeekerRepo.save(seeker);
    }

    @Test
    @Order(1)
    public void testCreateApplication() throws Exception {
        Application app = new Application();
        app.setStatus("Applied");
        app.setJob(job);
        app.setJobSeeker(seeker);

        MvcResult result = mockMvc.perform(post("/api/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(app)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Applied"))
                .andReturn();

        // ✅ Extract correct field name "id" from JSON
        applicationId = objectMapper.readTree(result.getResponse().getContentAsString()).get("id").asLong();
    }

    @Test
    @Order(2)
    public void testGetApplicationById() throws Exception {
        mockMvc.perform(get("/api/applications/" + applicationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Applied"))
                .andExpect(jsonPath("$.job.title").value("Integration Job"))
                .andExpect(jsonPath("$.jobSeeker.email").value("testseeker@example.com"));
    }

    @Test
    @Order(3)
    public void testUpdateApplication() throws Exception {
        Application updatedApp = new Application();
        updatedApp.setStatus("Reviewed");
        updatedApp.setJob(job);
        updatedApp.setJobSeeker(seeker);

        mockMvc.perform(put("/api/applications/" + applicationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedApp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Reviewed"));
    }

    @Test
    @Order(4)
    public void testDeleteApplication() throws Exception {
        mockMvc.perform(delete("/api/applications/" + applicationId))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/applications/" + applicationId))
                .andExpect(status().is4xxClientError()); // Not Found after delete
    }

    @AfterAll
    public static void cleanup(@Autowired ApplicationRepo applicationRepo,
                                @Autowired JobRepo jobRepo,
                                @Autowired JobSeekerRepo jobSeekerRepo) {
        applicationRepo.deleteAll();
        jobRepo.deleteAll();
        jobSeekerRepo.deleteAll();
    }
}
