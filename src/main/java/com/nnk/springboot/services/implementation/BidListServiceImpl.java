package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
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
    public ResponseDTO saveBidList(BidList bidList){
        logger.info("--- Method saveBidList ---");
        try{
            bidListRepository.save(bidList);
            logger.info("BidList saved : {}", bidList);
            return new ResponseDTO(true, "bidList saved with success");
        } catch (Exception e){
            logger.error("Impossible to save a bidList : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to save a bidList");
        }
    }

    @Override
    public ResponseDTO updateBidList(BidList bidList, int id){
        logger.info("--- Method updateBidList ---");
        Optional<BidList> bidListHandle = bidListRepository.findById(id);
        if(bidListHandle.isPresent()){
            BidList bidListHandleConfirm = bidListHandle.get();
            try{
                bidList.setBidListId(bidListHandleConfirm.getBidListId());
                if (bidList.getBidListId() != null) {
                    bidListHandleConfirm = bidListRepository.save(bidList);
                    logger.info("BidList updated : {}", bidListHandleConfirm);
                    return new ResponseDTO(true, "bidList updated with success");
                } else {
                    logger.error("BidList id is null with this id : {}", bidList);
                    return new ResponseDTO(false, "BidList id is null with this id : " + id);
                }
            } catch (Exception e) {
                logger.error("Impossible to updated the bidList : {}", e.getMessage());
                return new ResponseDTO(false, "Impossible to update a bidList");
            }
        } else {
            logger.error("Impossible to find the bidList");
            return new ResponseDTO(false, "Impossible to find a bidList");
        }
    }

    @Override
    public ResponseDTO deleteBidListById(int id) {
        logger.info("--- Method deleteBidListById ---");
        Optional<BidList> bidList = bidListRepository.findById(id);
        if(bidList.isPresent()) {
            BidList bidListHandleConfirm = bidList.get();
            try{
                bidListRepository.delete(bidListHandleConfirm);
                logger.info("BidList deleted");
                return new ResponseDTO(true, "bidList deleted with success");
            } catch (Exception e){
                logger.error("Impossible to delete the bidList with this id({}) : {}",id, e.getMessage());
                return new ResponseDTO(false, "Impossible to delete a bidList");
            }

        } else {
            logger.error("Impossible to find the bidList");
            return new ResponseDTO(false, "Impossible to find a bidList");
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
            Optional<BidList> bidListById = bidListRepository.findById(id);
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
