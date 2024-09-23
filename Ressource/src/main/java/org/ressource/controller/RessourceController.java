package org.ressource.controller;


import org.ressource.exception.RessourceNotFoundException;
import org.ressource.exception.TaskNotFoundException;
import org.ressource.model.Ressource;
import org.ressource.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiRessource")
public class RessourceController {
    @Autowired
    private RessourceService ressourceService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveRessource")
    public ResponseEntity<Ressource> createResource(@RequestBody Ressource resource) throws TaskNotFoundException {
        Ressource createdResource = ressourceService.createResource(resource);
        return ResponseEntity.ok(createdResource);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getAllRessources")
    public ResponseEntity<List<Ressource>> getAllResources() {
        return ResponseEntity.ok(ressourceService.getAllResources());
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getRessourceById/{id}")
    public ResponseEntity<Ressource> getResourceById(@PathVariable Long id) throws RessourceNotFoundException {
        return ResponseEntity.ok(ressourceService.getResourceById(id));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateRessource")
    public ResponseEntity<Ressource> updateResource(@RequestBody Ressource ressource) throws RessourceNotFoundException {
        return ResponseEntity.ok(ressourceService.updateResource(ressource));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteRessource/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        ressourceService.deleteResource(id);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping("/getRessourceByIdTask/{taskId}/{page}/{ascending}")
    public ResponseEntity<Page<Ressource>> getResourcesByTaskId(@PathVariable Long taskId,
                                                                @PathVariable int page,
                                                                @PathVariable boolean ascending) throws TaskNotFoundException {
        Sort sort = ascending ? Sort.by("id").ascending() : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, 5, sort);
        return ResponseEntity.ok(ressourceService.getResourcesByTaskId(taskId,pageable));
    }

}
