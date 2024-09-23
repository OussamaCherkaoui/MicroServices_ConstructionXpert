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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(0, 5, sort);
        Page<Project> retrievedProjects = projectService.getAllProjects(pageable);
        assertEquals(2, retrievedProjects.getTotalElements());
        assertEquals("Project 1", retrievedProjects.getContent().get(0).getName());
    }
}
