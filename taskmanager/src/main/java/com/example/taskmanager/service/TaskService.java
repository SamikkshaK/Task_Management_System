package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepo.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    public Optional<Task> updateTask(Long id, Task newTask) {
        return taskRepo.findById(id).map(task -> {
            task.setTitle(newTask.getTitle());
            task.setDescription(newTask.getDescription());
            task.setDueDate(newTask.getDueDate());
            task.setPriority(newTask.getPriority());
            task.setStatus(newTask.getStatus());
            return taskRepo.save(task);
        });
    }

    public boolean deleteTask(Long id) {
        return taskRepo.findById(id).map(task -> {
            taskRepo.delete(task);
            return true;
        }).orElse(false);
    }
}
