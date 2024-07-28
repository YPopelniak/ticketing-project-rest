package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.ResponseWrapper;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final UserService userService;

    public TaskController(TaskService taskService, ProjectService projectService, UserService userService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.userService = userService;
    }


    @GetMapping()
    public ResponseEntity<ResponseWrapper> getTasks() {

        List<TaskDTO> taskDTOList = taskService.listAllTasks();
        return ResponseEntity.ok(new ResponseWrapper("Task are successfully retrieved",taskDTOList, HttpStatus.OK));

    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> getTaskBuId(@PathVariable("taskId") Long taskId) {
        TaskDTO taskDto =  taskService.findById(taskId);
        return ResponseEntity.ok(new ResponseWrapper("Task are successfully retrieved",taskDto, HttpStatus.OK));
    }

    @PostMapping()
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody TaskDTO taskDTO) {
        taskService.save(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Task is successfully created",HttpStatus.CREATED));

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully deleted",HttpStatus.OK));


    }

        @PutMapping()
    public ResponseEntity<ResponseWrapper> updateTask(@RequestBody TaskDTO taskDTO) {
         taskService.update(taskDTO);
         return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated",HttpStatus.OK));
    }

    @GetMapping("/employee/pending-tasks")
    public ResponseEntity<ResponseWrapper> employeePendingTasks() {
        List<TaskDTO> taskDTOList = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved",taskDTOList, HttpStatus.OK));
    }

    @PutMapping("/employee/update/")
    public ResponseEntity<ResponseWrapper> employeeUpdateTask(@RequestBody TaskDTO task) {
        taskService.updateStatus(task);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated", HttpStatus.OK));

    }

    @GetMapping("/employee/archive")
    public ResponseEntity<ResponseWrapper> employeeArchivedTasks() {
       List<TaskDTO> taskDTOS = taskService.listAllTasksByStatus(Status.COMPLETE);
       return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved",taskDTOS, HttpStatus.OK));

    }

}


