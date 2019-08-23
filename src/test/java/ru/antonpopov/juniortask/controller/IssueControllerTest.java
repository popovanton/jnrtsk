package ru.antonpopov.juniortask.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.antonpopov.juniortask.model.Issue;
import ru.antonpopov.juniortask.model.Project;
import ru.antonpopov.juniortask.model.User;
import ru.antonpopov.juniortask.service.IssueService;
import ru.antonpopov.juniortask.service.ProjectService;
import ru.antonpopov.juniortask.service.UserService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(IssueController.class)
public class IssueControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssueService service;
    @MockBean
    private UserService userService;
    @MockBean
    private ProjectService projectService;


    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(service.getAllIssues()).thenReturn(Arrays.asList(new Issue()));
        this.mockMvc.perform(get("/issues/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"project\":null,\"user\":null,\"issueText\":null}]"));
    }

    @Test
    public void getIssuesByProjectIdAndUserId() throws Exception {
        User user = new User();
        user.setUserName("testUser");
        user.setId(1L);
        Project project = new Project();
        project.setId(1L);
        project.setProjectName("testProject");
        Issue testIssue = new Issue();
        testIssue.setIssueText("testIssue");
        testIssue.setUser(user);
        testIssue.setProject(project);
        testIssue.setId(8L);
        user.getIssueList().add(testIssue);
        project.getIssueList().add(testIssue);
        when(service.getIssuesByProjectIdAndUserId(1L, 1L)).thenReturn(Arrays.asList(testIssue));
        this.mockMvc.perform(get("/issues/projectId=1&userId=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":8,\"project\":{\"id\":1,\"projectName\":\"testProject\"},\"user\":{\"id\":1,\"userName\":\"testUser\"},\"issueText\":\"testIssue\"}]"));
    }

    @Test
    public void saveIssue() throws Exception {
        User user = new User();
        user.setUserName("testUser");
        user.setId(1L);
        Project project = new Project();
        project.setId(1L);
        project.setProjectName("testProject");
        Issue testIssue = new Issue();
        testIssue.setIssueText("testIssue");
        testIssue.setUser(user);
        testIssue.setProject(project);
        testIssue.setId(8L);
        user.getIssueList().add(testIssue);
        project.getIssueList().add(testIssue);
        when(service.save(testIssue)).thenReturn(testIssue);
        this.mockMvc.perform(post("/issues/save/userId=1&projectId=1&issueText=testIssue")).andDo(print()).andExpect(status().isOk())
                .andExpect(status().isOk());
    }
}