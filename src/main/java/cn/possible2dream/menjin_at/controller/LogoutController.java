package cn.possible2dream.menjin_at.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.io.IOException;

@RestController
public class LogoutController {
    @RequestMapping("/logout")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response){
//        System.out.println("进入登出后台（这里只是做一个转发，到登录页面）");
        Cookie[] cookies = request.getCookies();
        if(null != cookies && 0 != cookies.length){
            for(int i=0; i<cookies.length; i++){
//                System.out.println("客户端带上来的cookies："+cookies[i].getName());
                if(cookies[i].getName().equals("username")){
                    cookies[i].setMaxAge(-1);
                    cookies[i].setValue(null);
                    cookies[i].setPath("/");//
                    response.addCookie(cookies[i]);
                    break;
                }
            }
        }
        try {
            String reason = request.getParameter("reason");
            if(null == reason || reason.equalsIgnoreCase("logout")){
                response.sendRedirect("login.html");
            }else if(reason.equalsIgnoreCase("othersLogin")){
                response.sendRedirect("othersLogin.html");
            }else{
                response.sendRedirect("login.html");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
