package org.ressource.service;

import lombok.RequiredArgsConstructor;
import org.ressource.exception.RessourceNotFoundException;
import org.ressource.model.Ressource;
import org.ressource.repository.RessourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RessourceService {
    private final RessourceRepository resourceRepository;
    private static final String taskServiceUrl = "http://Task/apiTask";

    public Ressource createResource(Ressource resource) {

        return resourceRepository.save(resource);
    }

    public List<Ressource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Ressource getResourceById(Long id) throws RessourceNotFoundException {
        return resourceRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);
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
