<%@ page import="org.sos.sixbox.entity.FileEntity" %>
<%@ page import="org.sos.sixbox.entity.FolderEntity" %>
<%@ page import="org.sos.sixbox.utils.Utils" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zc
  Date: 2017/7/8
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("/index.jsp");
    }
%>
<s:action name="FileList" namespace="/box">
    <s:param name="isTrash" value="true"/>
</s:action>
<!DOCTYPE html>
<html>
<head>
    <title>回收站 - SixBox</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/boxpage.css">
</head>
<body>
<div class="wrap">
    <!-- 左侧导航栏 -->
    <div class="leftbar">
        <img src="../static/images/SixBox_logo_boxhome.jpg" alt="logo">
        <div class="nav-content">
            <ul class="nav-item">
                <li><a href="./home.jsp">文件</a></li>
                <li><a href="${pageContext.request.contextPath}/box/share.jsp">分享</a></li>
                <li><a href="./recyclebin.jsp" style="color: #0070E0;">回收站</a></li>
            </ul>
        </div>
    </div>
    <!-- Main Page -->
    <div class="main-page">
        <!-- Main Page Haeder -->
        <header class="main-page-header">
            <h1>回收站</h1>
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
                            <span>UserName</span>
                        </div>
                        <div class="storage-panel">
                            <span>已使用xxxMB(共 xx GB)</span>
                        </div>
                        <div class="action-panel">
                            <a href="${pageContext.request.contextPath}/user/userProfile.jsp">设置</a>
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
                        <th class="checkbox-th"><input type="checkbox" style="visibility: hidden"></th>
                        <th class="name-th">名称</th>
                        <th class="time-th">上传时间</th>
                        <th class="button-th"></th>
                    </tr>
                </table>
            </div>
            <div class="table-content">
                <table>
                    <%
                        List<FolderEntity> folders = (List<FolderEntity>) request.getAttribute("folders");
                        if (folders != null)
                            for (FolderEntity folder : folders) {
                                if (folder == null) continue;
                    %>
                    <tr>
                        <td class="checkbox-th"><input type="hidden" value="<%=folder.getId()%>"></td>
                        <td class="checkbox-th"><input type="checkbox" name="folder" value="<%=folder.getId()%>"></td>
                        <td class="name-th"><%=folder.getName()%>/
                        </td>
                        <td class="time-th"><%=Utils.getFormatDate(folder.getCreateTime())%>
                        </td>
                        <td class="button-th"></td>
                    </tr>
                    <% } %>
                    <%
                        List<FileEntity> files = (List<FileEntity>) request.getAttribute("files");
                        if (files != null)
                            for (FileEntity file : files) {
                    %>
                    <tr>
                        <td class="checkbox-th"><input type="hidden" value="<%=file.getId()%>"></td>
                        <td class="checkbox-th"><input type="checkbox" name="file" value="<%=file.getId()%>"></td>
                        <td class="name-th"><%=file.getFilename()%>
                        </td>
                        <td class="time-th"><%=Utils.getFormatDate(file.getUploadTime())%>
                        </td>
                        <td class="button-th"></td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
        <!-- 右侧操作栏 -->
        <div class="main-right">
            <div class="action-menu">
                <button type="button" class="btn btn-info btn-block" onclick="doClear();">清空回收站</button>
                <!-- 选中一个或多个文件的功能列表 -->
                <ul class="single-item-action" style="display: block;">
                    <li><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span><a
                            href="javascript:doRecover();">恢复</a></li>
                    <li><span class="glyphicon glyphicon-trash" aria-hidden="true"></span><a
                            href="javascript:doDelete();">删除</a></li>
                </ul>
            </div>
        </div>
        <script>
            function doRecover() {
                $("tr td input[type='checkbox']:checked").each(function () {
                    $.ajax({
                        url: '<s:url action="Recover" namespace="/box"/>',
                        type: 'POST',
                        data: {
                            'fid': $(this).val(),
                            'type': $(this).attr("name")
                        },
                        success: function () {
                            $("tr:has(input:checked)").css("display", "none");
                        }
                    });
                });
            }

            function doDelete() {
                $("tr td input[type='checkbox']:checked").each(function () {
                    $.ajax({
                        url: '<s:url action="Delete" namespace="/box"/>',
                        type: 'POST',
                        data: {
                            'fid': $(this).val(),
                            'type': $(this).attr("name")
                        },
                        success: function () {
                            $("tr:has(input:checked)").css("display", "none");
                        }
                    });
                });
            }

            function doClear() {
                $("tr td input[type='checkbox']").each(function () {
                    $.ajax({
                        url: '<s:url action="Delete" namespace="/box"/>',
                        type: 'POST',
                        data: {
                            'fid': $(this).val(),
                            'type': $(this).attr("name")
                        },
                        success: function () {
                            $("tr:has(input[type='checkbox])").css("display", "none");
                        }
                    });
                });
            }
        </script>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../static/js/jquery-3.2.1.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../static/js/binScript.js"></script>
</body>
</html>
