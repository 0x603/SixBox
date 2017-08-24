<%--
  Created by IntelliJ IDEA.
  User: zc
  Date: 2017/8/24
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>首页 - SixBox</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css">
</head>
<style>
    *{margin:0;padding: 0;}
</style>
<body>
<header class="index-header">
    <div class="head-container">
        <h1><img src="${pageContext.request.contextPath}/static/images/SixBox_logo.jpg" alt="logo" height="65">SixBox</h1>
    </div>
</header>
<div class="content">
    <div class="login-register-container">
        <img src="${pageContext.request.contextPath}/static/images/index_img.png" alt="index_img">
        <div class="login-register">
            <div class="info-page">
                <div class="info-header">创建账户</div>
                <div class="login-register-switch"><span>或</span><a href="/index.jsp">登录</a></div>
            </div>
            <!-- Register Form -->
            <div id="register-page" class="container-fluid">
                <div class="row">
                    <form action="<s:url action="register" namespace="/user"/>" method="post" id="registerForm">
                        <div class="form-group">
                            <label>Username</label>
                            <input type="text" class="form-control" placeholder="Username" name="username">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" placeholder="Password" name="password">
                        </div>
                        <div class="form-group">
                            <label>Retype Password</label>
                            <input type="password" class="form-control" placeholder="Confirm Password" name="confirmPassword">
                        </div>
                        <button type="submit" class="btn btn-info" id="btntest">注册</button>
                    </form>
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
<script src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src=https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js></script>
<script src="${pageContext.request.contextPath}/static/js/indexScript.js"></script>
</body>
</html>

