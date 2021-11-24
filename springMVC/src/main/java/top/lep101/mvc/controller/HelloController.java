package top.lep101.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    //"/" --> /WEB-INF/templates/index.html
    @RequestMapping(value = "/")
    public String index(){
        //返回视图名称
        return "index";
    }

    @RequestMapping(value = "/target")
    public String toTarget() {
//        System.out.println("请求转发");
        return "target";
    }

//    @RequestMapping(value = "testPut",method = RequestMethod.PUT)
//    public String testPut() {
//        return "success";
//    }

}
