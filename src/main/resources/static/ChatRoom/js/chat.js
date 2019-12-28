var ws;
$(document).ready(function () {
    //判断当前浏览器是否支持WebSocket
    if (WebSocket) {
        ws = new WebSocket("ws://localhost:8082/webSocketChat/" + "lyc");
    } else {
        alert('Not support websocket')
    }

    ws.onopen = function () {
        // var json="{'from':'1','to':'2','input':'111'}";
        // ws.send(json);//可以给后台发送参数
    };
    //接收到消息的回调方法
    ws.onmessage = function (event) {
        // alert('数据回来了额'+event.data)
        let data = JSON.parse(event.data);
        if (data.infoType===1){
            // setMessageInnerHTML(data.info);
        }else if (data.infoType===2){
            // setMessageInnerHTML(data.from+":"+data.message);
            setRelateDiv(data.relateInfo);
        }

        /*
            data的结构是
            message是发送给每个用户的消息
            relatedConcepts是相关的概念列表，需要渲染在左边目录树上的内容
            relatedGeoIcons是相关的图标列表，也是需要渲染在左边目录树上的内容
         */
        // sessionStorage.setItem("message",data["content"]);
        // sessionStorage.setItem("relatedConcepts",JSON.stringify(data["relatedConcepts"]));
        // let a = JSON.parse(sessionStorage.getItem("relatedConcepts"));
        // var pDiv = $("#graphDescription").parent();
        // for (var i=0;i<a.length;i++){
        //     var conceptDiv  =$('<div></div>');
        //     conceptDiv.html(a[i].name);
        //     pDiv.append(conceptDiv);
        // }

        // $("#graphDescription").val(a);

    };
    //断开 web socket 连接成功触发事件
    ws.onclose = function () {
        alert("连接已关闭...");
    };
    let outerDiv = $("#chatBox-list");
    //这里读取群组的人员信息并进行渲染
    for(var i=0;i<5;i++){
        var randomNum = Math.floor(Math.random()*10);
        let div = `<div class="chat-list-people"><div><img src="/static/ChatRoom/img/icon03.png" alt="头像"/></div><div class="chat-name"><p>樱花酒</p></div><div class="message-num">${randomNum}</div></div>`;
        outerDiv.append(div);
    }
    $(".chat-list-people").each(function () {
        $(this).click(function () {
            var n = $(this).index();
            $(".chatBox-head-one").toggle();
            $(".chatBox-head-two").toggle();
            $(".chatBox-list").fadeToggle();
            $(".chatBox-kuang").fadeToggle();
            //传名字
            $(".ChatInfoName").text($(this).children(".chat-name").children("p").eq(0).html());
            //传头像
            $(".ChatInfoHead>img").attr("src", $(this).children().eq(0).children("img").attr("src"));
            //聊天框默认最底部
            $(document).ready(function () {
                $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
            });
        })
    });
});
screenFuc();
function screenFuc() {
    var topHeight = $(".chatBox-head").innerHeight();//聊天头部高度
    //屏幕小于768px时候,布局change
    var winWidth = $(window).innerWidth();
    $(".chatBox-content-demo").css("height", "calc(100% - 47px)");
    if (winWidth <= 768) {
        var totalHeight = $(window).height(); //页面整体高度
        $(".chatBox-info").css("height", totalHeight - topHeight);
        var infoHeight = $(".chatBox-info").innerHeight();//聊天头部以下高度
        //中间内容高度
        $(".chatBox-content").css("height", infoHeight - 46);


        $(".chatBox-list").css("height", totalHeight - topHeight);
        // $(".chatBox-kuang").css("height", "calc(100% - 90px)");
        //chatBox-content-demo
        //calc(100% - 90px)
        $(".div-textarea").css("width", "calc(100% - 90px)");
        // $(".div-textarea").css("width", winWidth - 106);
    } else {
        $(".chatBox-info").css("height", 495);
        $(".chatBox-content").css("height", 448);
        // $(".chatBox-content-demo").css("height", 448);
        $(".chatBox-list").css("height", 495);
        $(".chatBox-kuang").css("height", 495);
        $(".div-textarea").css("width", 260);
    }
}
(window.onresize = function () {
    screenFuc();
})();
//未读信息数量为空时
var totalNum = $(".chat-message-num").html();
if (totalNum == "") {
    $(".chat-message-num").css("padding", 0);
}
$(".message-num").each(function () {
    var wdNum = $(this).html();
    if (wdNum == "") {
        $(this).css("padding", 0);
    }
});

//打开/关闭聊天框
$(".chatBtn").click(function () {
    $(".chatBox").toggle(10);
})

