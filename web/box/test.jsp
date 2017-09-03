<%--
  Created by IntelliJ IDEA.
  User: Lodour
  Date: 2017/9/3
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.sos.sixbox.entity.FileEntity" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:action name="UploadFileList" namespace="/box"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <%
        List<FileEntity> fileEntityList = (List<FileEntity>) request.getAttribute("fileEntityList");
        if (fileEntityList != null) {
            for (FileEntity fileEntity : fileEntityList) {
    %>
    <tr>
        <td><%=fileEntity.getId()%>
        </td>
        <td><%=fileEntity.getFilename()%>
        </td>
        <td><%=fileEntity.getOwnerId()%>
        </td>
        <td><%=fileEntity.getUploadTime()%>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

<form action="<s:url namespace="/box" action="UploadFile"/>" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="Upload"/>
</form>
</body>
</html>
