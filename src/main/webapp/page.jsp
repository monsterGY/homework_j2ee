
<%@ page import="second.Page" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2023/9/19
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>分页查询</title>
</head>
<body>

<h1>分页查询</h1>
<h4>符合查询的总记录数量为：${page.total}</h4>
<h4>当页显示的数量为：${page.count}</h4>
<table border="1pm">
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>班级</th>
        <th>移动电话</th>
        <th>邮箱</th>
    </tr>
    <c:forEach items="${res}" var="stu" begin="${page.currentPage}" end="${page.end}">
        <tr>
            <td>${stu.id}</td>
            <td>${stu.name}</td>
            <td>${stu.gender}</td>
            <td>${stu.strClass}</td>
            <td>${stu.mobile}</td>
            <td>${stu.email}</td>
        </tr>
    </c:forEach><br>
</table>
<%
    Page page_ = (Page) request.getAttribute("page");
    int currentPage = page_.getCurrentPage();
    String p = request.getParameter("param");
    request.setAttribute("p", p);
    request.setAttribute("preCur", currentPage - page_.getCount());
    request.setAttribute("nextCur", currentPage + page_.getCount());
%>
<%--首页--%>
<a href="${pageContext.request.contextPath}/find?param=${p}&currentPage=0&count=${page.count}">首页 </a>

<%--上一页--%>
<a href="${pageContext.request.contextPath}/find?param=${p}&currentPage=${preCur}&count=${page.count}">上一页 </a>



<%--下一页--%>
<a href="${pageContext.request.contextPath}/find?param=${p}&currentPage=${nextCur}&count=${page.count}">下一页 </a>

<%--尾页--%>
<a href="${pageContext.request.contextPath}/find?param=${p}&currentPage=${page.total-page.count}&count=${page.count}">尾页 </a>

每页显示页数： <a href="${pageContext.request.contextPath}/find?param=${p}&currentPage=0&count=5" >5 </a>
<a href="${pageContext.request.contextPath}/find?param=${p}&currentPage=0&count=10">10 </a>
<a href="${pageContext.request.contextPath}/find?param=${p}&currentPage=0&count=15">15 </a>
<a href="${pageContext.request.contextPath}/find?param=${p}&currentPage=0&count=20">20 </a>

</body>

</html>