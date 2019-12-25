$(document).ready(function () {
  $("#loginSubmit").click(function () {
    // 提交前做判断，看两个框的值是否为空
    if (checkInput("#usr") && !checkInput("#pwd")) {
      nullHint("#usr");
    } else if (checkInput("#pwd") && !checkInput("#usr")) {
      nullHint("#pwd");
    } else if (!checkInput("#pwd") && !checkInput("#usr")) {
      //    这里写登录事件的逻辑
      let userObj = {};
      userObj.email = $("#usr").val();
      userObj.password = $("#pwd").val();
      login(userObj);
    } else if (checkInput("#usr") && checkInput("#pwd")) {
      nullHint("#usr");
      nullHint("#pwd");
    }
  });

});
$("#registerSubmit").click(function () {
  if (checkInput("#registerName") && !checkInput("#registerPwd") && !checkInput("#registerEmail")) {
    nullHint("#registerName");
  } else if (!checkInput("#registerName") && checkInput("#registerPwd") && !checkInput("#registerEmail")) {
    nullHint("#registerPwd");
  } else if (!checkInput("#registerName") && !checkInput("#registerPwd") && checkInput("#registerEmail")) {
    nullHint("#registerEmail");
  } else if (!checkInput("#registerName") && !checkInput("#registerPwd") && !checkInput("#registerEmail")) {
    var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    let isok = emailReg.test($("#registerEmail").val());
    if (!isok) {
      alert("邮箱格式不正确，请重新输入！");
      $("#registerEmail").css({
        "border-color": "#C66161",
        "background-color": "#fbe2e2",
        "color": "#C00"
      });
      // emailReg.focus();
    } else {
      let registerObj = {};
      registerObj.registerName = $("#registerName").val();
      registerObj.registerEmail = $("#registerEmail").val();
      registerObj.registerPwd = $("#registerPwd").val();
      // console.log(registerObj);
      register(registerObj);
    }

  }
})
// 待做的内容
// 取消esc关闭模态框的功能
function checkInput(para) {
  if ($(para).val() === null || $(para).val() === "") {
    // console.log($(para).val());
    return true;
  } else if ($(para).val() != null || $(para).val() !== "") {
    return false;
  }

}

function nullHint(para) {
  $(para).css({
    "border-color": "#C66161",
    "background-color": "#fbe2e2",
    "color": "#C00"
  });
  $(para).attr("placeholder", "该属性不能为空");
}
$("#usr").focus(function () {
  $("#usr").css({
    "border-color": "",
    "background-color": "",
    "color": ""
  });
});
$("#pwd").focus(function () {
  $("#pwd").css({
    "border-color": "",
    "background-color": "",
    "color": ""
  });
});
$("#registerPwd").focus(function () {
  $("#registerPwd").css({
    "border-color": "",
    "background-color": "",
    "color": ""
  });
});
$("#registerName").focus(function () {
  $("#registerName").css({
    "border-color": "",
    "background-color": "",
    "color": ""
  });
});
$("#registerEmail").focus(function () {
  $("#registerEmail").css({
    "border-color": "",
    "background-color": "",
    "color": ""
  });
});

function login(obj) {
  let loginObj = JSON.stringify(obj);
  $.ajax({
    type: "post",
    url: "/TeamWorking/LoginControlServlet",
    data: {
      loginInfo: loginObj
    },
    async: true,
    success: function (data) {
      if (data != "") {
        let user = JSON.parse(data);
        sessionStorage.setItem("username", user.username);
        let address = "localhost:8081";
        window.location.href = "http://" + address + "/TeamWorking/home.html";
        // $("#loginModal").hide();
        // 跳转
        // $("#uName").val(data.userName);
      }
    }
  });
}

function register(obj) {
  let registerObj = JSON.stringify(obj);
  $.ajax({
    type: "post",
    url: "/TeamWorking/RegisterControlServlet",
    data: {
      registerInfo: registerObj
    },
    async: true,
    success: function (data) {
      switch (data) {
        case "emailHint":
          alert("邮箱已被注册，请重新输入或直接登录");
          break;
        case "usernameHint":
          alert("昵称已被注册，请更换一个昵称");
          break;
        case "success":
          alert("注册成功");
          $('#myTabContent div:first').tab('show'); // 选择第一个标签
          $('#register').hide(); // 选择第一个标签
          $("#usr").val($("#registerEmail").val());
          $("#pwd").val($("#registerPwd").val());
          $("#register input").val("");

      }
    }
  });
}