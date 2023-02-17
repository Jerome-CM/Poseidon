package com.nnk.springboot.domainTests;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersTests {
    
    @Autowired
    private UserService userService;

    @Test
    public void userTest() {
        User user = new User();
        user.setUsername("admin");
        user.setFullname("Pierre");
        user.setPassword("AdminPoseiden41#");
        user.setRole("ADMIN");
    
        // Save
        ResponseDTO responseSave = userService.saveUser(user);
        UserDTO userDTOSAve = userService.getAllUsers().get(0);
    
        assertNotNull(userDTOSAve.getId());
        assertEquals("admin", userDTOSAve.getUsername());
        assertEquals("User saved with success", responseSave.getMessage());
    
        // Update with password
        user.setUsername("manager");
        user.setRole("MANAGER");
        user.setId(userDTOSAve.getId());
        ResponseDTO responseUpdate = userService.updateUser(user, userDTOSAve.getId());
        UserDTO userDTOUpdate = userService.getAllUsers().get(0);
        assertEquals("manager", userDTOUpdate.getUsername());
        assertEquals("MANAGER", userDTOUpdate.getRole());
        assertEquals("User updated with success", responseUpdate.getMessage());

        // Update without password
        user.setPassword("");
        ResponseDTO responseUpdateWithoutPassword = userService.updateUser(user, userDTOSAve.getId());
        UserDTO userDTOUpdateWithoutPassword = userService.getUsersById(user.getId());
        assertEquals("User updated with success ( with password )", responseUpdateWithoutPassword.getMessage());

        // Find
        List<UserDTO> listResult = userService.getAllUsers();
        assertTrue(listResult.size() > 0);

        User result = userService.getUserByUsername(user.getUsername());
        assertEquals("MANAGER", user.getRole());

    
        // Delete
        ResponseDTO responseDelete = userService.deleteUserById(user.getId());
        List<UserDTO> list = userService.getAllUsers();
        assertEquals(0, list.size());
        assertEquals("User deleted with success", responseDelete.getMessage());

        // Delete with error
        ResponseDTO responseDeleteError = userService.deleteUserById(10);
        assertEquals("Impossible to find the user", responseDeleteError.getMessage());

    }
}

