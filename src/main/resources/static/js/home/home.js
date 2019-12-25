$(document).ready(function () {
    if(sessionStorage.getItem("username")){
        console.log(sessionStorage.getItem("username"));
        $("#uName").html(sessionStorage.getItem("username"));
        $("#logIn").click(()=>{
            // 这里完善个人空间的页面，后期再做。
            alert("不用跳转到登录页面了");
        })
    }else{
        $("#logIn").click(() => {
            window.location.href = "./login";
        })
    }
    getAllConcepts();
    // 禁用键盘esc按钮关闭模态框
    $("#exampleModalCenter").modal({
        show:false,
        keyboard:false
    });

    $('.deleteBtn').click(function () {
        $(this).parent().parent().parent().remove();
    });
    $('.inputTag').tags('initial', 'value');
    //获取信息的事件
    //获取到form表单中填的属性
    /*
      params:
      conceptName:概念名称
      conceptType:概念类别
      conceptDefination:概念定义
      process:过程名
      element:要素
    */

    $("#uploadInfo").click(function(){
            let _conceptType = $('#selectType').find("option:selected").text();
            let _conceptDefination = $('#conceptDefinationInput').val();
            let _conceptName = $("#conceptName").val();
            //@params: conceptInfo是一个对象，存储的是概念的一系列属性
            let conceptInfo = new Object();
            conceptInfo.conceptType = _conceptType;
            conceptInfo.conceptName = _conceptName;
            conceptInfo.conceptDefination = _conceptDefination;
            conceptInfo.structure = [];
            conceptInfo.structure.push({'process':$("#process1").val(),'element':$("#element1").val()});
            if($("#normal2")){
                if($("#process2").val()!==''&&$("#element2").val()!==''){
                    conceptInfo.structure.push({'process':$("#process2").val(),'element':$("#element2").val()});
                }
            }
            if($("#normal3")){
                if($("#process3").val()!==''&&$("#element3").val()!==''){
                    conceptInfo.structure.push({'process':$("#process3").val(),'element':$("#element3").val()});
                }
            }
            //发送ajax请求
            uploadConceptInfo(conceptInfo);
        }
    )
});

function getAllConcepts(){
    $.ajax({
        type:"post",
        url:"/TeamWorking/GetConceptsServlet",
        data:{para:"getList"},
        async:true,
        success:function(data){
            if(data!==""){
                //成功后模态框会关闭
                let conceptList = JSON.parse(data);
                createConceptList(conceptList);
                console.log(JSON.parse(data));
                if(data === "成功"){
                    $(".form-control").val("");
                }
            }
            else{
                alert("失败");
            }
        }

    })
}
let uploadConceptInfo = (conceptInfo) => {
    let uploadInfo = JSON.stringify(conceptInfo);
    $.ajax({
        type:"post",
        url:"/TeamWorking/conceptStructureServlet",
        data:{jsonStr:uploadInfo},
        // dataType:"text",
        async:true,
        // contentType: 'application/json',
        success:function(data){
            if(data==='success'){
                //成功后模态框会关闭
                $("#exampleModalCenter").modal('hide');
                alert("成功");
            }
            else if(data==='fail'){
                alert("失败");
            }
        }
    })
}
// 动态创建每一个div
let createConceptList = (conceptList)=>{
    if(conceptList.length>0){
        var conceptsDiv = $("#conceptsList");
        conceptList.forEach(element => {
            var div = $('<div></div>');
            div.attr('id',element.conceptID);
            // div.addClass("col-md-3");
            div.css({"width":"200px","height":"200px","margin":"0 20px 0 20px","display":"inline-block"});
            div.html(element.conceptName);
            div.appendTo(conceptsDiv);
        });
    }

}
function createConceptDesc() {
    alert(111);
    window.location.href = "./createConceptStructure";
}
