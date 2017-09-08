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
                <li><a href="${pageContext.request.contextPath}/box/share.jsp">分享</a></li>
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
            <div class="table-content">
                <table>
                    <tr>
                        <th class="checkbox-th"><input type="checkbox" style="visibility: hidden"></th>
                        <th class="name-th">文件名</th>
                        <th class="name-th">文件大小</th>
                        <th class="time-th">上传时间</th>
                        <th class="button-th"></th>
                    </tr>
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
                            <a href="home.jsp?pwd=<%=folder.getId()%>"><%=folder.getName()%>/</a>
                        </td>
                        <td class="name-th">-</td>
                        <td class="time-th"><%=Utils.getFormatDate(folder.getCreateTime())%>
                        </td>
                        <td class="button-th">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false"><span
                                        class="glyphicon glyphicon-list"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="javascript:doRename('folder', '<%=folder.getId()%>');">重命名</a>
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
                                if (file == null) continue;
                    %>
                    <tr>
                        <td style="display: none"><input type="hidden" value="<%=file.getId()%>" name="fileId"></td>
                        <td class="checkbox-th"><input type="checkbox" value="<%=file.getId()%>" name="file"></td>
                        <td class="name-th"><a
                                href="<s:url action="DownloadFile" namespace="/box" />?fid=<%=file.getId()%>"><%=file.getFilename()%>
                        </a>
                        <td class="name-th"><span id="<%=file.getId()%>"></span></td>
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
                                    <li>
                                        <a href="javascript:doRename('file', '<%=file.getId()%>');">重命名</a>
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
        <script>
            function test() {
                $("td[class='name-th'] span").each(function () {
                    var a = $(this);
                    $.ajax({
                        url: '<s:url action="GetFileSize" namespace="/box"/>',
                        data: {
                            'fid': $(this).attr("id")
                        },
                        success: function (data) {
                            a.text(data);
                        }
                    });
                });
            }

            function doRename(type, id) {
                var name = prompt("请输入新文件名", "新文件名...");
                $.ajax({
                    url: '<s:url action="RenameFolder" namespace="/box"/>',
                    type: 'POST',
                    data: {
                        'fid': id,
                        'type': type,
                        'name': name
                    },
                    success: function () {
                        name = type === 'folder' ? name + '/' : name;
                        $("td[class='name-th'] a[href$='" + id + "']").text(name);
                    }
                });
            }

            function doDelete() {
                $("tr td input[type='checkbox']:checked").each(function () {
                    $.ajax({
                        url: '<s:url action="MoveToTrash" namespace="/box"/>',
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

            function doShare() {
                var sharedFolders = [];
                $("tr td input[type='checkbox'][name='folder']:checked").each(function () {
                    sharedFolders.push($(this).val());
                });
                var sharedFiles = [];
                $("tr td input[type='checkbox'][name='file']:checked").each(function () {
                    sharedFiles.push($(this).val());
                });
                $.ajax({
                    url: '<s:url action="Share" namespace="/box"/>',
                    type: 'POST',
                    data: {
                        'sharedFolders': sharedFolders.join(","),
                        'sharedFiles': sharedFiles.join(","),
                        'caption': prompt("请输入分享名", "我的分享")
                    },
                    success: function (shareId) {
                        var url = "http://localhost:8080/box/ShareDetail.action?shareId=" + shareId.replace(/\s/g, "");
                        prompt("分享成功！链接如下: ", url);
                    }
                });
            }

            function doDownload() {
                var downFiles = [];
                $("tr td input[type='checkbox'][name='file']:checked").each(function () {
                    downFiles.push($(this).val());
                });
                location.href = '<s:url action="DownloadMultiFile" namespace="/box"/>?fid=' + downFiles.join(",");
            }
        </script>
        <div class="main-right">
            <div class="action-menu">
                <button type="file" class="btn btn-info btn-block" data-toggle="modal" data-target="#myModal">上传文件
                </button>
                <!-- 选中一个或多个文件的功能列表 -->
                <ul class="single-item-action">
                    <li><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span><a
                            href="javascript:doDownload();">下载</a></li>
                    <li><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span><a href="">移动</a></li>
                    <li><span class="glyphicon glyphicon-copy" aria-hidden="true"></span><a href="">复制</a></li>
                    <li><span class="glyphicon glyphicon-trash" aria-hidden="true"></span><a
                            href="javascript:doDelete();">删除</a></li>
                    <li><span class="glyphicon glyphicon-share" aria-hidden="true"></span><a
                            href="javascript:doShare();">分享</a></li>
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
                        <input type="file" id="fileUpload" name="file" multiple>
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
        test();
    })
</script>
</body>
</html>
