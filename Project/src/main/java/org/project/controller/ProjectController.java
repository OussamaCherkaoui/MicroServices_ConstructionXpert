package org.project.controller;

import org.project.exception.ProjectNotFoundException;
import org.project.model.Project;
import org.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/getAll/{page}/{ascending}")
    public Page<Project> getAllProjects(@PathVariable int page,
                                        @PathVariable boolean ascending) {
        Sort sort = ascending ? Sort.by("id").ascending() : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, 5, sort);
        return projectService.getAllProjects(pageable);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getProjectsByName/{nameSearched}")
    public Page<Project> getProjectsByName(@PathVariable String nameSearched) {
        System.out.println(nameSearched);
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0, 5, sort);
        return projectService.getProjectsByName(nameSearched,pageable);
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
