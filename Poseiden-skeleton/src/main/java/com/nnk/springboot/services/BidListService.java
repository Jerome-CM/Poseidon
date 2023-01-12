package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;

import java.util.List;

public interface BidListService {

    public void saveBidList(BidList bidList);

    public BidListDTO updateBidList(BidList bidList, int id);

    public void deleteBidListById(int id);
    public List<BidListDTO> getAllBidList();

    public BidListDTO getBidListById(int id);

}
