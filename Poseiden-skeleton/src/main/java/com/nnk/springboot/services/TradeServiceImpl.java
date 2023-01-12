package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TradeImpl implements com.nnk.springboot.services.interfaces.Trade {

    private static final Logger logger = LogManager.getLogger(UserImpl.class);

    private final TradeRepository tradeRepository;

    private final ModelMapper modelMapper;

    public TradeImpl(TradeRepository tradeRepository, ModelMapper modelMapper) {
        this.tradeRepository = tradeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TradeDTO> getTradeDTOList() {

        List<Trade> tradeList = tradeRepository.findAll();
        List<TradeDTO> tradeDTOList = new ArrayList<>();

        for(Trade trade : tradeList){
            TradeDTO tradeDTO = modelMapper.map(trade, TradeDTO.class);
            tradeDTOList.add(tradeDTO);
        }

        return tradeDTOList;

    }

    @Override
    public TradeDTO getTradeDTO(int id) {

        Optional<Trade> trade = Optional.ofNullable(tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id)));

        if (trade.isPresent()) {
            TradeDTO tradeDTO = modelMapper.map(trade.get(), TradeDTO.class);
            return tradeDTO;
        }

        return null;
    }

}
