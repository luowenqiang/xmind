package cn.org.xmind.commons.security;

import cn.org.xmind.commons.utils.ByteUtils;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 *
 * @author Luo Wenqiang
 */
public class StringSecurity {

    private static final Logger logger = Logger.getLogger("StringSecurity");
    /**
     * 字符编码
     */
    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    /**
     * 安全算饭
     */
    private final static String SECURITY_ALGORITHM = "DESede";
    /**
     * 密码算法
     */
    private final static String SECURITY_ALGORITHM_ECB = "DESede/CBC/PKCS5Padding";
    /**
     * 112或者168位的十六进制密钥
     */
    private final static String PASSWORD_CRYPT_KEY = "1234567890aecd1234567890abcd1234567890abcd1234567890abcd1234567890abcd1234567890abcd1234567890abcd1234567890abcd";
    /**
     * 8字节长度的向量
     */
    private final static String PASSWORD_IV = "wokanni!";

    public static String encrypt(String ori) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        SecureRandom sr = new SecureRandom();
        DESedeKeySpec dks = new DESedeKeySpec(PASSWORD_CRYPT_KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECURITY_ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        IvParameterSpec iv = new IvParameterSpec(PASSWORD_IV.getBytes());
        Cipher cipher = Cipher.getInstance(SECURITY_ALGORITHM_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, securekey, iv, sr);
        byte[] bs = cipher.doFinal(DEFAULT_CHARSET.encode(ori).array());
        String result = ByteUtils.getHexString(bs);
        return result;
    }

    public static String dcrypt(String ori) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        SecureRandom sr = new SecureRandom();
        DESedeKeySpec dks = new DESedeKeySpec(PASSWORD_CRYPT_KEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(SECURITY_ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        IvParameterSpec iv = new IvParameterSpec(PASSWORD_IV.getBytes());
        Cipher cipher = Cipher.getInstance(SECURITY_ALGORITHM_ECB);
        cipher.init(Cipher.DECRYPT_MODE, securekey, iv, sr);
        byte[] bs = cipher.doFinal(ByteUtils.getByteArray(ori));
//        String result = getHexString(bs);
        String result = DEFAULT_CHARSET.decode(ByteBuffer.wrap(bs)).toString();
        return result;
    }

    public static String md5(String inStr) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] charArray = inStr.toCharArray();

            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }

            byte[] md5Bytes = md5.digest(byteArray);

            StringBuilder hexValue = new StringBuilder();

            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }

            return hexValue.toString();
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, "MD5加密失败：" + ex.getLocalizedMessage(), ex);
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        String password = "jack12345";
        System.out.println(md5(password));
    }
}
