package cn.org.xmind.commons.security.service;

import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author lwq
 */
@Service("multiPasswordEncoder")
public class MultiPasswordEncoder implements PasswordEncoder {

    /**
     * 用来检查密文是否为用BCrypt算法算出来的
     */
    private final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");
    @Resource
    private BCryptPasswordEncoder bCryptpasswordEncoder;
    @Resource
    private StandardPasswordEncoder standardPasswordEncoder;

    public PasswordEncoder getDefaultPasswordEncoder() {
        //默认使用BCrypt算法计算需要存储的密码
        return bCryptpasswordEncoder;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return this.getDefaultPasswordEncoder().encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            //使用BCrypt算法进行运算
            return this.bCryptpasswordEncoder.matches(rawPassword, encodedPassword);
        } else {
            //使用标准算法进行运算
            return this.standardPasswordEncoder.matches(rawPassword, encodedPassword);
        }

    }
}
