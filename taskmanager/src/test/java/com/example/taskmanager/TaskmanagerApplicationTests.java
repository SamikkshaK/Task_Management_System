package com.example.taskmanager;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repo.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TaskRepository taskRepository;

    private Task existingTask;

    @BeforeEach
    void setup() {
        taskRepository.deleteAll(); 

        existingTask = new Task();
        existingTask.setTitle("Sample Task");
        existingTask.setDescription("For testing GET, PUT, DELETE");
        existingTask.setDueDate(new Date());
        existingTask.setPriority("Medium");
        existingTask.setStatus("Pending");

        existingTask = taskRepository.save(existingTask); 
    }

    @Test
    void testCreateTask() {
        Task newTask = new Task();
        newTask.setTitle("Created Task");
        newTask.setDescription("Created via POST");
        newTask.setDueDate(new Date());
        newTask.setPriority("High");
        newTask.setStatus("In Progress");

        ResponseEntity<Task> response = restTemplate.postForEntity("/tasks", newTask, Task.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitle()).isEqualTo("Created Task");
    }

    @Test
    void testGetAllTasks() {
        ResponseEntity<Task[]> response = restTemplate.getForEntity("/tasks", Task[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void testGetTaskById() {
        ResponseEntity<Task> response = restTemplate.getForEntity("/tasks/" + existingTask.getTaskId(), Task.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitle()).isEqualTo("Sample Task");
    }

    @Test
    void testUpdateTask() {
        Task updated = new Task();
        updated.setTitle("Updated Title");
        updated.setDescription("Updated description");
        updated.setDueDate(new Date());
        updated.setPriority("Low");
        updated.setStatus("Completed");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Task> entity = new HttpEntity<>(updated, headers);

        ResponseEntity<Task> response = restTemplate.exchange(
                "/tasks/" + existingTask.getTaskId(),
                HttpMethod.PUT,
                entity,
                Task.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTitle()).isEqualTo("Updated Title");
        assertThat(response.getBody().getStatus()).isEqualTo("Completed");
    }

    @Test
    void testDeleteTask() {
        ResponseEntity<Void> response = restTemplate.exchange(
                "/tasks/" + existingTask.getTaskId(),
                HttpMethod.DELETE,
                null,
                Void.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Optional<Task> deleted = taskRepository.findById(existingTask.getTaskId());
        assertThat(deleted).isEmpty();
    }
}
