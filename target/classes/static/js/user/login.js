$(document).ready(function () {
    $("#login-button-submit").click(function () {

        if (checkInputEmpty("#login-input-account")) {
            //为空则使颜色变红
            setInput2RedColor("#login-input-account");
        }
        else if (checkInputEmpty("#login-input-password")) {
            setInput2RedColor("#login-input-password");
        }
        else {
            var email = $("#login-input-email").val();
            var password = $("#login-input-password").val();

            var loginInfoObject = new Object();
            loginInfoObject["email"] = email;
            loginInfoObject["password"] = password;
            var loginInfoStr = JSON.stringify(loginInfoObject);
            $.ajax({
                type: "post",
                url: "/user/login",
                data: loginInfoStr,
                // data: loginInfoObject,
                async: true,
                contentType:"application/json;charset=UTF-8",
                success: function (data) {
                    if (data === "invalid") {
                        alert("Invalid account or password.");
                    }
                    else if(data === "fail"){
                        alert("servers failed...");
                    }
                    else {

                        sessionStorage.setItem("username",data.username);
                        if(localStorage.getItem("historyURL")){
                            let historyURL=localStorage.getItem("historyURL");
                            localStorage.removeItem("historyURL");
                            window.location.href=historyURL;
                        }
                        else{
                            window.location.href="/index";
                            // window.location.href="/TeamWorking/index.html";
                        }
                    }
                }
            });

        }
        return false;

    });
    $("#login-button-register").click(function (){
        window.location.href = "/register";
        // window.location.href = "http://localhost:8081/TeamWorking/html/register.html";
    });
    //input
    $(".x_content input").click(function () {
        recoverInputColor("#" + this.id);
    });


//判空
    function checkInputEmpty(id) {
        if ($(id).val() === null || $(id).val() === "") {
            console.log($(id).val());
            return true;
        }
        return false;
    }

//颜色变红
    function setInput2RedColor(id) {
        $(id).css({"border-color": "#C66161", "background-color": "#fbe2e2", "color": "#C00"});
    }

//使颜色恢复
    function recoverInputColor(id) {
        $(id).css({"border-color": "", "background-color": "", "color": ""});
    }
});

