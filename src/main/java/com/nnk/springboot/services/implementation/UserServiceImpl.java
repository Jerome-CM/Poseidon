package com.nnk.springboot.services.implementation;


import com.nnk.springboot.Utility.PasswordValidate;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;



    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    /**
     * Save a new user
     * @param user
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO saveUser(User user){
        logger.info("--- Method saveUser ---");

        if(PasswordValidate.isValid(user.getPassword())){

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
                return new ResponseDTO(false, "Impossible to save the user");
            }

        } else {
            logger.error("At least one capital letter, 8 characters minimum, at least one number and one symbol (@$!%#?&)");
            return new ResponseDTO(false, "The password pattern isn't exactly");
        }
    }

    /**
     * Update a user with are id
     * @param user
     * @param id
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO updateUser(User user, int id) {

        logger.info("--- Method updateUser ---");
        Optional<User> userWithIdJoin = userRepository.findById(id);
        User userJoinConfirm = userWithIdJoin.get();

        if (Objects.equals(userJoinConfirm.getId(), user.getId())) {

            // the username is available ?
            if (!userWithIdJoin.get().getUsername().equals(user.getUsername())) {
                if (!isTheUsernameAvailable(user.getUsername())) {
                    logger.error("The username isn't available : {}", user.getUsername());
                    return new ResponseDTO(false, "This username isn't available");
                }
            }

            // The password has he changed ?
            if (user.getPassword().isEmpty()) {
                String username = user.getUsername();
                String fullname = user.getFullname();
                String role = user.getRole();

                User userToUpdate = userWithIdJoin.get();
                userToUpdate.setFullname(fullname);
                userToUpdate.setRole(role);
                userToUpdate.setUsername(username);

                // Try update a user without password
                try {
                    userRepository.save(userToUpdate);
                    logger.info("User updated : {}", user);
                    return new ResponseDTO(true, "User updated with success");
                } catch (Exception e) {
                    logger.error("Impossible to updated the user : {}", e.getMessage());
                    return new ResponseDTO(false, "Impossible to update the user");
                }

            } else {
                if(PasswordValidate.isValid(user.getPassword())){
                    // Try update a user with a password
                    try {
                        user.setId(id);
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        User userUpdate = userRepository.save(user);
                        logger.info("User updated : {}", userUpdate);
                        return new ResponseDTO(true, "User updated with success ( with password )");
                    } catch (Exception e) {
                        logger.error("Impossible to updated the user : {}", e.getMessage());
                        return new ResponseDTO(false, "Impossible to update the user");
                    }
                } else {
                    logger.error("The password pattern isn't exactly");
                    return new ResponseDTO(false, "At least one capital letter, 8 characters minimum, at least one number and one symbol (!@#&()–[{}]:;',?/*~$^+=<>])");
                }
            }
        } else {
            return new ResponseDTO(false, "The IDs is different");
        }
    }

    /**
     * Delete a user with are id
     * @param id
     * @return ResponseDTO
     */
    @Override
    public ResponseDTO deleteUserById(int id) {
        logger.info("--- Method deleteUserById ---");
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User userConfirm = user.get();
            try {
                userRepository.delete(userConfirm);
                logger.info("User deleted");
                return new ResponseDTO(true, "User deleted with success");
            } catch (Exception e) {
                logger.error("Impossible to delete the user with this id({}) : {}", id, e.getMessage());
                return new ResponseDTO(false, "Impossible to delete the user");
            }
        } else {
            logger.error("Impossible to find the user with this id({})", id);
            return new ResponseDTO(false, "Impossible to find the user");
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

        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            return modelMapper.map(userById.get(),UserDTO.class);
        } else {
            logger.error("User not found with id : {})", id);
            throw new IllegalArgumentException("User not found with id : " + id);
        }
    }

    /**
     * return if the new username is available in the DB
     * @param username
     * @return boolean
     */
    @Override
    public boolean isTheUsernameAvailable(String username) {
        Optional<User> userFinded = Optional.ofNullable(userRepository.findByUsername(username));
        boolean usernameIsAvailable = userFinded.isPresent() ? false : true;
        return usernameIsAvailable;
    }

    @Override
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

}