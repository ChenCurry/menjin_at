package cn.possible2dream.menjin_at.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class MyTimeTask extends TimerTask {

    private static Logger logger = LoggerFactory.getLogger(MyTimeTask.class);

//  EmployeeService  employeeService   OriginalRecordService   originalRecordService
//OriginalRecordService originalRecordService = (OriginalRecordService) ApplicationContextUtil.getBean("originalRecordService");

    private String name;

    public MyTimeTask(String inputName){
        name = inputName;
    }

    /**
     * run（）方法中是要执行的任务代码，定时器启动时会执行 run() 方法中的业务逻辑 ;
     */
    @Override
    public void run() {
        //打印当前name 的内容
        System.out.println("Current exec name is " + name);
        logger.info(System.currentTimeMillis()+"111");

        /*List<OriginalRecord> cs = originalRecordService.getOriginalRecordListByMaxId(2975567l);
        for (OriginalRecord c : cs) {
            System.out.println("--c.toString():"+c.toString());
        }*/

        /*Long sss = employeeService.selectMaxScSerierno();
        System.out.println("--sss:"+sss);*/
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
