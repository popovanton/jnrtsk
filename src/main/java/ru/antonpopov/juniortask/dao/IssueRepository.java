package ru.antonpopov.juniortask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonpopov.juniortask.model.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findAllByProjectIdAndUserId(Long projectId, Long userId);
}
