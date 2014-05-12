package cn.org.xmind.commons.email;

/**
 *
 * @author rodney
 */
public enum MailType {

    /**
     * 激活码类型的邮件，查找激活码邮件模板进行发送邮件
     */
    ACCOUNT_ACTIVE_CODE,
    /**
     * 忘记密码且通过邮件找回密码时，发送的找回密码邮件
     */
    ACCOUNT_FORGOT_PASSWORD,
    /**
     * 业务流程已经启动
     */
    PROCESS_STARTED,
    /**
     * 业务流程已经完成
     */
    PROCESS_FINISHED,
    /**
     * 业务流程被拒绝，并且流程结束
     */
    PROCESS_DENIED,
    /**
     * 业务流程审批被驳回，流程未结束
     */
    PROCESS_REJECTED;
}
