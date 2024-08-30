package org.ressource.controller;


import org.ressource.exception.RessourceNotFoundException;
import org.ressource.exception.TaskNotFoundException;
import org.ressource.model.Ressource;
import org.ressource.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiRessource")
public class RessourceController {
    @Autowired
    private RessourceService ressourceService;

    @PostMapping("/saveRessource")
    public ResponseEntity<Ressource> createResource(@RequestBody Ressource resource) throws TaskNotFoundException {
        Ressource createdResource = ressourceService.createResource(resource);
        return ResponseEntity.ok(createdResource);
    }

    @GetMapping("/getAllRessources")
    public ResponseEntity<List<Ressource>> getAllResources() {
        return ResponseEntity.ok(ressourceService.getAllResources());
    }

    @GetMapping("/getRessourceById/{id}")
    public ResponseEntity<Ressource> getResourceById(@PathVariable Long id) throws RessourceNotFoundException {
        return ResponseEntity.ok(ressourceService.getResourceById(id));
    }

    @PutMapping("/updateRessource")
    public ResponseEntity<Ressource> updateResource(@RequestBody Ressource ressource) throws RessourceNotFoundException {
        return ResponseEntity.ok(ressourceService.updateResource(ressource));
    }

    @DeleteMapping("/deleteRessource/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        ressourceService.deleteResource(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getRessourceByIdTask/{taskId}")
    public ResponseEntity<List<Ressource>> getResourcesByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(ressourceService.getResourcesByTaskId(taskId));
    }

}
