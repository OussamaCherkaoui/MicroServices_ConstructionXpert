package org.ressource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.ressource.model.Ressource;
import org.ressource.repository.RessourceRepository;
import org.ressource.service.RessourceService;
import org.ressource.task.TaskRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@SpringBootTest
class RessourceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RessourceService resourceService;

    @MockBean
    private RessourceRepository resourceRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllResources_shouldReturnAllResources() {

        Ressource ressource = new Ressource();
        when(resourceRepository.findAll()).thenReturn(List.of(ressource));

        // Act
        List<Ressource> ressources = resourceService.getAllResources();

        // Assert
        assertNotNull(ressources);
        assertFalse(ressources.isEmpty());
    }

}
