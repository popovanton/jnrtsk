package ru.antonpopov.juniortask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.antonpopov.juniortask.dao.IssueRepository;
import ru.antonpopov.juniortask.dao.ProjectRepository;
import ru.antonpopov.juniortask.dao.UserRepository;
import ru.antonpopov.juniortask.model.Issue;
import ru.antonpopov.juniortask.model.Project;
import ru.antonpopov.juniortask.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class IssueServiceImplTest {

    @Mock
    private IssueRepository issueRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private IssueServiceImpl issueService;

    @Test
    public void getAllIssues() {
        Issue issue = new Issue();
        Issue issue1 = new Issue();
        List<Issue> issueList = Arrays.asList(issue, issue1);
        when(issueRepository.findAll()).thenReturn(issueList);

        assertEquals(issueService.getAllIssues().size(), 2);
    }

    @Test
    public void getIssuesByProjectIdAndUserId() {
        User user2 = new User();
        Project project2 = new Project();
        user2.setId(2L);
        project2.setId(2L);
        Issue issue2 = new Issue();

        List<Issue> issueList = Arrays.asList(issue2);

        when(issueRepository.findAllByProjectIdAndUserId(2L, 2L)).thenReturn(issueList);

        assertEquals(1, issueService.getIssuesByProjectIdAndUserId(2L, 2L).size());

    }

    @Test
    public void save() {
        User user2 = new User();
        Project project2 = new Project();
        user2.setId(2L);
        project2.setId(2L);
        Issue issue2 = new Issue();
        issue2.setUser(user2);
        issue2.setProject(project2);

        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(projectRepository.findById(2L)).thenReturn(Optional.of(project2));
        when(issueRepository.save(issue2)).thenReturn(issue2);

        issueService.save(issue2);
        verify(issueRepository, times(1)).save(issue2);
    }
}