package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.nnk.springboot.controllers.LoginController;

@SpringBootTest
class SpringSecurityAuthApplicationTests {

    @Autowired
    private LoginController controller;

    @Test
    void contextLoads()throws Exception {
        assertThat(controller).isNotNull();
    }

}