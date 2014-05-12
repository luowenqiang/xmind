package cn.org.xmind.commons.email;

import org.springframework.stereotype.Service;

/**
 *
 * @author rodney
 */
@Service("mailService")
public class MailService {

    /**
     * 发送邮件
     *
     * @param type 邮件的类型
     * @param to 接受者
     * @param content 邮件的内容
     * @return 发送成功，则返回true
     */
    public boolean send(MailType type, String to, String content) {
        return this.send(type, new String[]{to}, content);
    }

    /**
     *
     * @param type 邮件的类型
     * @param to 接受者
     * @param content 邮件的内容
     * @return 发送成功，则返回true
     */
    public boolean send(String type, String to, String content) {
        return this.send(MailType.valueOf(type), to, content);
    }

    /**
     *
     * @param type 邮件的类型
     * @param to 接受者们
     * @param content 邮件的内容
     * @return 发送成功，则返回true
     */
    public boolean send(String type, String[] to, String content) {
        return this.send(MailType.valueOf(type), to, content);
    }

    /**
     * 发送邮件
     *
     * @param type 邮件类型
     * @param to 接受者们
     * @param content 邮件内容
     * @return 发送成功，则返回true
     */
    public boolean send(MailType type, String[] to, String content) {
        //发送成功，则返回true
        if (type == MailType.ACCOUNT_ACTIVE_CODE) {
            //激活码不进行群发，只发第一个
            EmailUtils.sendActiveCode(to[0], content);
        }
        return true;
    }

    /**
     * 保存邮件模板到数据库中
     *
     * @param type
     * @param templet
     */
    public void saveMailTemplate(MailType type, String templet) {
    }
}
