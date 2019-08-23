package ru.antonpopov.juniortask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.antonpopov.juniortask.dao.ProjectRepository;
import ru.antonpopov.juniortask.model.Project;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void getAllProjects() {
        Project project = new Project();
        project.setId(1L);
        Project project1 = new Project();
        project1.setId(2L);
        List<Project> projectList = Arrays.asList(project, project1);

        when(projectRepository.findAll()).thenReturn(projectList);
        assertEquals(2, projectService.getAllProjects().size());
    }

    @Test
    public void save() {
        Project project = new Project();

        when(projectRepository.save(project)).thenReturn(project);
        assertEquals(project, projectService.save(project));
    }

    @Test
    public void findById() {
        Project project = new Project();
        project.setId(1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        assertEquals(project, projectService.findById(1L));
    }
}