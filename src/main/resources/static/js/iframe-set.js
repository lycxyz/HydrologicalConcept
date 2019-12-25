$(window).resize(function(){
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth - 250;
});

window.onload = function () {
    var href = window.location.href;
    var flag = href.split('#')[1];
    var sidebarWidth = 250;

    if (flag == null || flag == "problem") {
        document.getElementById("content").src = "./Description/problem.html";
        document.getElementById("content").height = window.innerHeight;
        document.getElementById("content").width = window.innerWidth-sidebarWidth;
    }
    else if (flag == "meeting") {
        document.getElementById("content").src = "./chatRoom/chatroom.html";
        document.getElementById("content").height = window.innerHeight;
        document.getElementById("content").width = window.innerWidth-sidebarWidth;
    }
    // else if (flag == "area") {
    //     document.getElementById("content").src = "./area.html";
    //     document.getElementById("content").height = window.innerHeight;
    //     document.getElementById("content").width = window.innerWidth-sidebarWidth;
    // }
    // else if (flag == "time") {
    //     document.getElementById("content").src = "./time.html";
    //     document.getElementById("content").height = window.innerHeight;
    //     document.getElementById("content").width = window.innerWidth-sidebarWidth;
    // }
    // else if (flag == "influence") {
    //     document.getElementById("content").src = "./influence.html";
    //     document.getElementById("content").height = window.innerHeight;
    //     document.getElementById("content").width = window.innerWidth-sidebarWidth;
    // }
    // else if (flag == "results") {
    //     document.getElementById("content").src = "./results.html";
    //     document.getElementById("content").height = window.innerHeight;
    //     document.getElementById("content").width = window.innerWidth-sidebarWidth;
    // }
}

function OnProblemClick() {
    document.getElementById("content").src = "./Description/problem.html";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;

    var url = document.URL;
    var num = url.indexOf('#');
    URL = url.substring(0, num) + "#problem";
    history.pushState(null, null, URL);
}

function OnDesciptionClick() {
    document.getElementById("content").src = "./Description/description.html";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;

    var url = document.URL;
    var num = url.indexOf('#');
    URL = url.substring(0, num) + "#description";
    history.pushState(null, null, URL);
}

function OnIntroductionClick() {
    document.getElementById("content").src = "./Description/introduction.html";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;

    var url = document.URL;
    var num = url.indexOf('#');
    URL = url.substring(0, num) + "#introduction";
    history.pushState(null, null, URL);
}

function OnAreaClick() {
    document.getElementById("content").src = "./Description/area.html";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;

    var url = document.URL;
    var num = url.indexOf('#');
    URL = url.substring(0, num) + "#area";
    history.pushState(null, null, URL);
}

function OnTimeClick() {
    document.getElementById("content").src = "./Description/time.html";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;

    var url = document.URL;
    var num = url.indexOf('#');
    URL = url.substring(0, num) + "#time";
    history.pushState(null, null, URL);
}

function OnInfluenceClick() {
    document.getElementById("content").src = "#influence";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;

    var url = document.URL;
    var num = url.indexOf('#');
    URL = url.substring(0, num) + "#influence";
    history.pushState(null, null, URL);
}

function OnResultClick() {
    document.getElementById("content").src = "#results";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;

    var url = document.URL;
    var num = url.indexOf('#');
    URL = url.substring(0, num) + "#results";
    history.pushState(null, null, URL);
}

function OnMeetingClick() {
    document.getElementById("content").src = "./chatRoom/chatroom.html";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;

    var url = document.URL;
    var num = url.indexOf('#');
    URL = url.substring(0, num) + "#meeting";
    history.pushState(null, null, URL);
}

function OnNewClick() {
    document.getElementById("content").src = "./projects/newproject.html";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;
}

function OnHistoryClick() {
}

function onLoginClick(){
    var originalURL = document.getElementById("content").src;
    sessionStorage.setItem("originalURL", originalURL);  //store the url before log in

    document.getElementById("content").src = "./LoginRegister/login.html";
    document.getElementById("content").height = window.innerHeight;
    document.getElementById("content").width = window.innerWidth-250;
}

$("#logoutbtn").click(function () {

    $.ajax({
        type: "POST",
        url: "/TeamWorking/LogoutServlet",
        success: function () {
            localStorage.removeItem('username');//清除本地储存的用户信息
            document.getElementById("content").src = "./LoginRegister/login.html";

        }
    })
});
