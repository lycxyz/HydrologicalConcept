var ws;
sessionStorage.setItem("username","lyc");
$(document).ready(function (){

});
$("#closeWebSocket").click(function(){
    ws.close();
});

$("#send").click(function () {
    var message = document.getElementById('text').value;
    ws.send(message);
    $("#text").val("");
});
$("#connect").click(function () {
    var nickname = $("#nickname").val();
    if (nickname===""){
        alert("请输入昵称");
        return;
    }
    //判断当前浏览器是否支持WebSocket
    if (WebSocket) {
        ws = new WebSocket("ws://localhost:8080/webSocketChat/" + nickname);
    } else {
        alert('Not support websocket')
    }
    //连接发生错误的回调方法
    ws.onerror = function() {
        setMessageInnerHTML("error");
    };
    //连接成功建立的回调方法
    ws.onopen = function(event) {
        setMessageInnerHTML("Loc MSG: 成功建立连接");
    };
    //接收到消息的回调方法
    ws.onmessage = function(event) {
        let data = JSON.parse(event.data);
        if (data.infoType===1){
            setMessageInnerHTML(data.info);
        }else if (data.infoType===2){
            setMessageInnerHTML(data.from+":"+data.message);
            setRelateDiv(data.relateInfo);
        }
        // if (JSON.parse(event.data)) {
        //     let data = JSON.parse(event.data);
        //     console.log(data.message);
        //     console.log(data.from);
        //     setMessageInnerHTML(data.from+":"+data.message);
        //     setRelateDiv(data.relateInfo);
        // }else if(!JSON.parse(event.data)){
        //     setMessageInnerHTML(event.data);
        // }

        // if (!event.data.relateInfo) {
        //     setMessageInnerHTML(event.data);
        // }else{
        //     setMessageInnerHTML(event.data.from + ":" + event.data.message);
        // }

    };
    //连接关闭的回调方法
    ws.onclose = function() {
        setMessageInnerHTML("Loc MSG:关闭连接");
    };
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function() {
        ws.close();
    };
});

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

//将其他信息展示在其他位置上
function setRelateDiv(info) {
    $("#relateConcepts").empty();
    let infoArray = JSON.parse(info);
    var relateConcepts = $("#relateConcepts");
    for (var i=0;i<infoArray[0].length;i++){
        let div = $("<div></div>");
        div.css("border","1px dotted gray");
        let title = $(`<h3>`+ infoArray[0][i].name +`</h3>`);
        let content =$(`<p>`+ infoArray[0][i].definition +`</p>`);
        div.append(title);
        div.append(content);
        relateConcepts.append(div);
    }
}
