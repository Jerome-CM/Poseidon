package com.nnk.springboot.controllersTests;

import com.nnk.springboot.controllers.BidListController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.annotation.PathVariable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@WebMvcTest(controllers = BidListController.class)
@Sql(value = "/dataInjected.sql",executionPhase = BEFORE_TEST_METHOD)
//@Sql(value = "/truncate.sql",executionPhase = AFTER_TEST_METHOD)
//@ComponentScan("com.nnk.springboot.jwtConfig")
@SpringBootTest
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BidListController bidListController;

    @Test
    void contextLoads()throws Exception {
        assertThat(bidListController).isNotNull();
    }

   /*  @Test
    public void getBidListPage() throws Exception {

      mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.length()", is(4)))
                .andExpect((ResultMatcher) jsonPath("$.[0].account", is("Account1")));
    }

   @Test
    public void updateBidList(@PathVariable("id") Integer id){
        mockMvc.perform(get("/bidList/update/{id}"))

    }*/
}
