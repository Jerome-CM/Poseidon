package com.nnk.springboot.controllersTests;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.HomeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.nnk.springboot.CustomSecurityMockMvcRequestPostProcessors.adminValue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomeController homeController;

    @Test
    void contextLoads()throws Exception {
        assertThat(homeController).isNotNull();
    }

    @Test
    public void getHomeTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAdminHomeTestWithoutAuthenticate() throws Exception {
        mockMvc.perform(get("/admin/home"))
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void getAdminHomeTestWithAuthenticate() throws Exception {
        mockMvc.perform(get("/admin/home")
                .with(adminValue())).andExpect(status().isOk());
    }

}
