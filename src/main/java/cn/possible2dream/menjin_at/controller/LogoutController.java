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
        Cookie[] cookies = request.getCookies();
        if(null != cookies && 0 != cookies.length){
            for(int i=0; i<cookies.length; i++){
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
            response.sendRedirect("login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
