package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTests {

    @Autowired
    private TradeService tradeService;

    @Test
    public void tradeTest() {
        Trade trade = new Trade("Trade Account", "Type");

        // Save
        ResponseDTO responseSave = tradeService.saveTrade(trade);
        TradeDTO tradeDTOSAve = tradeService.getAllTrade().get(0);

        assertNotNull(tradeDTOSAve.getTradeId());
        assertEquals("Type", tradeDTOSAve.getType());
        assertEquals("Trade saved with success", responseSave.getMessage());

        // Update
        trade.setType("Type new");
        trade.setTradeId(tradeDTOSAve.getTradeId());
        ResponseDTO responseUpdate = tradeService.updateTrade(trade, tradeDTOSAve.getTradeId());
        TradeDTO tradeDTOUpdate = tradeService.getAllTrade().get(0);
        assertEquals("Type new", tradeDTOUpdate.getType());
        assertEquals("Trade updated with success", responseUpdate.getMessage());

        // Find
        List<TradeDTO> listResult = tradeService.getAllTrade();
        assertTrue(listResult.size() > 0);

        // Delete
        ResponseDTO responseDelete = tradeService.deleteTradeById(trade.getTradeId());
        List<TradeDTO> list = tradeService.getAllTrade();
        assertEquals(0, list.size());
        assertEquals("Trade deleted with success", responseDelete.getMessage());
    }
}
