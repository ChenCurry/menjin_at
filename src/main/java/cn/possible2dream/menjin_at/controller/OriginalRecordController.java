package cn.possible2dream.menjin_at.controller;

import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.entity.OriginalRecord;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/accessRecord")
public class OriginalRecordController {

    @Resource
    private OriginalRecordService originalRecordService;

    //http://localhost:8080/menjin_at/accessRecord/showAccessRecord?scSerierno=2925958

    @RequestMapping("/showAccessRecord")
    @ResponseBody
    public AccessRecord toIndex(HttpServletRequest request){
        long scSerierno = Integer.parseInt(request.getParameter("scSerierno"));
        AccessRecord accessRecord = originalRecordService.getAccessRecordByScSerierno(scSerierno);
        return accessRecord;
    }

    @RequestMapping(value = "/getTab3Record",method= RequestMethod.GET)
    //@ResponseBody
    public @ResponseBody List<OriginalRecord> getTab3Record(String time1,String time2,String floorx,String departmentx,String nameX,String jobX,String offset,String pageSize,String pageNumber){

        /*  能获取到两个参数
        @RequestParam(value="time1",required=false) String time1
            , @RequestParam(value="time2",required=false) String time2
            , @RequestParam(value="floorx",required=false) String floorx, @RequestParam(value="departmentx",required=false) String departmentx
            , @RequestParam(value="nameX",required=false) String nameX, @RequestParam(value="jobX",required=false) String jobX
            , @RequestParam(value="offset",required=false) String offset, @RequestParam(value="pageSize",required=false) String pageSize
            , @RequestParam(value="pageNumber",required=false) String pageNumber
         */

        //List<string> arr

        /* 能获取到两个参数
        HttpServletRequest request
        */

        /* 能获取到两个参数
        String time1,String time2,String floorx,String departmentx,String nameX,String jobX,String offset,String pageSize,String pageNumber
        */


//        String offset=request.getParameter("offset");
//        String pageSize=request.getParameter("pageSize");
//        String pageNumber=request.getParameter("pageNumber");
//        String time1=request.getParameter("time1");
//        String time2=request.getParameter("time2");
//        String floorx=request.getParameter("floorx");
//        String departmentx=request.getParameter("departmentx");
//        String nameX=request.getParameter("nameX");
//        String jobX=request.getParameter("jobX");

//        String offset=arr.get(0);
//        String pageSize=arr.get(1);
//        String pageNumber=arr.get(2);
//        String time1=arr.get(3);
//        String time2=arr.get(4);
//        String floorx=arr.get(5);
//        String departmentx=arr.get(6);
//        String nameX=arr.get(7);
//        String jobX=arr.get(8);

        System.out.println(offset);
        System.out.println(pageSize);
        System.out.println(pageNumber);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(floorx);
        System.out.println(departmentx);
        System.out.println(nameX);
        System.out.println(jobX);

        List<OriginalRecord> originalRecords= originalRecordService.getTop25();
        return originalRecords;
    }

}