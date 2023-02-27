package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.UserRepository;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("app")
@Slf4j
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public ModelAndView login(User user, Model model) {
        log.info("--- Method login ---");
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        log.info("--- Method getAllUserArticles ( loginController ) ---");
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("/error")
    public ModelAndView error() {
        log.info("--- Method error ---");
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        log.error("403 Forbidden");
        return mav;
    }

    @PostMapping("/logout")
    public ModelAndView postLogout(HttpServletRequest request) {
        log.info("--- Method POST logout ---");
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        session.invalidate();
        mav.addObject("response", new ResponseDTO(true, "You have been logout."));
        mav.setViewName("home");
        return mav;
    }
}
