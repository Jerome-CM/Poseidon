package com.nnk.springboot;

import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

public class CustomSecurityMockMvcRequestPostProcessors {

    public static RequestPostProcessor adminValue() {
        return user("admin").password("azerty").roles("ADMIN");
    }

    public static RequestPostProcessor userValue() {
        return user("teddou").password("azerty").roles("USER");
    }
}
