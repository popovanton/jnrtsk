package ru.antonpopov.juniortask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonpopov.juniortask.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findProjectByProjectName(String projectName);
}
