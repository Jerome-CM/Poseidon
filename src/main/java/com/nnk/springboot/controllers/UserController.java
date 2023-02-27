package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) { //PatternInput patternInput
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        log.info("--- View list ---");
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid, Model model) {
        log.info("--- Method addUser ---");
        model.addAttribute("user", bid);
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        log.info("--- Method validate ---");
        if (!result.hasErrors()) {
            model.addAttribute("response", userService.saveUser(user));
            model.addAttribute("users", userService.getAllUsers());
            return "/user/list";
        }
        model.addAttribute("response", new ResponseDTO(false, "Error with the form"));
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("--- Method showUpdateForm ---");
        model.addAttribute("user", userService.getUsersById(id));
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        log.info("--- Method updateUser ---");
        model.addAttribute("response", userService.updateUser(user, id));
        log.info("--- All user {} ---", userService.getAllUsers());
        model.addAttribute("users", userService.getAllUsers());
        return "/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        log.info("--- Method deleteUser ---");
        model.addAttribute("response", userService.deleteUserById(id));
        model.addAttribute("users", userService.getAllUsers());
        return "/user/list";
    }
}
