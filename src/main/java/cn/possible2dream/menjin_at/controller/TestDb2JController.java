package cn.possible2dream.menjin_at.controller;

import cn.possible2dream.menjin_at.service.EmployeeService;
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
    @Resource   //(name="originalRecordService")
    EmployeeService employeeService;

    @RequestMapping("/TestDb2J")
    @ResponseBody
    public void toUpdate(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        if("172.30.47.14".equals(request.getRemoteAddr())){
//            Long scSerierno = Long.valueOf(request.getParameter("SC_SerierNO"));
//            AccessRecord accessRecord = originalRecordService.getAccessRecordByScSerierno(scSerierno);
//            WebSocketServer.broadCast(JSON.toJSONString(accessRecord));

            //scSeriernoMax
//            System.out.println("WebSocketServer.scSeriernoMax当前值为"+WebSocketServer.scSeriernoMax);
            /*List<OriginalRecord> list = originalRecordService.getOriginalRecordListByMaxId(WebSocketServer.scSeriernoMax);
            int iSize = list.size();
            if(0!=iSize){
                WebSocketServer.scSeriernoMax = list.get(iSize-1).getScSerierno();
                WebSocketServer.broadCast(JSON.toJSONString(list));
                System.out.println("已经进行了广播");
            }else{
                System.out.println("list长度为0，不需要进行广播");
            }*/

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
    }
}
