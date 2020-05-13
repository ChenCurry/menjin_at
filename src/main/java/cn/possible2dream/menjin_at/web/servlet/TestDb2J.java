package cn.possible2dream.menjin_at.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestDb2J")
public class TestDb2J extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        /*
		System.out.println("request.getCharacterEncoding()"+"==="+request.getCharacterEncoding());
		System.out.println("request.getContextPath()"+"==="+request.getContextPath());
		System.out.println("request.getPathInfo()"+"==="+request.getPathInfo());
		System.out.println("request.getPathTranslated()"+"==="+request.getPathTranslated());
		System.out.println("request.getProtocol()"+"==="+request.getProtocol());
		System.out.println("request.getQueryString()"+"==="+request.getQueryString());
		System.out.println("request.getRemoteHost()"+"==="+request.getRemoteHost());
		System.out.println("request.getRequestURI()"+"==="+request.getRequestURI());
		System.out.println("request.getServletPath()"+"==="+request.getServletPath());
		 */
        //System.out.println("request.getRemoteAddr()"+"==="+request.getRemoteAddr());

        if("172.30.47.14".equals(request.getRemoteAddr())){
            //String str = request.getParameter("SC_Name");
            String str1 = request.getParameter("SC_SerierNO");
            //String str2 = request.getParameter("SC_InOutStatus");
            //System.out.println((str1==null?"未知人员":str1)+"."+(str2!=null?(str2.equals("201")?"出来了.":"进去了."):"未知进出信息."));
            System.out.println((str1==null?"未知人员":str1));
            response.getWriter().println(192);
        }else{
            System.out.println("非法访问.");
            response.getWriter().println("非法访问.");
        }

        //      ?SC_SerierNO=192  本机用IP访问会执行两遍，用localhost正常
        //http://172.30.34.126:8080/menjin_at/servlet/TestDb2J
        //http://172.30.34.126:8080/menjin_at/TestDb2J
        //http://172.30.34.126:8080/TestDb2J
        //http://172.30.34.126:8080/servlet/TestDb2J
    }
}
