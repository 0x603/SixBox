<%@ page import="org.sos.sixbox.entity.FileEntity" %>
<%@ page import="org.sos.sixbox.entity.FolderEntity" %>
<%@ page import="org.sos.sixbox.utils.Utils" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: zc
  Date: 2017/7/8
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("/index.jsp");
    }
%>
<s:action name="FileList" namespace="/box"/>
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
        <a href="index.jsp"><img src="../static/images/SixBox_logo_boxhome.jpg" alt="logo"></a>
        <div class="nav-content">
            <ul class="nav-item">
                <%--<li>Username:<s:property value="#session.username" /></li>--%>
                <li><a href="${pageContext.request.contextPath}/box/home.jsp" style="color: #0070E0;">文件</a></li>
                <li><a href="">分享</a></li>
                <li><a href="${pageContext.request.contextPath}/box/recyclebin.jsp">回收站</a></li>
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
                            <span><s:property value="#session.username"/></span>
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
                        <th class="name-th">文件名</th>
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
                    %>
                    <tr>
                        <td style="display: none"><input type="hidden" value="<%=folder.getId()%>" name="fileId"></td>
                        <td class="checkbox-th"><input type="checkbox"></td>
                        <td class="name-th">
                            <a href="home.jsp?pwd=<%=folder.getId()%>"><%=folder.getName()%>
                            </a>
                        </td>
                        <td class="time-th"><%=Utils.getFormatDate(folder.getCreateTime())%>
                        </td>
                        <td class="button-th">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false"><span
                                        class="glyphicon glyphicon-list"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <%-- TODO: 用户输入新的文件名 --%>
                                    <li>
                                        <a href="<s:url action="RenameFolder" namespace="/box"/>?fid=<%=folder.getId()%>&name=test&type=folder">重命名</a>
                                    </li>
                                    <li><a href="#">复制</a></li>
                                    <li><a href="#">移动</a></li>
                                    <li>
                                        <a href="<s:url action="MoveToTrash" namespace="/box"/>?fid=<%=folder.getId()%>&type=folder">删除</a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <%
                            }
                    %>
                    <%
                        List<FileEntity> files = (List<FileEntity>) request.getAttribute("files");
                        if (files != null)
                            for (FileEntity file : files) {
                    %>
                    <tr>
                        <td style="display: none"><input type="hidden" value="<%=file.getId()%>" name="fileId"></td>
                        <td class="checkbox-th"><input type="checkbox"></td>
                        <td class="name-th"><a
                                href="<s:url action="DownloadFile" namespace="/box" />?fid=<%=file.getId()%>"><%=file.getFilename()%>
                        </a>
                        </td>
                        <td class="time-th"><%=Utils.getFormatDate(file.getUploadTime())%>
                        </td>
                        <td class="button-th">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false"><span
                                        class="glyphicon glyphicon-list"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="<s:url action="DownloadFile" namespace="/box" />?fid=<%=file.getId()%>">下载</a>
                                    </li>
                                    <%-- TODO: 用户输入新文件名 --%>
                                    <li>
                                        <a href="<s:url action="RenameFolder" namespace="/box"/>?fid=<%=file.getId()%>&name=test.txt&type=file">重命名</a>
                                    </li>
                                    <li><a href="#">复制</a></li>
                                    <li><a href="#">移动</a></li>
                                    <li>
                                        <a href="<s:url action="MoveToTrash" namespace="/box"/>?fid=<%=file.getId()%>&type=file">删除</a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <%
                            }
                    %>
                </table>
            </div>
        </div>
        <!-- 右侧操作栏 -->
        <div class="main-right">
            <div class="action-menu">
                <button type="file" class="btn btn-info btn-block" data-toggle="modal" data-target="#myModal">上传文件
                </button>
                <!-- 选中一个或多个文件的功能列表 -->
                <ul class="single-item-action">
                    <li><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span><a href="">下载</a></li>
                    <li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="">移动</a></li>
                    <li><span class="glyphicon glyphicon-copy" aria-hidden="true"></span><a href="">复制</a></li>
                    <li><span class="glyphicon glyphicon-trash" aria-hidden="true"></span><a href="">删除</a></li>
                    <li><span class="glyphicon glyphicon-edit" aria-hidden="true"></span><a href="">重命名</a></li>
                </ul>
                <ul class="none-item-action">
                    <li><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span><a
                            href="<s:url action="CreateFolder" namespace="/box" />?fid=<%=request.getParameter("pwd")%>">新建文件夹</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">上传文件</h4>
            </div>
            <div class="modal-body">
                <form action="<s:url action="UploadFile" namespace="/box"/>" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="fileUpload">File input</label>
                        <input type="file" id="fileUpload" name="file">
                        <input type="hidden" name="pwd" value="${pageContext.request.getParameter("pwd")}"/>
                    </div>
                    <button type="submit" class="btn btn-default">上传</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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

    })
</script>
</body>
</html>
