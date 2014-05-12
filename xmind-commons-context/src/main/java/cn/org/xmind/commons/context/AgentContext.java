package cn.org.xmind.commons.context;

/**
 * 用户代理Context，主要是存储浏览器相关的一些信息，方便在多个层次之间传递。
 *
 * @author lwq
 */
public class AgentContext {

    private static final AgentContext context = new AgentContext();
    private final ThreadLocal<String> contextPath = new ThreadLocal<String>();
    private final ThreadLocal<String> domainName = new ThreadLocal<String>();
    private final ThreadLocal<String> requestURI = new ThreadLocal<String>();
    private final ThreadLocal<String> protocol = new ThreadLocal<String>();
    private final ThreadLocal<Integer> port = new ThreadLocal<Integer>();

    public static AgentContext getContext() {
        return context;
    }

    public void setContextPath(String contextPath) {
        this.contextPath.set(contextPath);
    }

    public void setDomainName(String domainName) {
        this.domainName.set(domainName);
    }

    public void setRequestURI(String requestURI) {
        this.requestURI.set(requestURI);
    }

    public void setPort(int port) {
        this.port.set(port);
    }

    public String getContextPath() {
        return this.contextPath.get();
    }

    public String getDomainName() {
        return this.domainName.get();
    }

    public String getRequestURI() {
        return this.requestURI.get();
    }

    public int getPort() {
        return this.port.get();
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol.get();
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol.set(protocol);
    }

    /**
     * 清理掉线程内所有的线程变量
     */
    public void clean() {
        this.contextPath.remove();
        this.domainName.remove();
        this.requestURI.remove();
        this.protocol.remove();
        this.port.remove();
    }
}
