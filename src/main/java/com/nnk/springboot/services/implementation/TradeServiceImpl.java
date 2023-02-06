package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    private static final Logger logger = LogManager.getLogger(TradeServiceImpl.class);

    private final TradeRepository tradeRepository;

    private final ModelMapper modelMapper;

    public TradeServiceImpl(TradeRepository tradeRepository, ModelMapper modelMapper) {
        this.tradeRepository = tradeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDTO saveTrade(Trade trade){
        logger.info("--- Method saveTrade ---");
        try{
            tradeRepository.save(trade);
            logger.info("Trade saved : {}", trade);
            return new ResponseDTO(true, "Trade saved with success");
        } catch (Exception e){
            logger.error("Impossible to save a trade : {}", e.getMessage());
            return new ResponseDTO(false, "Trade saved with success");
        }
    }

    @Override
    public ResponseDTO updateTrade(Trade trade, int id){
        logger.info("--- Method updateTrade ---");
        try {
            Trade tradeHandle = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
            trade.setTradeId(tradeHandle.getTradeId());
            if (trade.getTradeId() != null) {
                tradeHandle = tradeRepository.save(trade);
                logger.info("Trade updated : {}", tradeHandle);
                return new ResponseDTO(true, "Trade updated with success");
            } else {
                logger.error("Trade id is null with this id : {}", trade);
                return new ResponseDTO(false, "Trade id is null with this id " + id);
            }
        } catch (Exception e) {
            logger.error("Impossible to updated the trade : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to updated the trade : " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO deleteTradeById(int id) {
        logger.info("--- Method deleteTradebyId ---");
        try{
            Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
            tradeRepository.delete(trade);
            logger.info("Trade deleted");
            return new ResponseDTO(true, "Trade deleted with success");
        } catch (Exception e){
            logger.error("Impossible to delete the trade with this id({}) : {}",id, e.getMessage());
            return new ResponseDTO(false, "Impossible to deleted the trade : " + e.getMessage());
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
