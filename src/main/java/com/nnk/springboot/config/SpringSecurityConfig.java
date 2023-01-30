package com.nnk.springboot.config;

import com.nnk.springboot.services.implementation.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private static final Logger logger = LogManager.getLogger(SpringSecurityConfig.class);

    UserServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                /* Access admin only */
                //.antMatchers("/admin/**").hasAuthority("ADMIN")
                //.antMatchers("/user/**").hasAuthority("ADMIN")
                /* Guest access */
                .antMatchers("/app/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/app/error").permitAll()
                .antMatchers("/CSS/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                /* URL access */
                //.anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/app/login")
                .and()
                /* Show 403.html if access is denied, /error is a get controller */
                .exceptionHandling().accessDeniedPage("/app/error")
                .and()
                .logout()
                .logoutSuccessUrl("/app/login")
                .and()
                .build();
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
