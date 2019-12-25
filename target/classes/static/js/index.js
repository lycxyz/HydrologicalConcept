$(document).ready(function() {
    // var storage = window.localStorage;
    // var username=storage["username"];
    // var password = storage["password"];
    // var getisstroename = storage["isstorename"];
    // if(username!=null && username!="" && username!=undefined){
    //     $("#name").val(username);
    //     $("#password").val(password);
    //     $("input[type='checkbox']").eq(0).attr("checked",true);
    // }else{
    //     $("#name").val("");
    //     $("#password").val("");
    //     $("input[type='checkbox']").eq(0).attr("checked",false);
    // }
    //
    // $(document).keydown(function (event) {
    //     var e = event || window.event || arguments.callee.caller.arguments[0];
    //     if(e && e.keyCode==13){ // enter 键
    //         $("#btnSubmit").click();
    //     }
    // });
});
$("#btnSubmit").click(function(){
    // alert(222);
    var params ={"username":$("#username").val(),"password":$("#password").val(),"phone":$("#phone").val(),"introduction":$("#introduction").val(),"email":$("#mail").val()}
    // console.log(params);
    $.ajax({
        type: "POST",
        url: "/TeamWorking/RegisterServlet",
        data: params,
        dataType : "json",
        success: function(data){
            var result = JSON.parse(data);
            // console.log(result);
            // $("#user").val(result);
        },
    });
    alert("success");



});
$("#submit").click(function () {
    var params = {"username":$("#name").val(),"password":$("#pwd").val()}
    $.ajax({
        type:"POST",
        url:"/TeamWorking/LoginServlet",
        data: params,
        success: function(data){
            // console.log(data);
            $(".welcome").text(data);
            sessionStorage.setItem('username',data);
        },
    })

})