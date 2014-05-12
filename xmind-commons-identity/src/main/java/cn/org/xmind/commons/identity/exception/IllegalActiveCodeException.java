package cn.org.xmind.commons.identity.exception;


import cn.org.xmind.commons.identity.db.entity.AccountActiveLog;
import java.io.Serializable;

/**
 * 过期的激活码
 *
 * @author rodney
 */
public class IllegalActiveCodeException extends IllegalArgumentException implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 激活码
     */
    private String activeCode;
    /**
     * 接收激活邮件的邮箱地址
     */
    private String email;
    private Type type;

    public IllegalActiveCodeException(Type type) {
        this.type = type;
    }

    public IllegalActiveCodeException(Type type, AccountActiveLog log) {
        this.email = log.getUser().getEmail();
        this.activeCode = log.getActiveCode();
        this.type = type;
    }

    /**
     * 激活码
     *
     * @return the activeCode
     */
    public String getActiveCode() {
        return activeCode;
    }

    /**
     * 激活码
     *
     * @param activeCode the activeCode to set
     */
    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    /**
     * 接收激活邮件的邮箱地址
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 接收激活邮件的邮箱地址
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    public static enum Type {

        /**
         * 过期的激活码
         */
        OUT_OF_DATE,
        /**
         * 已经使用过的激活码
         */
        ALREADY_IN_USED,
        /**
         * 激活码不存在
         */
        NOT_EXISTS;
    }

    @Override
    public String getMessage() {
        if (this.type == Type.OUT_OF_DATE) {
            //过期的激活码
            return "过期的激活码，不能再次激活，请重新发送激活码到邮箱，然后再尝试激活";
        } else if (this.type == Type.ALREADY_IN_USED) {
            return "激活码已经被使用过，不能再次使用，请重新发送激活码到邮箱，然后再尝试激活";
        } else if (this.type == Type.NOT_EXISTS) {
            return "激活码不存在，请点击邮件中的激活链接进行激活";
        }
        return super.getMessage();
    }
}
