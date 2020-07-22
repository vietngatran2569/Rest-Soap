package com.example.restdemo2.dto;
import com.example.restdemo2.domain.Person;
import com.example.restdemo2.domain.Task;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class TaskDTO {
    public static ModelMapper modelMapper = new ModelMapper();
    private Long id;
    private String title;
    private String description;
    private int priority;
    private String priorityName;
    private String classTable;
    private Long personId;
    //private Person person;

    public TaskDTO(Task task) {
        BeanUtils.copyProperties(task, this);
        this.personId = task.getPerson().getId();
        task.setPerson(task.getPerson());
        Task.Priority priority = Task.Priority.getPriorityByCode(task.getPriority());
        this.priorityName = priority.getName();
        this.classTable = priority.getClassTable();
    }

    public TaskDTO(Long id, String title, String description, int priority, String priorityName, String classTable, Long personId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.priorityName = priorityName;
        this.classTable = classTable;
        this.personId = personId;
    }
}
