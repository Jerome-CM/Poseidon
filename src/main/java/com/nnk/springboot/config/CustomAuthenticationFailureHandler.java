package com.nnk.springboot.config;

import com.nnk.springboot.dto.response.ResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LogManager.getLogger(CustomAuthenticationFailureHandler.class);


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse responseHttp, AuthenticationException exception) throws IOException, ServletException {
        logger.info("--- Method onAuthenticationFailure ---");
        responseHttp.sendRedirect("/app/login");
    }
}