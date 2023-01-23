package com.nnk.springboot.controllersTests;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.modelmapper.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(controllers = BidListController.class)
public class BidListControllerTest {

    private final BidListController bidListController;

    private final BidListService bidListService;

    private final MockMvc mockMvc;


    public BidListControllerTest(BidListController bidListController, BidListService bidListService, MockMvc mockMvc) {
        this.bidListController = bidListController;
        this.bidListService = bidListService;
        this.mockMvc = mockMvc;
    }

    @Test
    public void getBidListPage() throws Exception {
        BidList bid1 = new BidList("Account Test", "Type Test", 10d);
        BidList bid2 = new BidList("Test Account", "Test Type", 20d);

        bidListService.saveBidList(bid1);
        bidListService.saveBidList(bid2);

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.length()", is(2)))
                .andExpect((ResultMatcher) jsonPath("$.[0].account", is("Test Account")));
    }
}
