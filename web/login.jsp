<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" type="text/css" href="static/quickui/system/login/@media/style.css"/>
    <script type="text/javascript" src="static/quickui/libs/js/jquery.js"></script>
</head>
<body>
<div class="win">

    <div class="login_win">
        <div class="logo">
           <%-- <img src="static/quickui/system/login/@media/login_title.png"/>--%>
        </div>
        <div class="login_title">
            <!--<img src="@medialogin2/login_title.png"/>-->
            <span>代码生成工具登录</span>
        </div>
        <div class="login_content">

            <form action="" method="post" class="form_win" >
                <div class="tel clear">
                    <input type="text" name="" placeholder="请输入用户名" id="username" autocomplete="off" value="admin" />
                </div>
                <div class="pas clear">
                    <input type="password" name="password"  placeholder="请输入密码" id="password" autocomplete="off" value="123456"/>
                </div>
                <button class="login" type="button" onclick="login()">立即登录</button>
            </form>
            <div class="login_info" style="display:none;"></div>
        </div>
        <div class="right">
            <img src="static/quickui/system/login/@media/right.png"/>
        </div>
        <div class="clear"></div>
    </div>
    <!--背景图-->
    <!--<div class="bg_img">
        <img src="@medialogin2/login_register.jpg"/>
    </div>-->
    <!--备案号-->
    <div class="record_number">
        <a href="http://www.cupcat.cn" target="_blank">http://www.cupcat.cn</a><br/>
        &copy; 2017 zxy  All right reserved.
    </div>

</div>
<script>
    $(function() {

        document.getElementById("username").focus();
        $("#username").keydown(function(event) {
            if(event.keyCode == 13) {
                login()
            }
        })
        $("#password").keydown(function(event) {
            if(event.keyCode == 13) {
                login()
            }
        })

    })

    //登录
    function login() {
        var errorMsg = "";
        var loginName = document.getElementById("username");
        var password = document.getElementById("password");
        if(!loginName.value) {
            errorMsg += "&nbsp;&nbsp;用户名不能为空!";
        }
        if(!password.value) {
            errorMsg += "&nbsp;&nbsp;密码不能为空!";
        }

        if(errorMsg != "") {
            $(".login_info").html(errorMsg);
            $(".login_info").show();
        } else {
            $(".login_info").show();
            $(".login_info").html("&nbsp;&nbsp;正在登录中...");
            //登录处理
            //window.location = "/quickui/system/main_html/main.html";


            $.post("login.do", { "username": loginName.value, "password": password.value },
                function(result) {
                    debugger
                    if(result.errorCode == "200") {
                        $(".login_info").html("&nbsp;&nbsp;登录成功，正在转到主页...");
                        window.location = "/view/index.jsp";
                    } else {
                        $(".login_info").html("&nbsp;&nbsp;" + result.errorMessage);
                    }
                }, "json");
        }
    }
</script>
</body>
</html>
