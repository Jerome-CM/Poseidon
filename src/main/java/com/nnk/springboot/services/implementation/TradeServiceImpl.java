package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;

    private final ModelMapper modelMapper;

    public TradeServiceImpl(TradeRepository tradeRepository, ModelMapper modelMapper) {
        this.tradeRepository = tradeRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Save a new Trade
     * @param trade
     * @return
     */
    @Override
    public ResponseDTO saveTrade(Trade trade){
        log.info("--- Method saveTrade ---");
        try{
            tradeRepository.save(trade);
            log.info("Trade saved : {}", trade);
            return new ResponseDTO(true, "Trade saved with success");
        } catch (Exception e){
            log.error("Impossible to save a trade : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to save a trade");
        }
    }

    /**
     * Update a Trade
     * @param trade
     * @param id
     * @return
     */
    @Override
    public ResponseDTO updateTrade(Trade trade, int id){
        log.info("--- Method updateTrade ---");
        Optional<Trade> tradeHandle = tradeRepository.findById(id);
        if(tradeHandle.isPresent()) {
            Trade tradeHandleConfirm = tradeHandle.get();
            try {
                trade.setTradeId(tradeHandleConfirm.getTradeId());
                tradeHandleConfirm = tradeRepository.save(trade);
                log.info("Trade updated : {}", tradeHandleConfirm);
                return new ResponseDTO(true, "Trade updated with success");

            } catch (Exception e) {
                log.error("Impossible to updated the trade : {}", e.getMessage());
                return new ResponseDTO(false, "Impossible to updated the trade : " + e.getMessage());
            }
        } else {
            log.error("Impossible to find the trade");
            return new ResponseDTO(false, "Impossible to find this trade");
        }
    }

    /**
     * Delete a Trade
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deleteTradeById(int id) {
        log.info("--- Method deleteTradeById ---");
        Optional<Trade> trade = tradeRepository.findById(id);
        if(trade.isPresent()) {
            Trade tradeConfirm = trade.get();
            try {
                tradeRepository.delete(tradeConfirm);
                log.info("Trade deleted");
                return new ResponseDTO(true, "Trade deleted with success");
            } catch (Exception e) {
                log.error("Impossible to delete the trade with this id({}) : {}", id, e.getMessage());
                return new ResponseDTO(false, "Impossible to deleted the trade : " + e.getMessage());
            }
        } else {
            log.error("Impossible to find the trade with this id({})", id);
            return new ResponseDTO(false, "Impossible to find this trade");
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

        Optional<Trade> tradeById = tradeRepository.findById(id);
        if (tradeById.isPresent()) {
            return modelMapper.map(tradeById.get(), TradeDTO.class);
        } else {
            log.error("Trade not Found id : {})", id);
            throw new IllegalArgumentException("Invalid Id");
        }
    }

}
