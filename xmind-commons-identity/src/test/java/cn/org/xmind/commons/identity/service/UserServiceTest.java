package cn.org.xmind.commons.identity.service;

import cn.org.xmind.commons.context.AgentContext;
import cn.org.xmind.commons.identity.db.entity.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author LuoWenqiang
 */
public class UserServiceTest {

    private static ApplicationContext ctx;

    public UserServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        ctx = new ClassPathXmlApplicationContext(new String[]{
            "/META-INF/spring/applicationContext_email.xml",
            "/META-INF/spring/applicationContext_converter.xml",
            "/META-INF/spring/applicationContext_security.xml",
            "/META-INF/spring/applicationContext_identity.xml",
            "/META-INF/spring/applicationContext_identity_test.xml"});
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        AgentContext.getContext().setProtocol("http");
        AgentContext.getContext().setRequestURI("x");
        AgentContext.getContext().setContextPath("/sdf");
        AgentContext.getContext().setPort(80);
        AgentContext.getContext().setDomainName("www.fkjava.org");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class UserService.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        UserService service = ctx.getBean(UserService.class);
        User user = new User();
        user.setLoginName("luo_wenqiang");
        user.setEmail("luo_wenqiang@126.com");
        user.setPassword("abcd12345");
        
        System.out.println("add");
        service.add(user);
    }
}
