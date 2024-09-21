package org.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.task.model.Task;
import org.task.repository.TaskRepository;
import org.task.service.TaskService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskApplicationTests {

    @MockBean
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllTasks_shouldReturnAllTasks() {

        List<Task> tasks = List.of(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> listTasks = taskService.getAllTasks();

        assertNotNull(listTasks);
        assertFalse(listTasks.isEmpty());
    }

}
