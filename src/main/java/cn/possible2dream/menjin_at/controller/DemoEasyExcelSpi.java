package cn.possible2dream.menjin_at.controller;


import cn.possible2dream.menjin_at.dto.DemoUserDto;
import cn.possible2dream.menjin_at.entity.Conditions;
import cn.possible2dream.menjin_at.entity.OriginalRecord;
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
import java.util.List;

/**
 * @author quaint
 * @date 2020-01-14 11:13
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


    /*
    @PostMapping("/in/excel")
    public String inExcel(@RequestParam("inExcel") MultipartFile inExcel, Model model){

        DemoUserListener demoUserListener = applicationContext.getBean(DemoUserListener.class);

        log.info("demoUserListener 在 spi 调用之前 hashCode为 [{}]", demoUserListener.hashCode());

        if (inExcel.isEmpty()){
            // 读取 local 指定文件
            List<DemoUserDto> demoUserList;
            String filePath = System.getProperty("user.dir")+"/demo-easy-excel/src/main/resources/ExcelTest.xlsx";
            try {
                // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
                EasyExcel.read(filePath, DemoUserDto.class, demoUserListener).sheet().doRead();
                demoUserList = demoUserListener.getVirtualDataBase();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

            model.addAttribute("users", demoUserList);

        } else {
            // 读取 web 上传的文件
            List<DemoUserDto> demoUserList;
            try {
                EasyExcel.read(inExcel.getInputStream(), DemoUserDto.class, demoUserListener).sheet().doRead();
                demoUserList = demoUserListener.getVirtualDataBase();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            model.addAttribute("users", demoUserList);
        }
        log.info("demoUserListener 在 spi 调用之后 hashCode为 [{}]", demoUserListener.hashCode());
        return "index";
    }
*/

    @RequestMapping("/out/excel")
    public void export(HttpServletRequest request,HttpServletResponse response){

        //var data3 = '{"time1":"'+time1+'","time2":"'+time2+'","floorx":"'+floorx+'","departmentx":"'+departmentx+'","nameX":"'+nameX+'","jobX":"'+jobX+'"}';
//        request.getParameter("username");
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



        List<OriginalRecord> originalRecords= originalRecordService.getInOutRecordByConditionsWithoutPages(conditions);

        List<DemoUserDto> userDto = DemoUserDto.getUserDtoTest6(originalRecords);
        // 要忽略的 字段
//        List<String> ignoreIndices = Collections.singletonList("性别");

        // 根据类型获取要反射的对象
//        Class clazz = DemoUserDto.class;

//        // 遍历所有字段, 找到忽略的字段
//        Set<String> excludeFiledNames = new HashSet<>();
//        while (clazz != Object.class){
//            Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
//                ExcelProperty ann = field.getAnnotation(ExcelProperty.class);
//                if (ann!=null && ignoreIndices.contains(ann.value()[0])){
//                    // 忽略 该字段
//                    excludeFiledNames.add(field.getName());
//                }
//            });
//            clazz = clazz.getSuperclass();
//        }

        // 设置序号
//        AtomicInteger i = new AtomicInteger(1);
        //userDto.forEach(u-> u.setNum(i.getAndIncrement()));

        // 创建本地文件
        //EasyExcelUtils.exportLocalExcel(userDto,DemoUserDto.class,"ExcelTest",excludeFiledNames);
        // 创建web文件
        EasyExcelUtils.exportWebExcel(response,userDto,DemoUserDto.class,"jinchujilu",null);
    }
}