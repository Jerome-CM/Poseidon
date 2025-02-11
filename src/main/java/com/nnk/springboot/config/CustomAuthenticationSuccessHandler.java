package com.nnk.springboot.config;

import javax.servlet.http.HttpSession;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.jwtConfig.JwtTokenUtil;
import com.nnk.springboot.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    @Autowired
    private JwtTokenUtil jwtTest;

    @Autowired
    private  UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("--- Method onAuthenticationSuccess ---");
        /*Take the mail in the session after success login*/

        HttpSession session = request.getSession();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getUserByUsername(username);

        if(session.getAttribute("token") == null){
            log.info("Create a new token because is null in the session");
            String token = null;
            try {
                token = jwtTest.createAuthenticationToken(username);
            } catch (Exception e) {
                log.info("Impossible to create a token : {}", e.getMessage());
                throw new RuntimeException(e);
            }
            session.setAttribute("token", token);
            session.setAttribute("role", user.getRole());
        }
        log.info("Token in session : {}", session.getAttribute("token"));

        if(user.getRole().equals("ADMIN")){
            response.sendRedirect("/admin/home");
        }else{
            response.sendRedirect("/");
        }
    }
}