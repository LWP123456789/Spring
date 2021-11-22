package top.lep101.spring5.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import top.lep101.spring5.entity.Student;
import top.lep101.spring5.service.StudentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/*
* @Controller: 创建控制器(处理器)对象
*   控制器：叫做后端控制器(back controller),自定义的类处理请求的
*   位置：在类的上面,表示创建此类的对象,对象放在springmvc的容器中
* */
@Controller
public class MyController {
    //定义方法,处理请求

    /*
    * springmvc框架，使用控制器类中的方法，处理请求。
    * 方法的特点：
    *   1.方法的形参，表示请求中的参数
    *   2.方法的返回值，表示本次请求的处理请求
    * */

    /*
    * @RequestMapping: 请求映射
    *   属性：value 请求中的url地址，唯一值，以"/"开头
    *   位置：1.在方法的上面(必须的) 2.在类定义的上面(可选)
    *   作用：把指定的请求，交给指定的方法处理，等同于url-pattern
    *
    * 返回值ModelAndView：表示本次请求的处理结果(数据和视图)
    * */
    //不指定method属性,请求方式没有限制
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(HttpServletRequest request) {    //doGet()
        System.out.println("执行了MyController的doSome方法");
        //使用这个方法处理请求,能处理请求的方法叫做控制器方法

        String name = request.getParameter("name");

        //调用service对象,处理请求,返回数据
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","处理了some.do请求,name=" + name);
        mv.addObject("fun","执行了some.do请求");

        //指定视图 , setViewName("视图的完整路径")
        mv.setViewName("/show.jsp");

        //返回结果
        return mv;
        //mv是在"最后"时刻处理的
    }

    //请求方式不一样,错误码是405,请求方式不支持
    //指定other.do使用 post请求方式
    @RequestMapping(value = "/other.do",method = RequestMethod.POST)
    public ModelAndView doOther() {    //doGet()
        System.out.println("执行了MyController的doOther方法");
        //使用这个方法处理请求,能处理请求的方法叫做控制器方法
        //调用service对象,处理请求,返回数据
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","处理了other.do请求");
        mv.addObject("fun","执行了other.do请求");

        //指定视图 , setViewName("视图的完整路径")
        mv.setViewName("/other.jsp");

        //返回结果
        return mv;
        //mv是在"最后"时刻处理的
    }

    /*
    * 当框架调用完doSome()方法后，得到返回值ModelAndView，
    * 框架会在后续的处理逻辑中，处理mv对象里面的数据和视图。
    * 对数据执行request.setAttribute("msg","处理了some.do请求");把数据放入到request作用域。
    * 对视图执行forward转发操作。等同于  request.getRequestDispatcher("/show.jsp").forward(..)
    * */

    /*
    * 逐个接收请求参数
    * 要求：请求中的参数名和控制器方法的形参名一样。按名称对象接收请求参数
    *
    * 参数接收：
    * 1.框架使用request对象,接收参数
    *   String name = request.getParameter("name");
    *   String age = request.getParameter("age");
    * 2.在中央调度器的内部调用 doPropertyParam方法时,按名称对象传递参数
    *   doPropertyParam(name,Integer.valueOf(age))
    *   框架可以实现请求参数 String 到 int , long , float , double 等类型的转换
    * */
    @RequestMapping(value = "/receive-property.do",method = RequestMethod.POST)
    public ModelAndView doPropertyParam( String name,Integer age) {    //doGet()
//        接收参数之前用下面的代码有效
//        request.setCharacterEncoding("UTF-8");
        System.out.println("执行了MyController的doPropertyParam方法,name= " + name + ",age= " + age);
        //使用这个方法处理请求,能处理请求的方法叫做控制器方法
        //调用service对象,处理请求,返回数据
        ModelAndView mv = new ModelAndView();
        mv.addObject("myname",name);
        mv.addObject("myage",age);

        //指定视图 , setViewName("视图的完整路径")
        mv.setViewName("/show.jsp");

        //返回结果
        return mv;
        //mv是在"最后"时刻处理的

    }

    //处理前端发来的请求,根据请求邮箱给请求邮箱发送验证码
    @RequestMapping(value = "/sendCode.do",method = RequestMethod.GET)
    public void doGetCode(HttpServletRequest request) {    //doGet()
//        接收参数之前用下面的代码有效
//        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String title = "登录验证";
        //随机产生验证码
        int code = RandomUtil.randomInt(10000,99999);
        System.out.println(code);

        System.out.println("执行了MyController的doGetCode方法,email="+email);
        //使用这个方法处理请求,能处理请求的方法叫做控制器方法
        //调用service对象,处理请求,返回数据
        //拼接发送内容
        String content = "您的验证码是 "+ code + ", 请在五分钟内完成验证。";
        //发送验证码
        MailUtil.send(email, title, content, false);
    }

    /*
    * 使用对象接收请求中的参数
    * 要求：参数名和java对象的属性名一样。
    *       java类需要有一个无参数构造方法。属性有set方法
    *
    * 框架的处理：
    * 1.调用Student的无参数构造方法，创建对象
    * 2.调用对象set方法，同名的参数，调用对应的set方法
    *
    * */

    /*
    * 声明引用类型,调用引用类型的业务方法
    * */
    @Resource
    private StudentService service;

    //添加用户
    @RequestMapping(value = "/addUser.do")
    public ModelAndView addUser(Student student){
        ModelAndView mv = new ModelAndView();
        System.out.println("123");
        //调用service,处理业务逻辑,把处理结果返回给用户
        int rows = service.addStudent(student);
        String msg = "注册失败!!!";
        if (rows > 0){
            //注册成功,给用户成功的数据和视图
            msg = "注册成功";
        }
        mv.addObject("msg",student.getUsername()+ "," +msg);

        return mv;
    }

}
