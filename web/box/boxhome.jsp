<%--
  Created by IntelliJ IDEA.
  User: zc
  Date: 2017/7/8
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>文件 - SixBox</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/boxpage.css">
</head>
<body>
<div class="warp">
    <!-- 左侧导航栏 -->
    <div class="leftbar">
        <img src="../static/images/SixBox_logo_boxhome.jpg" alt="logo">
        <div class="nav-content">
            <ul class="nav-item">
                <%--<li>Username:<s:property value="#session.username" /></li>--%>
                <li><a href="/box/boxhome.jsp" style="color: #0070E0;">文件</a></li>
                <li><a href="">分享</a></li>
                <li><a href="/box/recyclebin.jsp">回收站</a></li>
            </ul>
        </div>
    </div>
    <div class="main-page">
        <!-- Main Page Haeder -->
        <header class="main-page-header">
            <h1>SixBox</h1>
            <div class="searchbox">
                <form class="form-inline">
                    <div class="form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" style="border-radius: 5px;" placeholder="搜索">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-search"></span></button>
                </form>
            </div>
            <div class="personalInfo">
                <a href="javascript:void(0);"><img src="../static/images/profile.jpg" alt="profileImg"></a>
                <div class="info-panel">
                    <div class="info-panel-warp">
                        <div class="detail-panel">
                            <img src="../static/images/profile.jpg" alt="profileImg">
                            <span><s:property value="#session.username" /></span>
                        </div>
                        <div class="storage-panel">
                            <span>已使用xxxMB(共 xx GB)</span>
                        </div>
                        <div class="action-panel">
                            <a href="#">设置</a>
                            <a href="<s:url namespace="/user" action="logout"/>">注销</a>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </div>

    <div class="main-page-bottom">
        <!-- 内容显示区域 -->
        <div class="main-middle">
            <div class="table-fixed">
                <table>
                    <tr>
                        <th class="checkbox-th"><input type="checkbox"></th>
                        <th class="name-th">名称</th>
                        <th class="time-th">创建时间</th>
                        <th class="button-th"></th>
                    </tr>
                </table>
            </div>
            <div class="table-content">
                <table>
                    <tr>
                        <td class="checkbox-th"><input type="checkbox"></td>
                        <td class="name-th">File Name 1</td>
                        <td class="time-th">2017-07-08</td>
                        <td class="button-th">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-list"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">下载</a></li>
                                    <li><a href="#">重命名</a></li>
                                    <li><a href="#">复制</a></li>
                                    <li><a href="#">移动</a></li>
                                    <li><a href="#">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="checkbox-th"><input type="checkbox"></td>
                        <td class="name-th">File Name 1</td>
                        <td class="time-th">2017-07-08</td>
                        <td class="button-th">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-list"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">下载</a></li>
                                    <li><a href="#">重命名</a></li>
                                    <li><a href="#">复制</a></li>
                                    <li><a href="#">移动</a></li>
                                    <li><a href="#">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="checkbox-th"><input type="checkbox"></td>
                        <td class="name-th">File Name 1</td>
                        <td class="time-th">2017-07-08</td>
                        <td class="button-th">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-list"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">下载</a></li>
                                    <li><a href="#">重命名</a></li>
                                    <li><a href="#">复制</a></li>
                                    <li><a href="#">移动</a></li>
                                    <li><a href="#">删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- 右侧操作栏 -->
        <div class="main-right">
            <div class="action-menu">
                <button type="button" class="btn btn-info btn-block">上传文件</button>
                <!-- 选中一个或多个文件的功能列表 -->
                <ul class="single-item-action">
                    <li><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span><a href="">下载</a></li>
                    <li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="">移动</a></li>
                    <li><span class="glyphicon glyphicon-copy" aria-hidden="true"></span><a href="">复制</a></li>
                    <li><span class="glyphicon glyphicon-trash" aria-hidden="true"></span><a href="">删除</a></li>
                    <li><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><a href="">重命名</a></li>
                </ul>
                <ul class="none-item-action">
                    <li><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><a href="">新建文件夹</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../static/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../static/js/boxScript.js"></script>
<script>
    $(function () {
        console.log("This is box home!");
        const myName="<%=session.getAttribute("username")%>";
        alert(myName);
    })
</script>
</body>
</html>
