package top.lep101.spring5.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.lep101.spring5.aopanno.User;

public class TestAop {

    @Test
    public void testAopAnno() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean5.xml");

        User user = context.getBean("user", User.class);
        user.add();
    }

}
