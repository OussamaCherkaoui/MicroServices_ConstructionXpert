package org.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/getTaskByIdProject/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId) throws ProjectNotFoundException {
        return ResponseEntity.ok(taskService.getTasksByProjectId(projectId));
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/isExist/{id}")
    public ResponseEntity<Boolean> existTask(@PathVariable("id") Long id) {
        boolean exists = taskService.existTask(id);
        return ResponseEntity.ok(exists);
    }
}
