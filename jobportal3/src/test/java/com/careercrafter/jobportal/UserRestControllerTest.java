package com.careercrafter.jobportal;

import com.careercrafter.jobportal.entity.User;
import com.careercrafter.jobportal.entity.User.Role;
import com.careercrafter.jobportal.repo.UserRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepo userRepo;

    private static Long userId;

    @Test
    @Order(1)
    void testCreateUser() {
        User user = new User();
        user.setFullName("Test User");
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        user.setRole(Role.JOB_SEEKER);

        ResponseEntity<User> response = restTemplate.postForEntity("/api/users", user, User.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();

        User created = response.getBody();
        userId = created.getId();

        assertThat(created.getEmail()).isEqualTo("testuser@example.com");
    }

    @Test
    @Order(2)
    void testGetUserById() {
        ResponseEntity<User> response = restTemplate.getForEntity("/api/users/" + userId, User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(userId);
    }

    @Test
    @Order(3)
    void testUpdateUser() {
        User update = new User();
        update.setFullName("Updated User");
        update.setEmail("updateduser@example.com");
        update.setPassword("newpassword");
        update.setRole(Role.EMPLOYER);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> entity = new HttpEntity<>(update, headers);

        ResponseEntity<User> response = restTemplate.exchange("/api/users/" + userId, HttpMethod.PUT, entity, User.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getFullName()).isEqualTo("Updated User");
    }

    @Test
    @Order(4)
    void testDeleteUser() {
        restTemplate.delete("/api/users/" + userId);
        boolean exists = userRepo.existsById(userId);
        assertThat(exists).isFalse();
    }
}
