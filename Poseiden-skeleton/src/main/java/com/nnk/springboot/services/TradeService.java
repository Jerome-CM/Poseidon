package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface Trade {

    public List<TradeDTO> getTradeDTOList();

    public TradeDTO getTradeDTO(int id);

}
