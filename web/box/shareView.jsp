<%@ page import="org.sos.sixbox.entity.FileEntity" %>
<%@ page import="org.sos.sixbox.entity.FolderEntity" %>
<%@ page import="org.sos.sixbox.entity.ShareEntity" %>
<%@ page import="org.sos.sixbox.entity.UserEntity" %>
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
<s:action name="ShareDetail" namespace="/box"/>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("/index.jsp");
    }
    ShareEntity share = (ShareEntity) request.getAttribute("shareEntity");
    UserEntity sharedBy = (UserEntity) request.getAttribute("sharedBy");
%>
<!DOCTYPE html>
<html>
<head>
    <title><%=share.getCaption()%> - SixBox</title>
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
                <li><a href="${pageContext.request.contextPath}/box/home.jsp">文件</a></li>
                <li><a href="${pageContext.request.contextPath}/box/share.jsp" style="color: #0070E0;">分享</a></li>
                <li><a href="${pageContext.request.contextPath}/box/recyclebin.jsp">回收站</a></li>
            </ul>
        </div>
    </div>
    <div class="main-page">
        <!-- Main Page Haeder -->
        <header class="main-page-header">
            <h1><%=sharedBy.getUsername()%>'s <%=share.getCaption()%>
            </h1>
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
                        <th class="checkbox-th"><input type="checkbox" onclick='swapCheck();'></th>
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
                                if (folder == null || folder.getName().startsWith(".")) continue;
                    %>
                    <tr>
                        <td style="display: none"><input type="hidden" value="<%=folder.getId()%>" name="fileId"></td>
                        <td class="checkbox-th"><input type="checkbox" value="<%=folder.getId()%>" name="folder"></td>
                        <td class="name-th">
                            <a href="shareView.jsp?pwd=<%=folder.getId()%>"><%=folder.getName()%>/
                            </a>
                        </td>
                        <td class="time-th"><%=Utils.getFormatDate(folder.getCreateTime())%>
                        </td>
                    </tr>
                    <%
                            }
                    %>
                    <%
                        List<FileEntity> files = (List<FileEntity>) request.getAttribute("files");
                        if (files != null)
                            for (FileEntity file : files) {
                                if (file == null) continue;
                    %>
                    <tr>
                        <td style="display: none"><input type="hidden" value="<%=file.getId()%>" name="fileId"></td>
                        <td class="checkbox-th"><input type="checkbox" value="<%=file.getId()%>" name="file"></td>
                        <td class="name-th"><a
                                href="<s:url action="DownloadFile" namespace="/box" />?fid=<%=file.getId()%>"><%=file.getFilename()%>
                        </a>
                        </td>
                        <td class="time-th"><%=Utils.getFormatDate(file.getUploadTime())%>
                        </td>
                    </tr>
                    <%
                            }
                    %>
                </table>
                <script>
                    function doDownload() {
                        var downFiles = [];
                        $("tr td input[type='checkbox'][name='file']:checked").each(function () {
                            downFiles.push($(this).val());
                        });
                        location.href = '<s:url action="DownloadMultiFile" namespace="/box"/>?fid=' + downFiles.join(",");
                    }

                    var isCheckAll = false;

                    function swapCheck() {
                        if (isCheckAll) {
                            $("tr td input[type='checkbox'][name='file']").each(function () {
                                this.checked = false;
                            });
                            isCheckAll = false;
                        } else {
                            $("tr td input[type='checkbox'][name='file']").each(function () {
                                this.checked = true;
                            });
                            isCheckAll = true;
                            $("li").each(function () {
                                this.setAttribute("aria-hidden", "false");
                            })
                        }
                    }
                </script>
                <div class="main-right">
                    <div class="action-menu">
                        <!-- 选中一个或多个文件的功能列表 -->
                        <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span><a
                            href="javascript:doDownload();">下载</a>
                    </div>
                </div>
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
