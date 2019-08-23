package ru.antonpopov.juniortask.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.antonpopov.juniortask.service.IssueService;
import ru.antonpopov.juniortask.service.ProjectService;
import ru.antonpopov.juniortask.service.UserService;
@RestController
public class MainController {

    private UserService userService;
    private ProjectService projectService;
    private IssueService issueService;

    public MainController(UserService userService, ProjectService projectService, IssueService issueService) {
        this.userService = userService;
        this.projectService = projectService;
        this.issueService = issueService;
    }

    @RequestMapping("/")
    public String getMain() {
        return "sup";
    }

}
