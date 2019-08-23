package ru.antonpopov.juniortask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.antonpopov.juniortask.model.Project;
import ru.antonpopov.juniortask.service.ProjectService;

import java.util.List;

@RestController
public class ProjectController {

    private Logger logger = LoggerFactory.getLogger(ProjectController.class);
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping("/projects/")
    public List<Project> getAllProjects() {
        logger.info("All projects requested");
        return projectService.getAllProjects();
    }

    @RequestMapping(value = "/projects/save/projectName={projectName}", method = RequestMethod.POST)
    public Project saveProject(@PathVariable String projectName) {
        logger.info("POST recieved to save project with name " + projectName);
        Project project = new Project();
        project.setProjectName(projectName);
        return projectService.save(project);
    }
}
