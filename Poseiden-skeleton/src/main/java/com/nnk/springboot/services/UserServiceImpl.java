package com.nnk.springboot.services;


import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserDetailsService, com.nnk.springboot.services.interfaces.User {
    private static final Logger logger = LogManager.getLogger(UserImpl.class);

    private final UserRepository userRepository;

    private final TradeRepository tradeRepository;

    private final ModelMapper modelMapper;

    public UserImpl(UserRepository userRepository, TradeRepository tradeRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.tradeRepository = tradeRepository;
        this.modelMapper = modelMapper;
    }

    /**
     *
     * @param username :
     * @return User Spring
     * @throws UsernameNotFoundException
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        /* load username, password and Authorities in a User Spring */
        UserDetails userAuth = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities(user.getRole().toString()).build();
        logger.info("Connexion User : {}", userAuth);
        return userAuth;
    }

    /**
     *
     * @param method SINGLE for found a user with ID or MULTIPLE for the users list
     * @param id 0 if MULTIPLE user, obligate if SINGLE (findById)
     * @return
     */
    @Override
    public List<UserDTO> getUserDTO(String method, int id) {
        logger.info("--- Method getUserDTO ---");

        List<User> userList = new ArrayList<>();
        if(method.equals("SINGLE") && id != 0){
            Optional<User> userById = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
            if(userById.isPresent()){
                userList.add(userById.get());
            } else {
                logger.error("User not Found ( method : {}, id : {})", method, id);
            }

        } else if(method.equals("MULTIPLE")) {
             userList = userRepository.findAll();
        }

        List<UserDTO> userDTOList = new ArrayList<>();

        for(User user : userList){
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOList.add(userDTO);
        }

        return userDTOList;

    }





}