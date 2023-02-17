package com.nnk.springboot;

import com.nnk.springboot.Utility.PasswordValidate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Khang Nguyen.
 * Email: khang.nguyen@banvien.com
 * Date: 09/03/2019
 * Time: 11:26 AM
 */
@SpringBootTest
public class PasswordTest {

    @Test
    public void encodePasswordTest() {

        String password = "AssertThat41!";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = encoder.encode(password);
        assertNotEquals("AssertThat41!", pw);
        assertTrue(pw.length() > 59);
        assertTrue(PasswordValidate.isValid(password));
    }

}

