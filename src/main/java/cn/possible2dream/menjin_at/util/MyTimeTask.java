package cn.possible2dream.menjin_at.util;

import cn.possible2dream.menjin_at.config.ApplicationContextUtil;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class MyTimeTask extends TimerTask {

    private static Logger logger = LoggerFactory.getLogger(MyTimeTask.class);

    //EmployeeService employeeService = (EmployeeService) ApplicationContextUtil.getBean("employeeService");
    OriginalRecordService originalRecordService = (OriginalRecordService) ApplicationContextUtil.getBean("originalRecordService");

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
        /*System.out.println("Current exec name is " + name);
        logger.info(System.currentTimeMillis()+"111");*/

        /*List<OriginalRecord> cs = originalRecordService.getOriginalRecordListByMaxId(2975567l);
        for (OriginalRecord c : cs) {
            System.out.println("--c.toString():"+c.toString());
        }*/

        /*Long sss = employeeService.selectMaxScSerierno();
        System.out.println("--sss:"+sss);*/

        /*List<OriginalRecord> list = originalRecordService.getOriginalRecordListByMaxId(WebSocketServer.scSeriernoMax);
        int iSize = list.size();
        if(0!=iSize){
            Long ssss = list.get(0).getScSerierno();
            WebSocketServer.scSeriernoMax = ssss;//iSize-1
            System.out.println("记录下这次广播中的最大值:"+ssss);
            String str = JSON.toJSONString(list);
            WebSocketServer.broadCast(str);
            System.out.println("已经进行了广播:"+str);
        }else{
            System.out.println("list长度为0，不需要进行广播");
        }*/

        // 固定查询25条，有变化值则进行广播
//        List<OriginalRecord> list = originalRecordService.getOriginalRecordListByMaxId(WebSocketServer.scSeriernoMax);
//        if(null!=list&&0!=list.size()){
//            OriginalRecordToFore rtf1 = new OriginalRecordToFore(2,list);
//            String str = JSON.toJSONString(rtf1);
//            WebSocketServer.broadCast(str);
//            System.out.println("不等，进行广播:"+str);
//            long ssss = list.get(0).getScSerierno();
//            WebSocketServer.scSeriernoMax = ssss;//iSize-1
//            for(OriginalRecord single:list){
//                if(201==single.getScInoutstatus()){
//                    int iSize = WebSocketServer.listOriginalRecord.size();
//                    for(int j=0;j<iSize;j++){//OriginalRecord shinei :WebSocketServer.listOriginalRecord
//                        if(single.getScCardguidno()==WebSocketServer.listOriginalRecord.get(j).getScCardguidno()){
//                            WebSocketServer.listOriginalRecord.remove(j);
//                            break;
//                        }
//                    }
//                }else if(1==single.getScInoutstatus()){
//                    WebSocketServer.listOriginalRecord.add(single);
//                }
//            }
//        }else{
//            System.out.println("无变化，不需要进行广播");
//        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
