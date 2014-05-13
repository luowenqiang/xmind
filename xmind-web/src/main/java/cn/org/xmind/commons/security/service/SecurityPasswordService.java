package cn.org.xmind.commons.security.service;

import cn.org.xmind.commons.security.StringSecurity;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author rodney
 */
//@Service("securityPasswordService")
public class SecurityPasswordService implements PasswordEncoder {

    public static String encode(String rawPass, Object salt) {
        System.out.println("salt:" + salt);
        if (rawPass.startsWith("md5:")) {
            //如果密码已md5:开头，表示密码已经是加密后的密码
            return rawPass;
        } else {
            String key = salt + rawPass;
            String pass = "md5:" + StringSecurity.md5(key);
            return pass;
        }
    }

    public static boolean isValid(String encPass, String rawPass, Object salt) {
        String pass = encode(rawPass, salt);
        return encPass.equals(pass);
    }

    /**
     *
     * @param rawPass 未加密的原文，来自HTTP请求
     * @param salt 来自security:salt-source，目前的规则是使用用户的userId属性，即Spring中的username
     * @return
     */
    @Override
    public String encodePassword(String rawPass, Object salt) {
        return encode(rawPass, salt);
    }

    /**
     *
     * @param encPass 加密后的密码，来自数据库
     * @param rawPass 未加密的原文，来自HTTP请求
     * @param salt 来自security:salt-source，目前的规则是使用用户的userId属性，即Spring中的username
     * @return
     */
    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        return isValid(encPass, rawPass, salt);
    }
}
