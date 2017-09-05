<%--
  Created by IntelliJ IDEA.
  User: zc
  Date: 2017/8/28
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户 - SixBox</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/userProfile.css">
</head>
<body>
<div class="wrap">
    <header>
        <img src="${pageContext.request.contextPath}/static/images/SixBox_logo.jpg" alt="logo">
    </header>
    <h3>账户设置</h3>
    <div class="account-info">
        <span class="title">个人资料</span>
        <h4>用户名:&nbsp;&nbsp;<span>Felix</span></h4>
        <h4>性别：&nbsp;&nbsp;<span>男</span></h4>
        <div class="profile-photo">
            <img src="${pageContext.request.contextPath}/static/images/profile.jpg" alt="profile"><a href="">更改头像</a>
        </div>
        <h4>电子邮箱:&nbsp;&nbsp;<span>felixzhangc@yeah.net</span></h4>
        <h4>个人介绍:<br><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto delectus placeat deleniti, rerum maiores temporibus, velit possimus deserunt minima. Quia tempore, officiis aspernatur molestiae suscipit voluptatum provident, illum temporibus dolorem!</span></h4>
        <button type="button" class="btn btn-info">修改个人信息</button>
    </div>
    <div class="account-storage">
        <span class="title">账户</span>
        <h4>存储空间</h4>
        <div class="account-email">
            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="min-width: 2em;">0%</div>
            </div>
            <h4>已使用xxxMB(共 xx GB)</h4>
        </div>
    </div>
    <div class="account-security">
        <span class="title">安全</span>
        <!-- Contextual button for informational alert messages -->
        <button type="button" class="btn btn-info">修改密码</button>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
