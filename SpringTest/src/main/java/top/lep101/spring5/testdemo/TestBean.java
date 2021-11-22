package top.lep101.spring5.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.lep101.spring5.bean.Emp;

public class TestBean {

//    @Test
//    public void testBean1() {
//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("bean2.xml");
//
//        UserService userService = context.getBean("userService",UserService.class);
//        userService.add();
//    }

    @Test
    public void testBean2() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean3.xml");

        Emp emp = context.getBean("emp", Emp.class);

        emp.add();
    }
}
