package cn.possible2dream.menjin_at.controller;

import cn.possible2dream.menjin_at.entity.AccessRecord;
import cn.possible2dream.menjin_at.entity.OriginalRecord;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

   // @RequestMapping("/getTab3Record")
//    @ResponseBody
//    public void getTab3Record(String nameX,String jobX){
//        System.out.println(nameX);
//        System.out.println(jobX);
//    }

//    @GetMapping("/getTab3Record")
////    public String table(Model model){
////        model.addAttribute("tables",null);
////        return "table";
////    }

    @RequestMapping(value = "/getTab3Record",method= RequestMethod.POST)
    public @ResponseBody List<OriginalRecord> getTab3Record(Model model){
        List<OriginalRecord> originalRecords= originalRecordService.getTop25();
        return originalRecords;
    }

}