package com.example.restdemo2.service;

import com.example.restdemo2.domain.Task;
import com.example.restdemo2.dto.TaskDTO;
import com.example.restdemo2.endpoint.rest.RESTResponse;
import com.example.restdemo2.repository.TaskRepository;
import com.example.restdemo2.specification.TaskSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    // Get All Task By Person Id
    public List<Task> getAllTasks(long personId) {
        TaskSpecification specification = TaskSpecification.spec();
        Optional.of(personId).ifPresent(s -> specification.byPersonId(personId));
        return taskRepository.findAll(specification.build());
    }

    // Save Task
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> taskList(long id){
        return taskRepository.getAllByPersonId(id);
    }
}
