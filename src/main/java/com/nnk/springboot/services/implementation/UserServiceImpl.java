package com.nnk.springboot.services.implementation;


import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }
/*
    /**
     * @param username :
     * @return User Spring
     * @throws UsernameNotFoundException

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        /* load username, password and Authorities in a User Spring
        UserDetails userAuth = org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities(user.getRole().toString()).build();
        logger.info("Connexion User : {}", userAuth);
        return userAuth;
    } */

    // TODO Documentation for 3 methods
    @Override
    public ResponseDTO saveUser(User user){
        logger.info("--- Method saveUser ---");
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // the username is available ?
        if(!isTheUsernameAvailable(user.getUsername())){
            logger.error("The username isn't available : {}", user.getUsername());
            return new ResponseDTO(false, "This username isn't available");
        }

        try{
            userRepository.save(user);
            logger.info("User saved : {}", user);
            return new ResponseDTO(true, "User saved with success");
        } catch (Exception e){
            logger.error("Impossible to save a user : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to save the user : " + e.getMessage());
        }

    }

    @Override
    public ResponseDTO updateUser(User user, int id){

        logger.info("--- Method updateUser ---");
        Optional<User> userWithIdJoin = userRepository.findById(id);
        int userJoinGetId = userWithIdJoin.get().getId();

        if(userJoinGetId == user.getId()){

            // the username is available ?
            if(!userWithIdJoin.get().getUsername().equals(user.getUsername())){
                if(!isTheUsernameAvailable(user.getUsername())){
                    logger.error("The username isn't available : {}", user.getUsername());
                    return new ResponseDTO(false, "This username isn't available");
                }
            }

            // The password has he changed ?
            if(user.getPassword().isEmpty()){
                String username = user.getUsername();
                String fullname = user.getFullname();
                String role = user.getRole();

                User userToUpdate = userWithIdJoin.get();
                userToUpdate.setFullname(fullname);
                userToUpdate.setRole(role);
                userToUpdate.setUsername(username);

                try{
                    userRepository.save(userToUpdate);
                    logger.info("User updated : {}", user);
                    return new ResponseDTO(true, "User updated with success");
                } catch (Exception e){
                    logger.error("Impossible to updated the user : {}", e.getMessage());
                    return new ResponseDTO(false, "Impossible to update the user : " + e.getMessage());
                }

            } else {

                try {
                    user.setId(id);
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    User userUpdate = userRepository.save(user);
                    logger.info("User updated : {}", userUpdate);
                    return new ResponseDTO(true, "User updated with success");
                } catch (Exception e) {
                    logger.error("Impossible to updated the user : {}", e.getMessage());
                    return new ResponseDTO(false, "Impossible to update the user : " + e.getMessage());
                }
            }
        } else {
            return new ResponseDTO(false, "The IDs is different");
        }
    }

    @Override
    public ResponseDTO deleteUserById(int id) {
        logger.info("--- Method deleteUserById ---");
        try{
            User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id :" + id));
            userRepository.delete(user);
            logger.info("User deleted");
            return new ResponseDTO(true, "User deleted with success");
        } catch (Exception e){
            logger.error("Impossible to delete the user with this id({}) : {}",id, e.getMessage());
            return new ResponseDTO(false, "Impossible to delete the user : " + e.getMessage());
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

    /**
     * return if the new username is available in the DB
     * @param username
     * @return boolean
     */
    public boolean isTheUsernameAvailable(String username) {
        Optional<User> userFinded = Optional.ofNullable(userRepository.findByUsername(username));
        boolean usernameIsAvailable = userFinded.isPresent() ? false : true;
        return usernameIsAvailable;
    }




}