$(document).ready(function() {
    var ws = null;
    if (WebSocket) {
        wsMxgraph = new WebSocket("ws://localhost:8081/TeamWorking/MxGraph");
        // wsMxgraph = new WebSocket("ws://172.21.212.7:8056/TeamWorking/MxGraph");
    }
    else {
        alert("浏览器不支持websocket！");
    }
    //localStorage.setItem("all", "");
    console.log(wsMxgraph);


    //连接建立成功后，触发该方法
    wsMxgraph.onopen = function () {
        //连接建立成功后，向服务器发送消息
        wsMxgraph.send("用户加入了协同画图......")
    };

    this.addSeparator();
    var graph = this.editorUi.editor.graph;
    var button = document.createElement("button");
    button.onclick = function () {
        var encoder = new mxCodec();
        var node = encoder.encode(graph.getModel());
        //mxUtils.popup(mxUtils.getPrettyXml(node), true);
        Integration = mxUtils.getXml(node);
    };
        //接收来自服务器的消息后，触发该方法
    wsMxgraph.onmessage = function (ev) {
            showMessage(ev.data);
        }
    });

function showMessage(data) {
    if(data!=null)
    {
        var graph = this.editorUi.editor.graph;
        var doc = mxUtils.parseXml(data);
        // var req = mxUtils.load(data);
        // var root = req.getDocumentElement();
        // var dec = new mxCodec(root);
        dec.decode(doc.documentElement, graph.getModel());
        graph.getModel().endUpdate();
    }
}