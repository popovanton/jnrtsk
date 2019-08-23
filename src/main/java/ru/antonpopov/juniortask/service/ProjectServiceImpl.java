package ru.antonpopov.juniortask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.antonpopov.juniortask.dao.ProjectRepository;
import ru.antonpopov.juniortask.model.Project;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final Lock lock = new ReentrantLock();

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projectList = null;
        lock.lock();
        try {
            projectList = projectRepository.findAll();
        } finally {
            lock.unlock();
        }
        return projectList;
    }

    @Override
    public Project save(Project project) {
        Project savedProject = null;
        if (projectRepository.findProjectByProjectName(project.getProjectName()) == null) {
            lock.lock();
            try {
                savedProject = projectRepository.save(project);
            } finally {
                lock.unlock();
            }
        }
        return savedProject;
    }

    @Override
    public Project findById(Long id) {
        Project project = null;
        lock.lock();
        try {
            project = projectRepository.findById(id).orElse(null);
        } finally {
            lock.unlock();
        }
        return project;
    }
}
