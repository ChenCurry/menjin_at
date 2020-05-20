package cn.possible2dream.menjin_at.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;

/**
 * 添加@Configuration  注解，自动注入实例对象，并由springboot 启动 定时器，执行任务。
 * 注意： 使用springboot 时保证包扫描路径是正确的；
 */
@Configuration
public class MyTimer {
    @Bean
    public void testQuartzTrigger1() {
        //1.创建一个timer实例
        Timer timer = new Timer();
        //2.创建一个MyTimerTask实例
        MyTimeTask myTimeTask = new MyTimeTask("No.1");

        //3.通过timer定时定频率调用myTimerTask的业务逻辑
        // 即 第一次执行是在当前时间的两秒之后，之后每隔一秒钟执行一次\
        timer.schedule(myTimeTask,5000L,5000L);

    }
}