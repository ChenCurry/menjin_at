package cn.possible2dream.menjin_at.controller;

import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/getTab3Record")
    @ResponseBody
    public AccessRecord getTab3Record(HttpServletRequest request){
//        long scSerierno = Integer.parseInt(request.getParameter("scSerierno"));
//        AccessRecord accessRecord = originalRecordService.getAccessRecordByScSerierno(scSerierno);
        return null;
    }

}