package cn.org.xmind.commons.context;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用户代理Context，主要是存储浏览器相关的一些信息，方便在多个层次之间传递。
 *
 * @author lwq
 */
public class AgentContext {

    private static final AgentContext context = new AgentContext();
    private final Lock lock = new ReentrantLock();
    private final ThreadLocal<AgentInfo> agent = new ThreadLocal<AgentInfo>();

    private AgentInfo getAgentInfo() {
        lock.lock();
        try {
            AgentInfo agentInfo = agent.get();
            if (agentInfo == null) {
                agentInfo = new AgentInfo();
                agent.set(agentInfo);
            }
            return agentInfo;
        } finally {
            lock.unlock();
        }
    }

    public static AgentContext getContext() {
        return context;
    }

    public void setContextPath(String contextPath) {
        getAgentInfo().setContextPath(contextPath);
    }

    public void setDomainName(String domainName) {
        getAgentInfo().setDomainName(domainName);
    }

    public void setRequestURI(String requestURI) {
        getAgentInfo().setRequestURI(requestURI);
    }

    public void setPort(int port) {
        getAgentInfo().setPort(port);
    }

    public String getContextPath() {
        return getAgentInfo().getContextPath();
    }

    public String getDomainName() {
        return getAgentInfo().getDomainName();
    }

    public String getRequestURI() {
        return getAgentInfo().getRequestURI();
    }

    public int getPort() {
        return getAgentInfo().getPort();
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return getAgentInfo().getProtocol();
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        getAgentInfo().setProtocol(protocol);
    }

    /**
     * 清理掉线程内所有的线程变量
     */
    public void clean() {
        this.agent.remove();
    }
}
