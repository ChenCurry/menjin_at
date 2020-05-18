package cn.possible2dream.menjin_at.controller;

import cn.possible2dream.menjin_at.config.WebSocketServer;
import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/test")
public class TestDb2JController {

    @Resource   //(name="originalRecordService")
    OriginalRecordService originalRecordService;

    @RequestMapping("/TestDb2J")
    @ResponseBody
    public void toUpdate(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        if("172.30.47.14".equals(request.getRemoteAddr())){
            Long scSerierno = Long.valueOf(request.getParameter("SC_SerierNO"));
            //System.out.println((str1==null?"未知人员":str1)+"."+(str2!=null?(str2.equals("201")?"出来了.":"进去了."):"未知进出信息."));
            //System.out.println((str1==null?"未知人员":str1));

            AccessRecord accessRecord = originalRecordService.getAccessRecordByScSerierno(scSerierno);
            WebSocketServer.listAccessRecord.add(accessRecord);

            WebSocketServer.broadCast(accessRecord.toString());
            //System.out.println(accessRecord);
            try {
                response.getWriter().println(192);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("非法访问.");
            try {
                response.getWriter().println("非法访问.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //      ?SC_SerierNO=192  本机用IP访问会执行两遍，用localhost正常
        //http://localhost:8080/menjin_at/test/TestDb2J
        //http://172.30.34.126:8080/menjin_at/test/TestDb2J
    }
}
