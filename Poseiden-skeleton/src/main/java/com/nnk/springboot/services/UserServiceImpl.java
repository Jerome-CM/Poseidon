package com.nnk.springboot.services;


import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;


    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    /**
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

    // TODO Documentation for 3 methods
    @Override
    public void saveUser(User user){
        logger.info("--- Method saveUser ---");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        try{
            userRepository.save(user);
            logger.info("User saved : {}", user);
        } catch (Exception e){
            logger.error("Impossible to save a user : {}", e.getMessage());
        }
    }

    @Override
    public UserDTO updateUser(User user, int id){
        logger.info("--- Method updateUser ---");
        User usernameExist = userRepository.findByUsername(user.getUsername());
        if(usernameExist == null) {
            try {
                User userHandle = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
                user.setId(userHandle.getId());
                if (user.getId() != null) {
                    userHandle = userRepository.save(user);
                    logger.info("User updated : {}", userHandle);
                    return modelMapper.map(userHandle, UserDTO.class);
                } else {
                    logger.error("User id is null with this id : {}", user);
                    return null;
                }
            } catch (Exception e) {
                logger.error("Impossible to updated the user : {}", e.getMessage());
                return null;
            }
        } else {
            logger.error("This username already exist : {}", user.getUsername());
            return null;
        }
    }

    @Override
    public void deleteUserById(int id) {
        logger.info("--- Method deleteUserById ---");
        try{
            User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
            userRepository.delete(user);
            logger.info("User deleted");
        } catch (Exception e){
            logger.error("Impossible to delete the user with this id({}) : {}",id, e.getMessage());
        }
    }

    /**
     * Get UserDTO for all users
     * @return
     */
    @Override
    public List<UserDTO> getAllUsers() {
        logger.info("--- Method getAllUsers ---");

        List<User> userList = userRepository.findAll();

        List<UserDTO> userDTOList = new ArrayList<>();

        for(User user : userList){
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOList.add(userDTO);
        }

        return userDTOList;

    }

    /**
     * Get UserDTO by Id
     * @param id
     * @return
     */
    @Override
    public UserDTO getUsersById(int id) {
        logger.info("--- Method getUsersById ---");

        if(id != 0) {
            Optional<User> userById = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id)));
            if (userById.isPresent()) {
                return modelMapper.map(userById.get(),UserDTO.class);
            } else {
                logger.error("User not Found id : {})", id);
            }
        } else {
            throw new IllegalArgumentException("Invalid Id:" + id);
        }
        return null;
    }





}