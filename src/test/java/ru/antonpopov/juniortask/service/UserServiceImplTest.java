package ru.antonpopov.juniortask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.antonpopov.juniortask.dao.UserRepository;
import ru.antonpopov.juniortask.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void getAllUsers() {
        User user = new User();
        user.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        List<User> userList = Arrays.asList(user, user2);

        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    public void save() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void findById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(user, userService.findById(1L));
    }


}