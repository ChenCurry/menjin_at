package cn.possible2dream.menjin_at.test;

import cn.possible2dream.menjin_at.entity.OriginalRecord;
import cn.possible2dream.menjin_at.service.EmployeeService;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import cn.possible2dream.menjin_at.service.impl.OriginalRecordServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest//@SpringBootTestingBootTest(classes = {Application.class})// 指定启动类
public class Test {

    //    @Autowired
//    AccessRecordMapper accessRecordMapper;
    @Autowired
    private OriginalRecordService originalRecordService;
    @Autowired
    private EmployeeService employeeService;


    @org.junit.Test
    public void test11(){
        Long sss = employeeService.selectMaxScSerierno();
        System.out.println("sss:"+sss);
        /*List<OriginalRecord> cs = originalRecordService.getOriginalRecordListByMaxId(2964111l);
        for (OriginalRecord c : cs) {
            System.out.println("c.getSC_Name():"+c.getSC_Name());
        }*/
    }

    public static void main(String [] args){
        OriginalRecordService originalRecordService = new OriginalRecordServiceImpl();
        List<OriginalRecord> cs = originalRecordService.getOriginalRecordListByMaxId(2975567l);
        for (OriginalRecord c : cs) {
            System.out.println("c.getScName():"+c.getScName());
        }
    }
}
