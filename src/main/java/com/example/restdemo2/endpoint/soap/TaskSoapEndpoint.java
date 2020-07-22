package com.example.restdemo2.endpoint.soap;

import com.company.GetTasksByIdRequest;
import com.company.GetTasksByIdResponse;
import com.company.PersonSoap;
import com.company.TaskSoap;
import com.example.restdemo2.domain.Task;
import com.example.restdemo2.dto.TaskDTO;
import com.example.restdemo2.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Endpoint
public class TaskSoapEndpoint {
    @Autowired
    TaskService taskService;

    private static final String NAMESPACE_URI = "http://company.com";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetTasksByIdRequest")
    @ResponsePayload
    public GetTasksByIdResponse taskResponseList(@RequestPayload GetTasksByIdRequest getTasksByIdRequest) {
        GetTasksByIdResponse getTasksByIdResponse = new GetTasksByIdResponse();
        List<TaskSoap> taskSoaps = new ArrayList<>();
        List<Task> taskListss = taskService.taskList(getTasksByIdRequest.getPersonId());
        List<Task> taskList = taskService.getAllTasks(getTasksByIdRequest.getPersonId());
        taskList.sort((o1, o2) -> (o1.getPriority() > o2.getPriority()) ? -1 : 1);
        for (Task task : taskList) {
            TaskSoap taskSoap = new TaskSoap();
            PersonSoap personSoap = new PersonSoap();
            BeanUtils.copyProperties(task.getPerson(), personSoap);
            taskSoap.setPerson(personSoap);
            BeanUtils.copyProperties(new TaskDTO(task), taskSoap);
            taskSoaps.add(taskSoap);
        }
        getTasksByIdResponse.getTask().addAll(taskSoaps);
        return getTasksByIdResponse;
    }

}
