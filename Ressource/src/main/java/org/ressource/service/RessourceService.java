package org.ressource.service;

import lombok.RequiredArgsConstructor;
import org.ressource.exception.RessourceNotFoundException;
import org.ressource.exception.TaskNotFoundException;
import org.ressource.model.Ressource;
import org.ressource.repository.RessourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RessourceService {
    private final RessourceRepository resourceRepository;
    private static final String taskServiceUrl = "http://Task/apiTask";
    private final RestTemplate restTemplate;

    public Ressource createResource(Ressource resource) throws TaskNotFoundException {
        validateTaskExists(resource.getTaskId());
        return resourceRepository.save(resource);
    }

    public List<Ressource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Ressource getResourceById(Long id) throws RessourceNotFoundException {
        return resourceRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);
    }
    private void validateTaskExists(Long taskId) throws TaskNotFoundException {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }
        String url = taskServiceUrl + "/isExist/" + taskId ;
        Boolean exists = restTemplate.getForObject(url, Boolean.class);
        if (exists == null || !exists) {
            throw new TaskNotFoundException();
        }
    }
    public Ressource updateResource(Ressource ressource){
        return resourceRepository.save(ressource);
    }

    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

    public List<Ressource> getResourcesByTaskId(Long taskId) {
        return resourceRepository.findByTaskId(taskId);
    }
}
