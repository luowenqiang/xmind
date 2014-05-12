package cn.org.xmind.commons.context;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 保存用户代理访问时的一些Context参数，比如主机名、端口等
 *
 * @author lwq
 */
public class AgentContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String contextPath = request.getContextPath();
        //String localIp = request.getLocalAddr();
        //String localName = request.getLocalName();
        int localPort = request.getLocalPort();
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String protocol = requestURL.substring(0, requestURL.indexOf("://"));
        String domainName = requestURL.substring(requestURL.indexOf("://") + 3);
        if (domainName.indexOf(":") > 0) {
            //有冒号的表示有携带端口信息，所以需要从URL中截获端口号
            String port = domainName.substring(domainName.indexOf(":") + 1);
            port = port.substring(0, port.indexOf("/"));
            localPort = Integer.parseInt(port);

            domainName = domainName.substring(0, domainName.indexOf(":"));
        } else {
            domainName = domainName.substring(0, domainName.indexOf("/"));
        }

        AgentContext.getContext().setContextPath(contextPath);
        AgentContext.getContext().setDomainName(domainName);
        AgentContext.getContext().setPort(localPort);
        AgentContext.getContext().setRequestURI(requestURI);
        AgentContext.getContext().setProtocol(protocol);

        try {
            chain.doFilter(req, response);
        } finally {
            AgentContext.getContext().clean();
        }
    }

    @Override
    public void destroy() {
    }
}
