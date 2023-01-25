package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.response.ResponseDTO;

import java.util.List;

public interface BidListService {

    public ResponseDTO saveBidList(BidList bidList);

    public ResponseDTO updateBidList(BidList bidList, int id);

    public ResponseDTO deleteBidListById(int id);
    public List<BidListDTO> getAllBidList();

    public BidListDTO getBidListById(int id);

}
