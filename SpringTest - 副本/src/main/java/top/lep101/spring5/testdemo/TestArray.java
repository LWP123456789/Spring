package top.lep101.spring5.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.lep101.spring5.collectiontype.Stu;

public class TestArray {

    @Test
    public void test() {
        ApplicationContext context =
            new ClassPathXmlApplicationContext("bean4.xml");

        Stu stu = context.getBean("stu",Stu.class);
        stu.test();
    }
}
