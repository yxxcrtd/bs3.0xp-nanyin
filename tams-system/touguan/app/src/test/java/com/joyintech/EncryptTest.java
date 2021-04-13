package com.joyintech;

import com.joyintech.system.SystemApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes= SystemApplication.class)
public class EncryptTest {

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void test() {
        String password = "tams1_ejfr16_01";
        System.out.println(stringEncryptor.encrypt(password));
    }

}
