package ru.antonpopov.juniortask.service;

import ru.antonpopov.juniortask.model.Issue;

import java.util.List;

public interface IssueService {

    List<Issue> getAllIssues();

    List<Issue> getIssuesByProjectIdAndUserId(Long projectId, Long userId);

    Issue save(Issue issue);

}
