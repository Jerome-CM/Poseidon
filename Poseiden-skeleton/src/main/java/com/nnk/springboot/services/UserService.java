package com.nnk.springboot.services.interfaces;

import com.nnk.springboot.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface User {

    public UserDetails loadUserByUsername(String username);

    public List<UserDTO> getUserDTO(String method, int id);

}
