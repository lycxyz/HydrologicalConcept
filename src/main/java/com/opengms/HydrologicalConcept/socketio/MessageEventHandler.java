package com.opengms.HydrologicalConcept.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnError;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息事件，作为后端与前台交互
 * @authoer liangxifeng 2018-07-07
 */
@Component
public class MessageEventHandler {
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
        System.out.println("客户端:" + client.getSessionId() + "断开连接");
    }

    @OnError
    public void onError(SocketIOClient client){
        System.out.println("出错了！");
    }
    /**
     * 自定义消息事件，客户端js触发：socket.emit('messageevent', {msgContent: msg}); 时触发
     * 前端js的 socket.emit("事件名","参数数据")方法，是触发后端自定义消息事件的时候使用的,
     * 前端js的 socket.on("事件名",匿名函数(服务器向客户端发送的数据))为监听服务器端的事件
     * @param client　客户端信息
     * @param request 请求信息
     * @param data　客户端发送数据{msgContent: msg}
     */
    @OnEvent(value = "message")
    public void onMessage(SocketIOClient client, AckRequest request, MessageInfo data) {
        System.out.println("发来消息：" + data);
        //服务器端向该客户端发送消息
        request.sendAckData();
        client.sendEvent("message","我是服务器都安发送的信息");
    }
    @OnEvent(value = "groupMessage")
    public void onGroupMessage(SocketIOClient client, AckRequest request, MessageInfo data) {
        System.out.println("发来消息：" + data);

    }
    @OnEvent(value = "system")
    public void onSystem(SocketIOClient client, AckRequest request, MessageInfo data) {

    }
    @OnEvent(value = "connect_error")
    public void onConnectError(SocketIOClient client, AckRequest request, MessageInfo data) {

    }
    @OnEvent(value = "connect_timeout")
    public void onConnectTimeout(SocketIOClient client, AckRequest request, MessageInfo data) {

    }
    @OnEvent(value = "reconnect")
    public void onReconnect(SocketIOClient client, AckRequest request, MessageInfo data) {

    }

    @OnEvent(value = "reconnect_attempt")
    public void onReconnectAttempt(SocketIOClient client, AckRequest request, MessageInfo data) {

    }

    @OnEvent(value = "login")
    public void onLogin(SocketIOClient client,String user) {
        System.out.println(user);
    }
    @OnEvent(value = "loginSuccess")
    public void onLoginSuccess(SocketIOClient client, AckRequest request, MessageInfo data) {

    }
    @OnEvent(value = "loginFail")
    public void onLoginFail(SocketIOClient client, AckRequest request, MessageInfo data) {

    }


    public static void sendBuyLogEvent() {   //这里就是向客户端推消息了
        //String dateTime = new DateTime().toString("hh:mm:ss");

        for (UUID clientId : listClient) {
            if (socketIoServer.getClient(clientId) == null) continue;
            socketIoServer.getClient(clientId).sendEvent("enewbuy", "当前时间", 1);
        }

    }
}