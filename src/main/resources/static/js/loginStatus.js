$(document).ready(function () {
    $.ajax({
        type: "post",
        url: "/TeamWorking/LoginStatusServlet",
        data: "",
        async: false,
        success: function (data) {
            if (data === "message invalid") {
                // alert("Your login information has expired,please login again.");
            }
            else {
                var jsonObj = JSON.parse(data);
                var name = jsonObj.userName;
                var email = jsonObj.email;
                $(".profile_info h2").text(name);
                $(".user-profile.dropdown-toggle").text(name);
                $(".topnav_login").text("Log Out");

                localStorage.setItem("username",name);
                //TODO 解析后台传过来的json字符串，并将username设置为html的内容
                // window.location.href="../html/description_initial.html";
            }
        }
    });
});

