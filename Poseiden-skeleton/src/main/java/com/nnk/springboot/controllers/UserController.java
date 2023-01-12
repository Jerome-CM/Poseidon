package com.nnk.springboot.controllers;

import com.nnk.springboot.config.PatternInput;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserRepository userRepository;

    private final UserService userService;

    // private final PatternInput patternInput;

    public UserController(UserRepository userRepository, UserService userService) { //PatternInput patternInput
        this.userRepository = userRepository;
        this.userService = userService;
        //this.patternInput = patternInput;
    }

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        logger.info("--- Method home ---");
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid, Model model) {
        logger.info("--- Method addUser ---");
        // model.addAttribute("pattern", patternInput);
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        logger.info("--- Method validate ---");
        if (!result.hasErrors()) {
            userService.saveUser(user);
            model.addAttribute("users", userService.getAllUsers());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("--- Method showUpdateForm ---");
        model.addAttribute("user", userService.getUsersById(id));
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        logger.info("--- Method updateUser ---");
        userService.updateUser(user, id);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        logger.info("--- Method deleteUser ---");
        userService.deleteUserById(id);
        model.addAttribute("users", userService.getAllUsers());
        return "redirect:/user/list";
    }
}
