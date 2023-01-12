package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.repositories.TradeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TradeServiceImpl implements TradeService {

    private static final Logger logger = LogManager.getLogger(TradeServiceImpl.class);

    private final TradeRepository tradeRepository;

    private final ModelMapper modelMapper;

    public TradeServiceImpl(TradeRepository tradeRepository, ModelMapper modelMapper) {
        this.tradeRepository = tradeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTrade(Trade trade){
        logger.info("--- Method saveTrade ---");
        try{
            tradeRepository.save(trade);
            logger.info("Trade saved : {}", trade);
        } catch (Exception e){
            logger.error("Impossible to save a trade : {}", e.getMessage());
        }
    }

    @Override
    public TradeDTO updateTrade(Trade trade, int id){
        logger.info("--- Method updateTrade ---");
        try {
            Trade tradeHandle = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            trade.setTradeId(tradeHandle.getTradeId());
            if (trade.getTradeId() != null) {
                tradeHandle = tradeRepository.save(trade);
                logger.info("Trade updated : {}", tradeHandle);
                return modelMapper.map(tradeHandle, TradeDTO.class);
            } else {
                logger.error("Trade id is null with this id : {}", trade);
                return null;
            }
        } catch (Exception e) {
            logger.error("Impossible to updated the trade : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteTradeById(int id) {
        logger.info("--- Method deleteTradebyId ---");
        try{
            Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
            tradeRepository.delete(trade);
            logger.info("Trade deleted");
        } catch (Exception e){
            logger.error("Impossible to delete the trade with this id({}) : {}",id, e.getMessage());
        }
    }


    /**
     * Get TradeDTO for all trade
     * @return
     */
    @Override
    public List<TradeDTO> getAllTrade() {

        List<Trade> tradeList = tradeRepository.findAll();
        List<TradeDTO> tradeDTOList = new ArrayList<>();

        for(Trade trade : tradeList){
            TradeDTO tradeDTO = modelMapper.map(trade, TradeDTO.class);
            tradeDTOList.add(tradeDTO);
        }

        return tradeDTOList;

    }

    /**
     * Get TradeDTO by Id
     * @param id
     * @return
     */
    @Override
    public TradeDTO getTradeById(int id) {

        if(id != 0) {
            Optional<Trade> tradeById = Optional.ofNullable(tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id)));
            if (tradeById.isPresent()) {
                return modelMapper.map(tradeById.get(), TradeDTO.class);
            } else {
                logger.error("Trade not Found id : {})", id);
            }
        } else {
            throw new IllegalArgumentException("Invalid Id:" + id);
        }
        return null;
    }

}
