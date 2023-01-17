package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BidListServiceImpl implements BidListService {

    private static final Logger logger = LogManager.getLogger(BidListServiceImpl.class);

    private final BidListRepository bidListRepository;

    private final ModelMapper modelMapper;

    public BidListServiceImpl(BidListRepository bidListRepository, ModelMapper modelMapper) {
        this.bidListRepository = bidListRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveBidList(BidList bidList){
        logger.info("--- Method saveBidList ---");
        try{
            bidListRepository.save(bidList);
            logger.info("BidList saved : {}", bidList);
        } catch (Exception e){
            logger.error("Impossible to save a bidList : {}", e.getMessage());
        }
    }

    @Override
    public BidListDTO updateBidList(BidList bidList, int id){
        logger.info("--- Method updateBidList ---");
        try {
            BidList bidListHandle = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
            bidList.setBidListId(bidListHandle.getBidListId());
            if (bidList.getBidListId() != null) {
                bidListHandle = bidListRepository.save(bidList);
                logger.info("BidList updated : {}", bidListHandle);
                return modelMapper.map(bidListHandle, BidListDTO.class);
            } else {
                logger.error("BidList id is null with this id : {}", bidList);
                return null;
            }
        } catch (Exception e) {
            logger.error("Impossible to updated the bidList : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteBidListById(int id) {
        logger.info("--- Method deleteBidListById ---");
        try{
            BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
            bidListRepository.delete(bidList);
            logger.info("BidList deleted");
        } catch (Exception e){
            logger.error("Impossible to delete the bidList with this id({}) : {}",id, e.getMessage());
        }
    }


    /**
     * Get BidListDTO for all bidList
     * @return
     */
    @Override
    public List<BidListDTO> getAllBidList() {

        List<BidList> bidListList = bidListRepository.findAll();
        List<BidListDTO> bidListDTOList = new ArrayList<>();

        for(BidList bidList : bidListList){
            BidListDTO bidListDTO = modelMapper.map(bidList, BidListDTO.class);
            bidListDTOList.add(bidListDTO);
        }

        return bidListDTOList;

    }

    /**
     * Get BidListDTO by Id
     * @param id
     * @return
     */
    @Override
    public BidListDTO getBidListById(int id) {

        if(id != 0) {
            Optional<BidList> bidListById = Optional.ofNullable(bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id)));
            if (bidListById.isPresent()) {
                return modelMapper.map(bidListById.get(), BidListDTO.class);
            } else {
                logger.error("BidList not Found id : {})", id);
            }
        } else {
            throw new IllegalArgumentException("Invalid Id:" + id);
        }
        return null;
    }

}
