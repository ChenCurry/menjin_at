package cn.possible2dream.menjin_at.controller;

import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.entity.Conditions;
import cn.possible2dream.menjin_at.entity.OriginalRecord;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public @ResponseBody List<OriginalRecord> getTab3Record(String time1,String time2,String floorx,String departmentx,String nameX,String jobX,String pageSize,String pageNumber){

        System.out.println(pageSize);
        System.out.println(pageNumber);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(floorx);
        System.out.println(departmentx);
        System.out.println(nameX);
        System.out.println(jobX);

        Conditions conditions = new Conditions();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(null!=time1&&!"".equals(time1)){
            try {
                conditions.setTime1(formatter.parse(time1));
                conditions.setTime2(formatter.parse(time2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(null!=floorx&&!"".equals(floorx)){
            conditions.setFloorx(Integer.valueOf(floorx));
        }
        if(null!=departmentx&&!"".equals(departmentx)){
            conditions.setDepartmentx(Integer.valueOf(departmentx));
        }
        conditions.setNameX(nameX);
        conditions.setJobX(jobX);
        conditions.setPageNumber(Integer.valueOf(pageNumber));
        conditions.setPageSize(Integer.valueOf(pageSize));


        List<OriginalRecord> originalRecords= originalRecordService.getInOutRecordByConditions(conditions);
        return originalRecords;
    }

}