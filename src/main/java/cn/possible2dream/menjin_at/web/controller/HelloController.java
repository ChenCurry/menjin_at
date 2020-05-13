package cn.possible2dream.menjin_at.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//spring boot  自动装配
@RestController
public class HelloController {

    @RequestMapping("/hello")//这个接口的名字就叫做http://localhost:8080/hello
    //// 它就单纯的成了一个接口 不用再去管web.xml  前端  等等一系列东西了 它就和vue完全前后端分离了
    public String hello(){
        //接收前端的参数，调用业务


        //System.out.println("request.getRemoteAddr()"+"==="+request.getRemoteAddr());
//        if("172.30.47.14".equals(request.getRemoteAddr())){
//
//        }else{
//            //System.out.println("非法访问.");
//        }

        //String str = request.getParameter("SC_Name");
//        String str1 = request.getParameter("SC_SerierNO");
        //String str2 = request.getParameter("SC_InOutStatus");
        //System.out.println((str1==null?"未知人员":str1)+"."+(str2!=null?(str2.equals("201")?"出来了.":"进去了."):"未知进出信息."));
//        System.out.println((str1==null?"未知人员":str1));

        return "hello world";
    }
}