//返回列表
$(".chat-return").click(function () {
    $(".chatBox-head-one").toggle(1);
    $(".chatBox-head-two").toggle(1);
    $(".chatBox-list").fadeToggle(1);
    $(".chatBox-kuang").fadeToggle(1);
});

//      发送信息
$("#chat-fasong").click(function () {

    var textContent = $(".div-textarea").html().replace(/[\n\r]/g, '<br>');
    if (textContent != "") {
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\">2017-12-02 14:26:58</small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"> " + textContent + " </div> " +
            "<div class=\"chat-avatars\"><img src=\"/static/ChatRoom/img/icon01.png\" alt=\"头像\" /></div> </div> </div>");
        //发送后清空输入框
        // $(".div-textarea").html("");
        $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        // $("#relateConcepts").empty();
    };
    var message = document.getElementById('my_message').innerText;
    $("#my_message").html("");
    ws.send(message);
    // var jsonObj = new Object();
    // jsonObj["from"] = "lyc";
    // jsonObj["to"] = "all";
    // jsonObj["input"] = textContent;
    // console.log(jsonObj);
    // var jsonStr = JSON.stringify(jsonObj);
    // ws.send(jsonStr);
});

//      发送表情
$("#chat-biaoqing").click(function () {
    $(".biaoqing-photo").toggle();
});
$(document).click(function () {
    $(".biaoqing-photo").css("display", "none");
});
$("#chat-biaoqing").click(function (event) {
    event.stopPropagation();//阻止事件
});

$(".emoji-picker-image").each(function () {
    $(this).click(function () {
        var bq = $(this).parent().html();
        console.log(bq);
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\">2017-12-02 14:26:58</small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"> " + bq + " </div> " +
            "<div class=\"chat-avatars\"><img src=\"/static/ChatRoom/img/icon01.png\" alt=\"头像\" /></div> </div> </div>");
        //发送后关闭表情框
        $(".biaoqing-photo").toggle();
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
    })
});
//      发送图片
function selectImg(pic) {
    if (!pic.files || !pic.files[0]) {
        return;
    }
    var reader = new FileReader();
    reader.onload = function (evt) {
        var images = evt.target.result;
        $(".chatBox-content-demo").append("<div class=\"clearfloat\">" +
            "<div class=\"author-name\"><small class=\"chat-date\">2017-12-02 14:26:58</small> </div> " +
            "<div class=\"right\"> <div class=\"chat-message\"><img src=" + images + "></div> " +
            "<div class=\"chat-avatars\"><img src=\"/static/ChatRoom/img/icon01.png\" alt=\"头像\" /></div> </div> </div>");
        //聊天框默认最底部
        $(document).ready(function () {
            $("#chatBox-content-demo").scrollTop($("#chatBox-content-demo")[0].scrollHeight);
        });
    };
    reader.readAsDataURL(pic.files[0]);

}

//将其他信息展示在其他位置上
function setRelateDiv(info) {
    $("#concept-panel").empty();
    let infoArray = JSON.parse(info);
    var relateConcepts = $("#concept-panel");
    for (let j = 0; j <infoArray[0].length ; j++) {
        for (var i=0;i<infoArray[0][j].length;i++){
            let div = $("<div></div>");
            div.css("border","1px dashed gray");
            let title = $(`<div style="font-size: 18px;font-weight: 600">`+ infoArray[0][j][i].name +`</div>`);
            let content =$(`<div>`+ infoArray[0][j][i].definition +`</div>`);
            div.append(title);
            div.append(content);
            relateConcepts.append(div);
        }
    }
    $("#geoIcon-panel").empty();
    var relateGeoIcons = $("#geoIcon-panel");
    for (let j = 0; j <infoArray[1].length ; j++) {
        for (var i=0;i<infoArray[1][j].length;i++){
            let geoIcon = $(`<img src="`+infoArray[1][j][i].pathUrl+`" width="80" height="80" style="margin: 5px;border: 2px solid #b4dc8c;">`);

            relateGeoIcons.append(geoIcon);
        }
    }
    $("#conceptMap-panel").empty();
    var relateConceptMaps = $("#conceptMap-panel");
    for (let j = 0; j <infoArray[2].length ; j++) {
        for (var i=0;i<infoArray[2][j].length;i++){
            let conceptMap = $(`<img src="`+infoArray[2][j][i].pathUrl+`" width="100%" height="100%" style="margin-bottom: 5px;border: 2px solid #b4dc8c;">`);

            relateConceptMaps.append(conceptMap);
        }
    }
}

