package cn.org.xmind.commons.identity.exception;

/**
 *
 * @author lwq
 */
public class PasswordValidateFailed extends RuntimeException {

    public static enum Key {

        /**
         * 旧密码校验失败
         */
        OLD_PASSWORD_ERROR(PasswordValidateFailed.class.getName() + ".oldPasswordError"),
        /**
         * 两次输入的密码不一致
         */
        PASSOWRD_NOT_THE_SAME(PasswordValidateFailed.class.getName() + ".passwordNotTheSame");
        private String key;

        private Key(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
    private String key;
    private String message;

    public PasswordValidateFailed(Key key, String message) {
        this.key = key.getKey();
        this.message = message;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
