package cn.org.xmind.commons.vo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LuoWenqiang
 */
public class RestResponse {

    /**
     * 错误代码，1是一个特殊的值，永远表示成功
     */
    private String errorCode = "1";
    /**
     * 标题
     */
    private String title;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 提示页面的重定向url，这个url是一个相对于WEB项目的绝对路径。<br/>
     * 如果通过AJAX异步请求得到此属性，需要在前面加上ContextPath才能正常访问。
     */
    private String redirect;
    /**
     * 异常信息，这个信息用于异常跟踪使用
     */
    private Exception exception;
    /**
     * 校验结果
     */
    private Map<String, String> validateResultMap = new HashMap<String, String>();

    /**
     * 错误代码，1是一个特殊的值，永远表示成功
     *
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 错误代码，1是一个特殊的值，永远表示成功
     *
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 标题
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 提示信息
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 提示信息
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 提示页面的重定向url，这个url是一个相对于WEB项目的绝对路径<br/>
     * 如果通过AJAX异步请求得到此属性，需要在前面加上ContextPath才能正常访问。
     *
     * @return the redirect
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     * 提示页面的重定向url，这个url是一个相对于WEB项目的绝对路径<br/>
     * 如果通过AJAX异步请求得到此属性，需要在前面加上ContextPath才能正常访问。
     *
     * @param redirect the redirect to set
     */
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    /**
     * 异常信息，这个信息用于异常跟踪使用
     *
     * @return the exception
     */
    public Exception getException() {
        return exception;
    }

    /**
     * 异常信息，这个信息用于异常跟踪使用
     *
     * @param exception the exception to set
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * 校验结果
     *
     * @return the validateResultMap
     */
    public Map<String, String> getValidateResultMap() {
        return validateResultMap;
    }

    /**
     * 校验结果
     *
     * @param validateResultMap the validateResultMap to set
     */
    public void setValidateResultMap(Map<String, String> validateResultMap) {
        this.validateResultMap = validateResultMap;
    }

}
