package com.nnk.springboot.domainTests;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Profile("test")
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

        // Save with error
        trade.setAccount(null);
        ResponseDTO responseSaveError = tradeService.saveTrade(trade);
        assertEquals("Impossible to save a trade", responseSaveError.getMessage());


        // Update
        trade.setAccount("Trade Account");
        trade.setType("Type new");
        trade.setTradeId(tradeDTOSAve.getTradeId());
        ResponseDTO responseUpdate = tradeService.updateTrade(trade, tradeDTOSAve.getTradeId());
        TradeDTO tradeDTOUpdate = tradeService.getAllTrade().get(0);
        assertEquals("Type new", tradeDTOUpdate.getType());
        assertEquals("Trade updated with success", responseUpdate.getMessage());


        //Update with error
        ResponseDTO responseUpdateError = tradeService.updateTrade( trade, 10);
        assertEquals("Impossible to find this trade", responseUpdateError.getMessage());


        // Find
        List<TradeDTO> listResult = tradeService.getAllTrade();
        assertTrue(listResult.size() > 0);

        TradeDTO result = tradeService.getTradeById(trade.getTradeId());
        assertEquals("Trade Account", result.getAccount());

        // Delete
        ResponseDTO responseDelete = tradeService.deleteTradeById(trade.getTradeId());
        List<TradeDTO> list = tradeService.getAllTrade();
        assertEquals(0, list.size());
        assertEquals("Trade deleted with success", responseDelete.getMessage());

        // Delete with error
        ResponseDTO responseDeleteError = tradeService.deleteTradeById(10);
        assertEquals("Impossible to find this trade", responseDeleteError.getMessage());

        // Get Error
        assertThrows(IllegalArgumentException.class, () -> tradeService.getTradeById(10));


    }
}
