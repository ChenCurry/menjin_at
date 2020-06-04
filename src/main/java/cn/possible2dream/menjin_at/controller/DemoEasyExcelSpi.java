package cn.possible2dream.menjin_at.controller;


import cn.possible2dream.menjin_at.dto.DemoUserDto;
import cn.possible2dream.menjin_at.dto.DemoUserDto2;
import cn.possible2dream.menjin_at.entity.Conditions;
import cn.possible2dream.menjin_at.entity.OriginalRecord;
import cn.possible2dream.menjin_at.entity.OriginalRecordInner;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import cn.possible2dream.menjin_at.util.EasyExcelUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 导出excel
 */
@Controller
public class DemoEasyExcelSpi implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Resource
    private OriginalRecordService originalRecordService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/out/excel")
    public void export(HttpServletRequest request,HttpServletResponse response){

        String time1 = request.getParameter("time1");
        String time2 = request.getParameter("time2");
        String floorx = request.getParameter("floorx");
        String departmentx = request.getParameter("departmentx");
        String nameX = request.getParameter("nameX");
        String jobX = request.getParameter("jobX");

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
        if(null!=nameX&&!"".equals(nameX)){
            conditions.setNameX(nameX);
        }
        if(null!=jobX&&!"".equals(jobX)){
            conditions.setJobX(jobX);
        }

        Date date = new Date();
        long timestamp = date.getTime();

        List<OriginalRecord> originalRecords= originalRecordService.getInOutRecordByConditionsWithoutPages(conditions);

        List<DemoUserDto> userDto = DemoUserDto.getUserDtoTest6(originalRecords);
        // 创建web文件
        EasyExcelUtils.exportWebExcel(response,userDto,DemoUserDto.class,"进出记录-"+timestamp,null);
    }

    @RequestMapping("/out/excel2")
    public void export2(HttpServletRequest request,HttpServletResponse response){

        String time1 = request.getParameter("time1");
        String time2 = request.getParameter("time2");
        String departmentx = request.getParameter("departmentx");
        String nameX = request.getParameter("nameX");
        String jobX = request.getParameter("jobX");

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
        if(null!=departmentx&&!"".equals(departmentx)){
            conditions.setDepartmentx(Integer.valueOf(departmentx));
        }
        if(null!=nameX&&!"".equals(nameX)){
            conditions.setNameX(nameX);
        }
        if(null!=jobX&&!"".equals(jobX)){
            conditions.setJobX(jobX);
        }

        Date date = new Date();
        long timestamp = date.getTime();

        List<OriginalRecordInner> originalRecordInners= originalRecordService.getInnerTimeByConditionsWithoutPages(conditions);

        List<DemoUserDto2> userDto = DemoUserDto2.getUserDtoTest6(originalRecordInners);
        // 创建web文件
        EasyExcelUtils.exportWebExcel(response,userDto,DemoUserDto2.class,"室内时长-"+timestamp,null);
    }

    @RequestMapping("/out/excelPeople")
    public void excelPeople(HttpServletRequest request,HttpServletResponse response){

        Date date = new Date();
        long timestamp = date.getTime();
        List<OriginalRecord> listOriginalRecords = originalRecordService.getMaxAddTime8h();
        List<DemoUserDto> userDto = DemoUserDto.getUserDtoTest6(listOriginalRecords);
        EasyExcelUtils.exportWebExcel(response,userDto,DemoUserDto.class,"室内人员-"+timestamp,null);
    }
}