package com.opengms.HydrologicalConcept.socket;

import com.alibaba.fastjson.JSONObject;
import com.opengms.HydrologicalConcept.service.AnsjSegService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/20 15:15
 */
@ServerEndpoint(value = "/webSocketChat/{nickname}")
@RestController
public class ChatController {

    private static AnsjSegService ansjSegService;

    //分词的注解
    @Autowired
    public void setAnsjSegService(AnsjSegService ansjSegService)
    {
        ChatController.ansjSegService = ansjSegService;
    }

    // 用来记录当前连接数的变量
    private static volatile int onlineCount = 0;
    // 设一个变量来存用户名
    private String nickname;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<ChatController> webSocketSet = new CopyOnWriteArraySet<ChatController>();

    // 与某个客户端的连接会话，需要通过它来与客户端进行数据收发
    private Session session;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("nickname") String nickname) throws Exception
    {
        this.session=session;
        this.nickname=nickname;
        webSocketSet.add(this);     //加入set中
        System.out.println("有新连接加入！当前在线人数为" + webSocketSet.size());
//        this.session.getAsyncRemote().sendText("恭喜您成功连接上WebSocket-->当前在线人数为："+webSocketSet.size());\
        JSONObject object = new JSONObject();
        object.put("info","恭喜您成功连接上WebSocket-->当前在线人数为："+webSocketSet.size());
        object.put("infoType",1);
        this.session.getAsyncRemote().sendText(object.toString());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("nickname") String nickname) {
        LOGGER.info("来自客户端的消息-->"+nickname+": " + message);
        String result = ansjSegService.processInfo(message);
        String relateConceptSet = ansjSegService.elasticSearch(result);
        JSONObject object = new JSONObject();
        object.put("message",message);
        object.put("from",nickname);
        object.put("infoType",2);
        if(relateConceptSet!=""){
            object.put("relateInfo",relateConceptSet);
        }
        //群发消息
//        broadcast(nickname+ ":" +message);
        broadcast(object.toString());
        //同时去做检索，搜索相关的概念


    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void broadcast(String message){
        for(ChatController item :webSocketSet){
            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
            //this.session.getBasicRemote().sendText(message);
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
            //todo明泰拿来该
//            item.session.getAsyncRemote().sendObject(message);//异步发送消息.
        }
    }

}
