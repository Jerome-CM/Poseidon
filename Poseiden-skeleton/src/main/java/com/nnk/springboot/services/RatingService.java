package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;

import java.util.List;

public interface TradeService {

    public void saveTrade(Trade trade);

    public TradeDTO updateTrade(Trade trade, int id);

    public void deleteTradeById(int id);
    public List<TradeDTO> getAllTrade();

    public TradeDTO getTradeById(int id);

}
