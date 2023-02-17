package com.nnk.springboot.controllersTests;

import com.nnk.springboot.controllers.CurveController;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@Sql(value = "/dataInjected.sql",executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "/truncate.sql",executionPhase = AFTER_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class CurvePointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurveController curveController;

    @Test
    void contextLoads()throws Exception {
        assertThat(curveController).isNotNull();
    }

    @Test
    public void getCurvePointListPageWithoutLoginTest() throws Exception {

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getCurvePointPageWithLoginTest() throws Exception {
        mockMvc.perform(get("/curvePoint/list").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void addCurvePointTest() throws Exception {
        mockMvc.perform(get("/curvePoint/add").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void validateCurvePointAdmin() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                .param("curveId", "10")
                .param("term", "10.00")
                .param("value", "5.00")
                .with(adminValue())).andExpect(status().isOk());
    }

    @Test
    public void validateErrorCurvePointAdmin() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                .param("curveId", "10")
                .param("term", "10.00")
                .param("value", "Error")
                .with(adminValue())).andExpect(model().hasErrors());
    }

    @Test
    public void updateCurvePointAdmin() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                .param("curveId", "10")
                .param("term", "10.00")
                .param("value", "13.2")
                .with(adminValue())).andExpect(status().isOk());
    }

    @Test
    public void updateCurvePointTest() throws Exception {
        mockMvc.perform(get("/curvePoint/update/1").with(adminValue()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCurvePointTest() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1").with(adminValue()))
                .andExpect(status().isOk());
    }

}
