package org.project.controller;

import org.project.exception.ProjectNotFoundException;
import org.project.model.Project;
import org.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiProject")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveProject")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getAll")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateProject")
    public Project updateProject(@RequestBody Project projectDetails) {
        System.out.println(projectDetails.getName());
        System.out.println(projectDetails.getBudget());
        return projectService.updateProject(projectDetails);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteProject/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/isExist/{id}")
    public ResponseEntity<Boolean> existProject(@PathVariable("id") Long id) {
        boolean exist = projectService.existProject(id);
        return ResponseEntity.ok(exist);
    }
}
