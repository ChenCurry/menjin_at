package cn.possible2dream.menjin_at.controller;

import cn.possible2dream.menjin_at.entity.EmployeeWithBLOBs;
import cn.possible2dream.menjin_at.service.EmployeeService;
import cn.possible2dream.menjin_at.util.MD5Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@RestController
public class LoginController {

    @Resource
    EmployeeService employeeService;


    @RequestMapping("/login")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("username");
        String pwd = request.getParameter("password");

        System.out.println("code:"+code+",pwd:"+pwd);
        //EmployeeService employeeService = EmployeeServiceImpl.getInstance();
        EmployeeWithBLOBs staff = employeeService.getEmployee(code);

        try {
            PrintWriter pw = response.getWriter();
            if(null == staff){
                pw.print("用户不存在");
            }
            else{
                //用 SC_Employee 表的 SC_OfficeTelNO 字段作为密码 1234
                String md5Pwd = MD5Util.getMD5Str(staff.getScOfficetelno(), null);
                if(md5Pwd.equals(pwd)){
                    HttpSession session = request.getSession();
                    session.setAttribute("name", staff.getScName());
                    session.setAttribute("staffId", staff.getScWorkerno());
                    //System.out.println("登陆成功，LoginServlet存储到Constant中，NAMELOGIN="+Constant.NAMELOGIN+",staffIdLogin="+Constant.staffIdLogin);
                    Cookie username = new Cookie("username", URLEncoder.encode(staff.getScName(), "utf-8"));
                    username.setPath("/");//
                    response.addCookie(username);
                    pw.print("ok");
                }
                else{
                    pw.print("用户名或密码不匹配");
                }
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
