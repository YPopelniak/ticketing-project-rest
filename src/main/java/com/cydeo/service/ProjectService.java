package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.User;

import java.util.List;

public interface ProjectService {
    ProjectDTO getByProjectCode(String code);
    List<ProjectDTO> listAllProjects();
    void save(ProjectDTO dto);
    void update(ProjectDTO dto);
    void delete(String code);
    void complete(String projectCode);

    List<ProjectDTO> listAllProjectDetails();

    List<ProjectDTO> readAllByAssignedManager(User assignedManager);

}
