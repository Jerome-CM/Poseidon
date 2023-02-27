package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
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
public class BidListServiceImpl implements BidListService {

    private final BidListRepository bidListRepository;

    private final ModelMapper modelMapper;

    public BidListServiceImpl(BidListRepository bidListRepository, ModelMapper modelMapper) {
        this.bidListRepository = bidListRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Save a new bid
     * @param bidList
     * @return
     */
    @Override
    public ResponseDTO saveBidList(BidList bidList){
        log.info("--- Method saveBidList ---");
        try{
            bidListRepository.save(bidList);
            log.info("BidList saved : {}", bidList);
            return new ResponseDTO(true, "bidList saved with success");
        } catch (Exception e){
            log.error("Impossible to save a bidList : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to save a bidList");
        }
    }

    /**
     * Update a bid
     * @param bidList
     * @param id
     * @return
     */
    @Override
    public ResponseDTO updateBidList(BidList bidList, int id){
        log.info("--- Method updateBidList ---");
        Optional<BidList> bidListHandle = bidListRepository.findById(id);
        if(bidListHandle.isPresent()){
            BidList bidListHandleConfirm = bidListHandle.get();
            try{
                bidList.setBidListId(bidListHandleConfirm.getBidListId());
                bidListHandleConfirm = bidListRepository.save(bidList);
                log.info("BidList updated : {}", bidListHandleConfirm);
                return new ResponseDTO(true, "bidList updated with success");
            } catch (Exception e) {
                log.error("Impossible to updated the bidList : {}", e.getMessage());
                return new ResponseDTO(false, "Impossible to update a bidList");
            }
        } else {
            log.error("Impossible to find the bidList");
            return new ResponseDTO(false, "Impossible to find a bidList");
        }
    }

    /**
     * Delete a bid
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deleteBidListById(int id) {
        log.info("--- Method deleteBidListById ---");
        Optional<BidList> bidList = bidListRepository.findById(id);
        if(bidList.isPresent()) {
            BidList bidListHandleConfirm = bidList.get();
            try{
                bidListRepository.delete(bidListHandleConfirm);
                log.info("BidList deleted");
                return new ResponseDTO(true, "bidList deleted with success");
            } catch (Exception e){
                log.error("Impossible to delete the bidList with this id({}) : {}",id, e.getMessage());
                return new ResponseDTO(false, "Impossible to delete a bidList");
            }

        } else {
            log.error("Impossible to find the bidList");
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

        Optional<BidList> bidListById = bidListRepository.findById(id);
        if (bidListById.isPresent()) {
            return modelMapper.map(bidListById.get(), BidListDTO.class);
        } else {
            log.error("BidList not Found id : {})", id);
            throw new IllegalArgumentException("Invalid Id");
        }
    }

}
