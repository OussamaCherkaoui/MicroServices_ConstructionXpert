package org.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.project.model.Project;
import org.project.repository.ProjectRepository;
import org.project.service.ProjectService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void testGetAllProjects() {
        List<Project> projectList = new ArrayList<>();
        Project project1 = new Project();
        project1.setName("Project 1");

        Project project2 = new Project();
        project2.setName("Project 2");

        projectList.add(project1);
        projectList.add(project2);

        when(projectRepository.findAll()).thenReturn(projectList);

        List<Project> retrievedProjects = projectService.getAllProjects();
        assertEquals(2, retrievedProjects.size());
        assertEquals("Project 1", retrievedProjects.get(0).getName());
    }
}
