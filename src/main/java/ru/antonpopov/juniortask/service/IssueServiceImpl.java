package ru.antonpopov.juniortask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonpopov.juniortask.dao.IssueRepository;
import ru.antonpopov.juniortask.dao.ProjectRepository;
import ru.antonpopov.juniortask.dao.UserRepository;
import ru.antonpopov.juniortask.model.Issue;
import ru.antonpopov.juniortask.model.Project;
import ru.antonpopov.juniortask.model.User;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Service
public class IssueServiceImpl implements IssueService {

    private final static Logger logger = LoggerFactory.getLogger(IssueServiceImpl.class);
    private final Lock lock = new ReentrantLock();

    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private IssueRepository issueRepository;

    @Autowired
    public IssueServiceImpl(UserRepository userRepository,
                            ProjectRepository projectRepository,
                            IssueRepository issueRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.issueRepository = issueRepository;
    }


    @Override
    public List<Issue> getAllIssues() {
        List<Issue> issueList = null;
        lock.lock();
        try {
            issueList = issueRepository.findAll();
            if (issueList.size() == 0) {
                throw new RuntimeException();
            }
        } finally {
            lock.unlock();
        }
        return issueList;
    }

    @Override
    public List<Issue> getIssuesByProjectIdAndUserId(Long projectId, Long userId) {
        List<Issue> issuesByProjectAndUser = null;
        lock.lock();
        try {
            issuesByProjectAndUser = issueRepository.findAllByProjectIdAndUserId(projectId, userId);
            if (issuesByProjectAndUser == null) {
                throw new RuntimeException();
            }
        } finally {
            lock.unlock();
        }
        return issuesByProjectAndUser;
    }

    @Override
    public Issue save(Issue issue) {
        lock.lock();
        User user = null;
        Project project = null;
        if (userRepository.findById(issue.getUser().getId()).isPresent()) {
            user = userRepository.findById(issue.getUser().getId()).get();
        }
        if (projectRepository.findById(issue.getProject().getId()).isPresent()) {
            project = projectRepository.findById(issue.getProject().getId()).get();
        }
        issue = issueRepository.save(issue);
        user.getIssueList().add(issue);
        project.getIssueList().add(issue);
        userRepository.save(user);
        projectRepository.save(project);
        lock.unlock();
        return issue;
    }
}
