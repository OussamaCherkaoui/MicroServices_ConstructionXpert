package org.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.task.exception.ProjectNotFoundException;
import org.task.exception.TaskNotFoundException;
import org.task.model.Task;
import org.task.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final RestTemplate restTemplate;
    private  final TaskRepository taskRepository;

    private static final String projectServiceUrl = "http://project/apiProject/";

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

    public List<Task> getTasksByProjectId(Long projectId) throws ProjectNotFoundException {
        validateProjectExists(projectId);
        return taskRepository.findByProjectId(projectId);
    }

    private void validateProjectExists(Long projectId) throws ProjectNotFoundException {
        if (projectId == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }
        String url = projectServiceUrl + "/isExist/" + projectId ;
        Boolean exists = restTemplate.getForObject(url, Boolean.class);
        if (exists == null || !exists) {
            throw new ProjectNotFoundException();
        }
    }


    public Boolean existTask(Long id) {
        return taskRepository.findById(id).isPresent();
    }
}
