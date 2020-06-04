package cn.possible2dream.menjin_at.config;

import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 需要初始化的一些内容放到这里来
 */
@Component
public class PostConstructTest {

    private static final Logger LOG = LoggerFactory.getLogger(PostConstructTest.class);

    @Autowired//@Resource
    OriginalRecordService originalRecordService;

    @PostConstruct
    public void init() {
        LOG.info("PostConstruct 开始了了。。。");
        //初始化操作
        System.out.println("程序启动后，初始化的工作写在这里。");
        WebSocketServer.scSeriernoMax = originalRecordService.selectMaxScSerierno();
        WebSocketServer.listDepartment = originalRecordService.getAllDepartment();
        //WebSocketServer.listOriginalRecord = originalRecordService.getMaxAddTime8h();
    }
}