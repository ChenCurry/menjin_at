package cn.possible2dream.menjin_at.config;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.possible2dream.menjin_at.entity.Department;
import cn.possible2dream.menjin_at.entity.EmployeeWithBLOBs;
import cn.possible2dream.menjin_at.entity.OriginalRecordToFore;
import cn.possible2dream.menjin_at.service.EmployeeService;
import cn.possible2dream.menjin_at.service.OriginalRecordService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * WebSocketServer
 */

@ServerEndpoint(value="/webSocket",configurator=GetHttpSessionConfigurator.class)//
@Component
public class WebSocketServer {

    static Log log= LogFactory.get(WebSocketServer.class);
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    //private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    //private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    //private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();
    private static Hashtable<Long, WebSocketServer> connections = new Hashtable<>();
    //
    //public static List<OriginalRecord> listOriginalRecord = new ArrayList<OriginalRecord>();//在里边的人
    public static List<Department> listDepartment = new ArrayList<Department>();//放部门信息
    //用来记录当前查到的最大的id值
    public static long scSeriernoMax;
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    private HttpSession httpSession;
    private String nickname;
    /*连接时唯一对应一个员工*/
    private EmployeeWithBLOBs employee;

    public static EmployeeService employeeService;

    public static OriginalRecordService originalRecordService;



    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {//, @PathParam(value = "sid") String userName
        this.session = session;
        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());

        this.nickname = this.httpSession.getAttribute("name").toString();
        String staffId = this.httpSession.getAttribute("staffId").toString();

        EmployeeWithBLOBs employeeWithBLOBs = employeeService.getEmployee(staffId);
        this.employee = employeeWithBLOBs;


        checkLogin(this.employee.getScEmpno());
//        System.out.println("校验登陆 完成");
        connections.put(this.employee.getScEmpno(), this);
//        System.out.println("创建连接 完成");

        //初次建立连接，从后台加载进出记录到当前连接对象
        try {
            OriginalRecordToFore rtf1 = new OriginalRecordToFore(2,originalRecordService.getTop25());

            String str01 = JSON.toJSONString(rtf1);
            System.out.println("第一次传给前台的实时进出信息："+str01);
            this.session.getBasicRemote().sendText(str01);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.out.println("websocket Error: " );
        connections.remove(this.employee.getScEmpno(),this);
        //t.printStackTrace();
        //LogManager.getLogger(getClass()).log(Level.SEVERE, "id为"+this.employee.getScEmpno()+"的WebSocket对象出错，服务器严重错误", t);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void end(@PathParam(value = "sid") String userName) {
        //this.isOpen = false;
        connections.remove(this.employee.getScEmpno(),this);
        System.out.println("连接已断开");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:"+nickname+",报文:"+message);
//        //可以群发消息
//        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                String stt = jsonObject.getString("targetId");
                if("1".equals(stt)){
                    OriginalRecordToFore rtf1 = new OriginalRecordToFore(4,listDepartment);
                    String listDepartmentx = JSON.toJSONString(rtf1);
                    //System.out.println("部门数据："+listDepartmentx);
                    this.session.getBasicRemote().sendText(listDepartmentx);
                }else if("2".equals(stt)){
                    OriginalRecordToFore rtf1 = new OriginalRecordToFore(5,originalRecordService.getMaxAddTime8h());
                    String listShiNei = JSON.toJSONString(rtf1);
                    this.session.getBasicRemote().sendText(listShiNei);
                }else if("3".equals(stt)){
                    OriginalRecordToFore rtf1 = new OriginalRecordToFore(2,originalRecordService.getTop25());
                    String shishi = JSON.toJSONString(rtf1);
                    this.session.getBasicRemote().sendText(shishi);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 广播消息
     * @param jsonString
     */
    public static void broadCast(String jsonString){
        Enumeration<WebSocketServer> enSocket = connections.elements();
        while(enSocket.hasMoreElements()){
            WebSocketServer client = enSocket.nextElement();
            try {
                synchronized (client) {
                    if(client.session.isOpen()){
                        client.session.getBasicRemote().sendText(jsonString);
                    }
                }
            } catch (IOException e) {
                //LogManager.getLogger(getClass()).log(Level.SEVERE, "广播消息发送至id为"+client.staff.getId()+"的前台失败", e);
                System.out.println("Chat Error: Failed to send message to client");
                connections.remove(client.employee.getScEmpno(), client);
            }
        }
    }

    /**
     *   检查用户是否重复登录,把之前登录的顶掉
     */
    private void checkLogin(Long stfId){
        if(connections.containsKey(stfId)){
            try {
                connections.get(stfId).session.getBasicRemote().sendText("1");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                //LogManager.getLogger(getClass()).log(Level.WARNING,"用户重复登录时通知先前登录的用户下线失败",e);
            }
            connections.remove(stfId);
        }
    }
}