package com.nnk.springboot.config;

import com.nnk.springboot.services.implementation.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/*

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private static final Logger logger = LogManager.getLogger(SpringSecurityConfig.class);

    UserServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
         http.authorizeHttpRequests((requests) -> {
             try {
                 requests
                        /* Access admin only
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAuthority("ADMIN")
                        /* Guest access
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .formLogin().loginPage("/login")
                        .and()
                        /* Show 403.html if access is denied, /error is a get controller
                        .exceptionHandling().accessDeniedPage("/error")
                        .and()
                        .logout()
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true);
             } catch (Exception e) {
                 logger.info("--- Fail to filterchain ---");
                 throw new RuntimeException(e);
             }
         });
                 return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return new ProviderManager(provider);
    }
}
*/