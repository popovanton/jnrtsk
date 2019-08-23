package ru.antonpopov.juniortask.service;

import ru.antonpopov.juniortask.model.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project save(Project project);

    Project findById(Long id);
}
