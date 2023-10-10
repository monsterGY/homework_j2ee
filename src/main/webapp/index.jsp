<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>软院找人</title>
</head>
<body>
<%--  设置表单提交,提交方式为post--%>
<form action="${pageContext.request.contextPath}/find" method="post">
    <%--段落标签--%>
    <div style="display:flex; justify-content: center; align-items: start; height: 100vh; padding-top: 200px; font-size: 19px">
        <p>请输入:<input type="text" name="param" style="margin-left: 20px; border-radius: 7px; text-indent: 13px ">
            <input type="submit" value="提交" >
        </p>
    </div>

</form>
</body>
</html>