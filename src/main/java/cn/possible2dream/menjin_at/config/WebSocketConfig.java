package cn.possible2dream.menjin_at.config;

import cn.possible2dream.menjin_at.service.EmployeeService;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持
 */
@Configuration
public class WebSocketConfig {

    /**
     *  这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    //从配置类中注入employeeService
    @Autowired
    public void setChatService(EmployeeService employeeService) {
        WebSocketServer.employeeService = employeeService;
    }

    //从配置类中注入originalRecordService
    @Autowired
    public void setChatService(OriginalRecordService originalRecordService) {
        WebSocketServer.originalRecordService = originalRecordService;
    }

}
