package com.nnk.springboot.controllersTests;

import com.nnk.springboot.controllers.BidListController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.annotation.PathVariable;

import static com.nnk.springboot.CustomSecurityMockMvcRequestPostProcessors.adminValue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.implementation.FixedValue.value;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@Sql(value = "/dataInjected.sql",executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/truncate.sql",executionPhase = AFTER_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BidListController bidListController;

    @Test
    void contextLoads()throws Exception {
        assertThat(bidListController).isNotNull();
    }

    @Test
    public void getBidListPageWithoutLoginTest() throws Exception {

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getBidListPageWithLoginTest() throws Exception {
        mockMvc.perform(get("/bidList/list").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void addBidListTest() throws Exception {
        mockMvc.perform(get("/bidList/add").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBidListTest() throws Exception {
        mockMvc.perform(get("/bidList/update/1").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBidListTest() throws Exception {
        mockMvc.perform(get("/bidList/delete/1").with(adminValue()))
                .andExpect(status().isOk());
    }

}
