package com.nnk.springboot.controllersTests;

import com.nnk.springboot.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.nnk.springboot.CustomSecurityMockMvcRequestPostProcessors.adminValue;
import static com.nnk.springboot.CustomSecurityMockMvcRequestPostProcessors.userValue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@Sql(value = "/dataInjected.sql",executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/truncate.sql",executionPhase = AFTER_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Test
    void contextLoads()throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    public void getUserPageWithoutLoginTest() throws Exception {

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getUserPageWithLoginUserTest() throws Exception {

        mockMvc.perform(get("/user/list").with(userValue()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getUserPageWithLoginAdminTest() throws Exception {
        mockMvc.perform(get("/user/list").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void addUserTest() throws Exception {
        mockMvc.perform(get("/user/add").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserTest() throws Exception {
        mockMvc.perform(get("/user/update/1").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(get("/user/delete/1").with(adminValue()))
                .andExpect(status().isOk());
    }

}
