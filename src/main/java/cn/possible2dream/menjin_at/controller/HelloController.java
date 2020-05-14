package cn.possible2dream.menjin_at.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//spring boot  自动装配
@RestController
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")//这个接口的名字就叫做http://localhost:8080/hello
    //// 它就单纯的成了一个接口 不用再去管web.xml  前端  等等一系列东西了 它就和vue完全前后端分离了
    public String hello(){
        //接收前端的参数，调用业务
        return "hello world";
    }

    @RequestMapping("/welcome")
    public String welcome(Map<String,Object> map){

        //给thymeleaf准备数据
        map.put("welcome","welcome thymeleaf!");
        return "result";
    }
}