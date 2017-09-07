<%@ page import="org.sos.sixbox.entity.ShareEntity" %>
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
<s:action name="ShareList" namespace="/box"/>
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
            <div class="table-fixed">
                <table>
                    <tr>
                        <th class="checkbox-th"><input type="checkbox" style="visibility: hidden"></th>
                        <th class="name-th">分享名</th>
                        <th class="time-th">分享时间</th>
                        <th class="button-th"></th>
                    </tr>
                </table>
            </div>
            <div class="table-content">
                <table>
                    <%
                        List<ShareEntity> shares = (List<ShareEntity>) request.getAttribute("shareList");
                        if (shares != null)
                            for (ShareEntity share : shares) {
                                if (share == null) continue;
                    %>
                    <tr>
                        <td style="display: none"><input type="hidden" value="<%=share.getId()%>" name="fileId"></td>
                        <td class="checkbox-th"><input type="checkbox" value="<%=share.getId()%>"></td>
                        <td class="name-th">
                            <a href="<s:url action="ShareDetail" namespace="/box"/>?shareId=<%=share.getId()%>"><%=share.getCaption()%>
                            </a>
                        </td>
                        <td class="time-th"><%=Utils.getFormatDate(share.getCreateTime())%>
                        </td>
                    </tr>
                    <%
                            }
                    %>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
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
