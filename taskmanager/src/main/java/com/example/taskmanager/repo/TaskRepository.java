package com.example.taskmanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

