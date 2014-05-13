package cn.org.xmind.commons.security.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 *
 * @author lwq
 */
public class PasswordEncoderTest {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        for (int i = 0; i < 10; i++) {
            String raw = "1234";
            String enc = encoder.encode(raw);
            System.out.println("raw:" + raw + "=>" + enc);
            System.out.println(encoder.matches(raw, enc));
        }

        System.out.println("=======================");
        encoder = new StandardPasswordEncoder();
        for (int i = 0; i < 10; i++) {
            String raw = "1234";
            String enc = encoder.encode(raw);
            System.out.println("raw:" + raw + "=>" + enc);
            System.out.println(encoder.matches(raw, enc));
        }
    }
}
