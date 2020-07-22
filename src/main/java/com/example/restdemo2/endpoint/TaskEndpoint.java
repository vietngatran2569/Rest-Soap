package com.example.restdemo2.endpoint;

import com.example.restdemo2.domain.Task;
import com.example.restdemo2.dto.TaskDTO;
import com.example.restdemo2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api_v1/task")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class TaskEndpoint {

    @Autowired
    TaskService taskService;

    @GetMapping("/{personId}")
    public List<TaskDTO> getAllTasksByPersonId(@PathVariable("personId") Long personId) {
        List<Task> tasks = taskService.getAllTasks(personId);
        return tasks.stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTask(@Valid @RequestBody Task task) {
        Task taskResult = taskService.save(task);
        return ResponseEntity.ok(new TaskDTO(taskResult));
    }

    /*@PostMapping("/valid")
    public ResponseEntity<?> postTask(@Valid Task task) {
        return ResponseEntity.ok(task);
    }*/

}
