package org.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.task.exception.ProjectNotFoundException;
import org.task.exception.TaskNotFoundException;
import org.task.model.Task;
import org.task.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiTask")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveTask")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws ProjectNotFoundException {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getAllTask")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok( taskService.getAllTasks());
    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable Long id) throws TaskNotFoundException {
        Optional<Task> task = Optional.ofNullable(taskService.getTaskById(id));
        return ResponseEntity.ok(task);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateTask")
    public ResponseEntity<Task> updateTask(@RequestBody Task taskDetails) throws TaskNotFoundException {
        return ResponseEntity.ok( taskService.updateTask(taskDetails));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getTaskByIdProject/{projectId}/{page}/{ascending}")
    public ResponseEntity<Page<Task>> getTasksByProjectId(@PathVariable Long projectId,
                                                          @PathVariable int page,
                                                          @PathVariable boolean ascending) throws ProjectNotFoundException {
        Sort sort = ascending ? Sort.by("id").ascending() : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, 5, sort);
        return ResponseEntity.ok(taskService.getTasksByProjectId(projectId,pageable));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/isExist/{id}")
    public ResponseEntity<Boolean> existTask(@PathVariable("id") Long id) {
        boolean exists = taskService.existTask(id);
        return ResponseEntity.ok(exists);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getTasksByStatus/{idProject}/{status}")
    public Page<Task> getTaskByStatus(@PathVariable Long idProject,@PathVariable String status) throws ProjectNotFoundException {
        System.out.println(status);
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0, 5, sort);
        return taskService.getTasksByStatus(idProject,status,pageable);
    }
}
