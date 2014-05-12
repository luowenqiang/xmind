package cn.org.xmind.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LuoWenqiang
 */
public class MD5Utils {

    private static final Logger logger = Logger.getLogger("MD5Util");

    public static String encrypt(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            String str = ByteUtils.getHexString(md);
            return str;
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.WARNING, e.getLocalizedMessage(), e);
            return s;
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5Utils.encrypt("1234"));
    }
}
