package ru.antonpopov.juniortask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.antonpopov.juniortask.controller.IssueController;
import ru.antonpopov.juniortask.controller.ProjectController;
import ru.antonpopov.juniortask.controller.UserController;
import ru.antonpopov.juniortask.dao.IssueRepository;
import ru.antonpopov.juniortask.dao.ProjectRepository;
import ru.antonpopov.juniortask.dao.UserRepository;
import ru.antonpopov.juniortask.service.IssueService;
import ru.antonpopov.juniortask.service.ProjectService;
import ru.antonpopov.juniortask.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JuniorTaskApplicationTests {

    @MockBean
    private ProjectRepository projectRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private IssueRepository issueRepository;
    @MockBean
    private IssueService issueService;
    @MockBean
    private ProjectService projectService;
    @MockBean
    private UserService userService;
    @MockBean
    private IssueController issueController;
    @MockBean
    private ProjectController projectController;
    @MockBean
    private UserController userController;


    @Test
    public void contextLoads() {
    }

}
