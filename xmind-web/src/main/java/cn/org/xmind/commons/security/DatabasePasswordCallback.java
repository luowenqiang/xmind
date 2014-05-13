/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.org.xmind.commons.security;

import java.util.logging.Logger;
import javax.security.auth.callback.PasswordCallback;
import org.open4j.commons.security.password.EncrypPasswordRSA;
import org.open4j.commons.security.password.Password;


/**
 *
 * @author lwq
 */
public class DatabasePasswordCallback extends PasswordCallback {

    private static final Logger logger = Logger.getLogger(DatabasePasswordCallback.class.getName());
    /**
     *
     */
    private static final long serialVersionUID = -2034820150051613774L;
    private String privateKeyString;
    private String ciphertext;
    private String plaintext;

    /**
     * @param prompt
     * @param echoOn
     */
    public DatabasePasswordCallback(String prompt, boolean echoOn) {
        super(prompt, echoOn);
    }

    public DatabasePasswordCallback(String privateKeyString, String ciphertext) {
        this("Database password encrypt algorithm must be ...", true);
        this.privateKeyString = privateKeyString;
        this.ciphertext = ciphertext;

        Password password = new Password(ciphertext, privateKeyString);
        password = EncrypPasswordRSA.decrypt(password);
        this. plaintext = password.getPlaintext();
        super.setPassword(plaintext.toCharArray());
    }
}
