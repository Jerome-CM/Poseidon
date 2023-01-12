package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    public UserDetails loadUserByUsername(String username);

    public void saveUser(User user);

    public UserDTO updateUser(User user, int id);

    public void deleteUserById(int id);

    public List<UserDTO> getAllUsers();

    public UserDTO getUsersById(int id);

}
