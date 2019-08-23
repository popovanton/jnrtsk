package ru.antonpopov.juniortask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.antonpopov.juniortask.model.Issue;
import ru.antonpopov.juniortask.model.Project;
import ru.antonpopov.juniortask.model.User;
import ru.antonpopov.juniortask.service.IssueService;
import ru.antonpopov.juniortask.service.ProjectService;
import ru.antonpopov.juniortask.service.UserService;

import java.util.List;

@RestController
public class IssueController {

    private Logger logger = LoggerFactory.getLogger(IssueController.class);
    private IssueService issueService;
    private UserService userService;
    private ProjectService projectService;

    public IssueController(IssueService issueService, UserService userService, ProjectService projectService) {
        this.issueService = issueService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @RequestMapping("/issues/")
    public List<Issue> getAllIssues() {
        logger.info("All issues requested");
        List<Issue> issueList = issueService.getAllIssues();

        return issueList;
    }

    @RequestMapping("/issues/projectId={projectId}&userId={userId}")
    public List<Issue> getIssuesByProjectIdAndUserId(@PathVariable Long projectId,
                                                     @PathVariable Long userId) {
        logger.info("Issues requested for userID= " + userId + " and projectID=" + projectId);
        return issueService.getIssuesByProjectIdAndUserId(userId, projectId);
    }

    @RequestMapping(value = "/issues/save/userId={userId}&projectId={projectId}&issueText={issueText}",
            method = RequestMethod.POST)
    public Issue saveIssue(@PathVariable Long userId,
                           @PathVariable Long projectId,
                           @PathVariable String issueText) {
        logger.info("POST method with userID=" + userId + ", projectID=" + projectId + " and issueText='" + issueText + "'");
        User user = userService.findById(userId);
        Project project = projectService.findById(projectId);

        if (user != null && project != null) {
            Issue issue = new Issue();
            issue.setProject(project);
            issue.setUser(user);
            issue.setIssueText(issueText);
            return issueService.save(issue);
        }
        logger.error("No such user=" + userId + " or project=" + projectId + " found");
        return null;
    }

}
