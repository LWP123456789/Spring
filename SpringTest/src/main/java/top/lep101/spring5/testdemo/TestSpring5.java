package top.lep101.spring5.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.lep101.spring5.Book;

public class TestSpring5 {

    @Test
    public void testAdd() {
//        1.加载spring配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean1.xml");

//      2.获取配置创建的对象
//      User user = context.getBean("user", User.class);
        Book book = context.getBean("book",Book.class);

        System.out.println(book);
        book.testDemo();
    }

//    @Test
//    public void TestSpring () {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:conf/applicationContext.xml");
//        StudentService as = (StudentService) ac.getBean("studentService");
//        as.queryStudents();
//    }
}
