package com.nnk.springboot.controllersTests;

import com.nnk.springboot.controllers.TradeController;
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
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TradeController tradeController;

    @Test
    void contextLoads()throws Exception {
        assertThat(tradeController).isNotNull();
    }

    @Test
    public void getTradePageWithoutLoginTest() throws Exception {

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getTradePageWithLoginTest() throws Exception {
        mockMvc.perform(get("/trade/list").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void addTradeTest() throws Exception {
        mockMvc.perform(get("/trade/add").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTradeTest() throws Exception {
        mockMvc.perform(get("/trade/update/1").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTradeTest() throws Exception {
        mockMvc.perform(get("/trade/delete/1").with(adminValue()))
                .andExpect(status().isOk());
    }

}
