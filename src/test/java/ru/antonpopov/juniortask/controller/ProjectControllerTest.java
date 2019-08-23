package ru.antonpopov.juniortask.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.antonpopov.juniortask.model.Project;
import ru.antonpopov.juniortask.service.ProjectService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    public void getAllProjects() throws Exception {
        Project project = new Project();
        project.setId(1L);
        project.setProjectName("testProject1");
        Project project1 = new Project();
        project1.setId(2L);
        project1.setProjectName("testProject2");
        when(projectService.getAllProjects()).thenReturn(Arrays.asList(project, project1));
        this.mockMvc.perform(get("/projects/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"projectName\":\"testProject1\"},{\"id\":2,\"projectName\":\"testProject2\"}]"));
    }

    @Test
    public void saveProject() throws Exception {
        Project project = new Project();
        project.setProjectName("testProject");
        project.setId(1L);
        when(projectService.save(project)).thenReturn(project);
        this.mockMvc.perform(post("/projects/save/projectName=testProject")).andExpect(status().isOk());
    }
}