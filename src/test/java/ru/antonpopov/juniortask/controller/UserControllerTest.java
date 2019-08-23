package ru.antonpopov.juniortask.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.antonpopov.juniortask.model.User;
import ru.antonpopov.juniortask.service.UserService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getAllUsers() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUserName("testUsername");
        user.setIssueList(null);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));
        this.mockMvc.perform(get("/users/")).andExpect(status().isOk()).andExpect(content().json("[{\"id\":1,\"userName\":\"testUsername\"}]"));
    }

    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUserName("testUsername");
        user.setIssueList(null);
        when(userService.save(user)).thenReturn(user);
        this.mockMvc.perform(post("/users/save/userName=testUsername")).andExpect(status().isOk());
    }
}