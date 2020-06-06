package com.opengms.HydrologicalConcept.socketio;

import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.opengms.HydrologicalConcept.entity.User;
import com.opengms.HydrologicalConcept.entity.User_Chat;
import com.opengms.HydrologicalConcept.service.AnsjSegService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息事件，作为后端与前台交互
 * @authoer liangxifeng 2018-07-07
 */
@Component
public class MessageEventHandler {
    private static final Logger logger = LoggerFactory.getLogger(MessageEventHandler.class);

    static ArrayList<User_Chat> listUser = new ArrayList<>();

    static User_Chat currentControlUser = new User_Chat();

    @Autowired
    AnsjSegService ansjSegService;

    public static SocketIOServer socketIoServer;
    static ArrayList<UUID> listClient = new ArrayList<UUID>();
    static final int limitSeconds = 60;
    //线程安全的map
    public static ConcurrentHashMap<String, SocketIOClient> webSocketMap = new ConcurrentHashMap<String, SocketIOClient>();

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        this.socketIoServer = server;
    }

    /**
     * 客户端连接的时候触发，前端js触发：socket = io.connect("http://223.2.41.168:8080");
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String mac = client.getHandshakeData().getSingleUrlParam("mac");
        listClient.add(client.getSessionId());
        //以mac地址为key,SocketIOClient 为value存入map,后续可以指定mac地址向客户端发送消息
        webSocketMap.put(mac,client);
        //socketIoServer.getClient(client.getSessionId()).sendEvent("message", "back data");
        System.out.println("客户端:" + client.getSessionId() + "已连接,mac="+mac);
    }

    /**
     * 客户端关闭连接时触发：前端js触发：socket.disconnect();
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        logger.info("客户端{}断开连接", client.getSessionId());
        if (listClient.contains(client.getSessionId())) {
            listClient.remove(client.getSessionId());

            for (int i = 0; i < listUser.size(); i++) {
                User_Chat user = listUser.get(i);
                if (user.getId() == client.getSessionId()){
                    listUser.remove(user);
                    broadcast(user,"logout");
                    logger.info(user + "logout");
                }
            }
        }
    }

    @OnError
    public void onError(SocketIOClient client){
        System.out.println("出错了！");
    }
    /**
     * 自定义消息事件，客户端js触发：socket.emit('messageevent', {msgContent: msg}); 时触发
     * 前端js的 socket.emit("事件名","参数数据")方法，是触发后端自定义消息事件的时候使用的,
     * 前端js的 socket.on("事件名",匿名函数(服务器向客户端发送的数据))为监听服务器端的事件

     */
    //@OnMessage
    @OnEvent(value = "message")
    public void onMessage(SocketIOClient client, String from, String to, String data,String type) {
        User_Chat fromU = JSON.parseObject(from,User_Chat.class);
        User_Chat toU = JSON.parseObject(to,User_Chat.class);

        String result = ansjSegService.processInfo(data);
        String relateConceptSet = ansjSegService.elasticSearch(result);
        socketIoServer.getClient(fromU.getId()).sendEvent("suggest",relateConceptSet);
        socketIoServer.getClient(toU.getId()).sendEvent("suggest",relateConceptSet);

        socketIoServer.getClient(toU.getId()).sendEvent("message",fromU,toU,data,type);
    }
    @OnEvent(value = "groupMessage")
    public void onGroupMessage(SocketIOClient client, String from, String to, String data,String type) {

        String result = ansjSegService.processInfo(data);
        String relateConceptSet = ansjSegService.elasticSearch(result);

        for (UUID clientId : listClient) {
            socketIoServer.getClient(clientId).sendEvent("suggest",relateConceptSet);
            if (socketIoServer.getClient(clientId) == client) continue;
            socketIoServer.getClient(clientId).sendEvent("groupMessage",from,to,data,type);
        }
    }

    @OnEvent(value = "login")
    public void onLogin(SocketIOClient client,String data, AckRequest request) throws UnsupportedEncodingException {

        User_Chat user = JSON.parseObject(URLDecoder.decode(data,"utf-8"),User_Chat.class);
        if (isHaveUser(user)) {
            logger.info("登录失败,昵称<"+user.getName()+">已存在！");
            client.sendEvent("loginFail", "登录失败,昵称已存在!");
        } else {
            user.setId(client.getSessionId());
            user.setRoomId(client.getSessionId());
            user.setAddress(client.getHandshakeData().getAddress().toString().replaceAll("/::ffff:/",""));
            user.setDeviceType("pc");

            user.setLoginTime(new Date().getTime());
            client.sendEvent("loginSuccess",user,listUser);
            listUser.add(user);
            broadcast( user, "join");
            logger.info(user+"join");
        }
        logger.info("客户端{}登录请求:{}", client.getSessionId(), data);

    }


    @OnEvent(value = "applyRight")
    public void onApplyRight(SocketIOClient client,String applyUser){
        System.out.println(applyUser);
        User_Chat user = JSON.parseObject(applyUser,User_Chat.class);
        if (currentControlUser.getName() == null){
            currentControlUser = user;
            //给所有用户发送消息
            for (UUID clientId : listClient) {
                //给申请者发送获得控制权的信息
                if (socketIoServer.getClient(clientId) == client){
                    socketIoServer.getClient(clientId).sendEvent("getRight");
                    continue;
                }
                //给其他参与者发送失去控制权的信息
                socketIoServer.getClient(clientId).sendEvent("changeRight",currentControlUser);
            }
        }else {
            socketIoServer.getClient(user.getId()).sendEvent("wait",currentControlUser);
        }

    }

    @OnEvent(value = "syncXml")
    public void onSyncXml(SocketIOClient client,String xml,String selected,String relateImages,String geoRules){
        for (UUID clientId : listClient) {
            if (socketIoServer.getClient(clientId) == client) continue;
            socketIoServer.getClient(clientId).sendEvent("refreshXml",xml,selected,relateImages,geoRules);
        }
    }
    @OnEvent(value = "syncSelect")
    public void onSyncSelect(SocketIOClient client,String selected){
        for (UUID clientId : listClient) {
            if (socketIoServer.getClient(clientId) == client) continue;
            socketIoServer.getClient(clientId).sendEvent("cellSelect",selected);
        }
    }

    @OnEvent(value = "releaseRight")
    public void onReleaseRight(SocketIOClient client){
        for (UUID clientId : listClient) {
            socketIoServer.getClient(clientId).sendEvent("release",currentControlUser);
        }
        currentControlUser = new User_Chat();
    }

    //这里就是向客户端推消息了
    public static void broadcast(User_Chat user,String type) {
        for (UUID clientId : listClient) {
            if (socketIoServer.getClient(clientId) == null) continue;
            socketIoServer.getClient(clientId).sendEvent("system",user, type);
        }
    }

    public static boolean isHaveUser(User_Chat user){
        boolean flag = false;
        for (User_Chat item :listUser) {
            if (item.getName().equals(user.getName())) {
                flag = true;
            }
        }
        return flag;
    }


}