package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.dto.response.ResponseDTO;

import java.util.List;

public interface UserService {

    public ResponseDTO saveUser(User user);

    public ResponseDTO updateUser(User user, int id);

    public ResponseDTO deleteUserById(int id);

    public List<UserDTO> getAllUsers();

    public UserDTO getUsersById(int id);

    public boolean isTheUsernameAvailable(String username);

    public User getUserByUsername(String username);

}
