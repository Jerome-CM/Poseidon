package com.nnk.springboot.config;

import com.nnk.springboot.dto.response.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LogManager.getLogger(CustomAuthenticationSuccessHandler.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("--- Method onAuthenticationSuccess ---");
        response.sendRedirect("/");
    }
}