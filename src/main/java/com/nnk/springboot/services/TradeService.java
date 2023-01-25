package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.response.ResponseDTO;

import java.util.List;

public interface TradeService {

    public ResponseDTO saveTrade(Trade trade);

    public ResponseDTO updateTrade(Trade trade, int id);

    public ResponseDTO deleteTradeById(int id);
    public List<TradeDTO> getAllTrade();

    public TradeDTO getTradeById(int id);

}
