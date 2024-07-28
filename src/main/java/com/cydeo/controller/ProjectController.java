package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.ResponseWrapper;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<ResponseWrapper> getProjects(@PathVariable("role") String role) {

       // List<UserDTO> userDTOList = userService.listAllByRole(role);
        List<ProjectDTO> projectDetails = projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Project are successfully retrieved",projectDetails, HttpStatus.OK));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseWrapper> getProjectsByCode(@PathVariable("code") String code) {

        ProjectDTO projectByCode = projectService.getByProjectCode(code);
        return ResponseEntity.ok(new ResponseWrapper("Project are successfully retrieved",projectByCode, HttpStatus.OK));
    }


    @PostMapping()
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project) {
        projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Project is successfully created",HttpStatus.CREATED));

    }


    @DeleteMapping("/{projectCode}")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("project") String project) {
        projectService.delete(project);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully deleted",HttpStatus.OK));

    }

    @GetMapping("/manager/project-status")
    public ResponseEntity<ResponseWrapper> getProjectByManager() {
        List<ProjectDTO> listAllProjectsDetails = projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved",listAllProjectsDetails, HttpStatus.OK));


    }

    @PutMapping("/manager/complete/{projectCode}")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String code) {
        projectService.complete(code);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully completed",HttpStatus.OK));
    }

}

