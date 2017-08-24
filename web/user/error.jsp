<%--
  Created by IntelliJ IDEA.
  User: zc
  Date: 2017/8/24
  Time: 20:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error - SixBox</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/main.css">
</head>
<style>
    *{margin:0;padding: 0;}
</style>
<body>
<header class="index-header">
    <div class="head-container">
        <h1><img src="../static/images/SixBox_logo.jpg" alt="logo" height="65">SixBox</h1>
    </div>
</header>
<div class="content">
    <div class="login-register-container">
        <img src="../static/images/index_img.png" alt="index_img">
        <div class="login-register">
            <div class="info-page">
                <div class="info-header">登录失败</div>
            </div>
            <!-- Register Form -->
            <div id="register-page" class="container-fluid">
                <div class="row">
                    <a href="../index.jsp">重新登录</a>
                    <a href="./register.jsp">注册</a>
                </div>
            </div><!-- End of Register Form -->
        </div>
    </div>
</div>
<footer>
    <div class="page-footer">
        <div class="footer-padding"></div>
        <div class="footer-col">
            <ul>
                <li>SixBox</li>
            </ul>
        </div>
        <div class="footer-col">
            <ul>
                <li>开发技术</li>
                <li>Java EE</li>
                <li>Struts2</li>
                <li>Hibernate</li>
                <li>Spring</li>
                <li>Mysql</li>
            </ul>
        </div>
        <div class="footer-col">
            <ul>
                <li>关于我们</li>
            </ul>
        </div>
    </div>
</footer>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../static/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
