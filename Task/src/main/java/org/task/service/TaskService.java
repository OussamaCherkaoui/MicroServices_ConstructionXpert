package org.task.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.task.Project.ProjectRest;
import org.task.exception.ProjectNotFoundException;
import org.task.exception.TaskNotFoundException;
import org.task.model.Task;
import org.task.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final ProjectRest projectRest;
    private  final TaskRepository taskRepository;

    public Task createTask(Task task) throws ProjectNotFoundException {
        validateProjectExists(task.getProjectId());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) throws TaskNotFoundException {
        return taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Page<Task> getTasksByProjectId(Long projectId, Pageable pageable) throws ProjectNotFoundException {
        validateProjectExists(projectId);
        return taskRepository.findByProjectId(projectId,pageable);
    }

    private void validateProjectExists(Long projectId) throws ProjectNotFoundException {
        if (projectId == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        Boolean exists = projectRest.isExist(projectId);
        if (exists == null || !exists) {
            throw new ProjectNotFoundException();
        }
    }


    public Boolean existTask(Long id) {
        return taskRepository.findById(id).isPresent();
    }
}
