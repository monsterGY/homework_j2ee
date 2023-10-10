<%@page import="dao.GetUserDao"%>
<%@page import="second.Student"%>
<%@page import="second.Admin"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="managePageBean" class="second.PageBean" scope="session"/>
<jsp:setProperty name="managePageBean" property="*"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
	<link rel="stylesheet" href="css/page.css"/>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="http://img.htmlsucai.com/cdn/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/page.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>

<body>
    <a href="index.html">返回</a>
	<%! private static final int MAX_COUNT = 10; %>
	<% 
		int pageNum = 0;
		String parameter = request.getParameter("currentPageNum");
		if(parameter==null){
			pageNum = 0;
		}else{
			pageNum = Integer.parseInt(parameter);
		}
		request.getSession().setAttribute("current_page", pageNum);
		
		String currId = (String)request.getSession().getAttribute("currID");
	%>
    <div class="toolbar" >
        <!-- 可按不同信息进行综合搜索 -->
        <div style="float: left; margin-left: 100px; margin-top: 10px; margin-bottom: 10px;">
            <form class="form-inline" action="${pageContext.request.contextPath }/searchInfoServlet" method="get">
                <div style="margin-left: 8px" class="form-group">
                    <label><b>学号</b></label>
                    <input name="search_id" style=" margin-left: 10px" type="text" class="form-control" />
                </div>
                <div style="margin-left: 8px" class="form-group">
                    <label><b>姓名</b></label>
                    <input name="search_name" style=" margin-left: 10px" type="text" class="form-control" />
                </div>
                <div style="margin-left: 8px" class="form-group">
                    <label><b>性别</b></label>
                    <input name="search_gender" style=" margin-left: 10px" type="text" class="form-control" />
                </div>
                <div style="margin-left: 8px" class="form-group">
                    <label><b>班级</b></label>
                    <input name="search_grade" style=" margin-left: 10px" type="text" class="form-control" />
                </div>
                <div style="margin-left: 8px" class="form-group">
                    <label><b>电话</b></label>
                    <input name="search_mobile" style=" margin-left: 10px" type="text" class="form-control" />
                </div>
                <div style="margin-left: 8px" class="form-group">
                    <label><b>邮箱</b></label>
                    <input name="search_email" style=" margin-left: 10px" type="text" class="form-control" />
                </div>
                <button id="searchSubmitBtn" style="margin-left: 8px;" type="submit"
                        class="btn btn-primary">查找</button>
                <button id="photoSearchBtn" onclick="searchByPhoto()" style="margin-left: 8px;" type="button"
                        class="btn btn-primary">图片查找</button>
                <button id="addNewBtn" onclick="addNew()" style="margin-left: 8px;" type="button"
                    class="btn btn-primary">添加新人</button>
            </form>
        </div>
    </div>

    <!-- 表单的具体内容 -->
    <div style="margin: auto; width: 90%;">
        <table id="info_table" class="table table-hover table-bordered">
            <thead>
            	<tr>
	                <th data-field="mat_id" data-visible="false">学号</th>
	                <th data-field="mat_name">姓名</th>
                    <th data-field="mat_gender">性别</th>
	                <th data-field="mat_grade">班级</th>
	                <th data-field="mat_mobile">电话</th>
	                <th data-field="mat_email">邮箱</th>
	                <th data-field="mat_option">操作</th>
	            </tr> 
            </thead>
            <tbody>
            	<%
            		List<Student> list = (List<Student>)request.getSession().getAttribute("ResultList");
            		if(list!=null){
            			request.getSession().setAttribute("num", list.size());
            		}else{
            			request.getSession().setAttribute("num", 0);
            		}
            	
            	%>
            	<c:if test="${num>=1 }" var="flag2" scope="session">
            		<c:if test="${num<=10 }" var="flag3" scope="session">
            			<!-- 不分页显示 -->
            			<c:forEach begin="0" end="${num-1 }" var="i">
		            		<tr>
			            		<td>${ResultList[i].id }</td>
			            		<td>${ResultList[i].name }</td>
                                <td>${ResultList[i].gender }</td>
                                <td>${ResultList[i].grade }</td>
			            		<td>${ResultList[i].mobile }</td>
			            		<td>${ResultList[i].email }</td>
			            		<td>
			            			<button id="modifyBtn" class='btn btn-success' onclick="modify(${i+1})">修改</button>
			            			<button id="delBtn" style='margin-left: 10px;' type='button' class='btn btn-danger' onclick="del(${i+1})">删除</button>
			            		</td>
			            	</tr>
		            	</c:forEach>
            		</c:if>
            		<c:if test="${not flag3 }">
            			<!-- 分页显示 -->
            			<%
            				managePageBean.setTotalNum((int)request.getSession().getAttribute("num"));
	            			managePageBean.setCurrentPageNum((int)request.getSession().getAttribute("current_page"));
            				managePageBean.showInfo();
            				managePageBean.pageStrategy();
            				String SessionID="";
            			%>
            			<c:forEach begin="<%=managePageBean.getStart() %>" end="<%= managePageBean.getEnd() %>" var="i">
		            		<tr>
                                <td>${ResultList[i].id }</td>
                                <td>${ResultList[i].name }</td>
                                <td>${ResultList[i].gender }</td>
                                <td>${ResultList[i].grade }</td>
                                <td>${ResultList[i].mobile }</td>
                                <td>${ResultList[i].email }</td>
			            		<td>
			            			<button id="modifyBtn" class='btn btn-success' onclick="modify(${(i+1)-current_page*10})">修改</button>
			            			<button id="delBtn" style='margin-left: 10px;' type='button' class='btn btn-danger' onclick="del(${(i+1)-current_page*10})">删除</button>
			            		</td>
			            	</tr>
			            	
		            	</c:forEach>
            		</c:if>
	            </c:if>
	            <c:if test="${not flag2 }">
	            	<div style="text-align: center; padding-top:200px; margin-bottom:500px;">
	            		<img alt="图片显示失败" src="img/no_result.jpg">
	            		<h3>查询无果</h3>
	            	</div>
	            </c:if>
            </tbody>
        </table>
    </div>
    <!-- 分页按钮 -->
	<c:if test="${flag2 and not flag3 }">
		<div style="float:right; margin-right: 100px;">
			<ul class="page" maxshowpageitem="5" pagelistcount="<%=MAX_COUNT %>"  id="page"></ul>
		</div>
	</c:if>
	
	<!-- 新增个人信息 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addMatModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">新增个人信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- 提交表单到后台 -->
                    <form role="form" action="${pageContext.request.contextPath }/addInfoServlet" method="post">
                        <div class="form-group">
                            <label class="control-label">学号</label>
                            <input name="add_id" class="form-control" type="text" placeholder="请输入学号">
                        </div>
                        <div class="form-group">
                            <label class="control-label">姓名</label>
                            <input name="add_name" class="form-control" type="text" placeholder="请输入姓名">
                        </div>
                        <div class="form-group">
                            <label class="control-label">性别</label>
                            <input name="add_gender" class="form-control" type="text" placeholder="请输入性别">
                        </div>
                        <div class="form-group">
                            <label class="control-label">班级</label>
                            <input name="add_grade" class="form-control" type="text" placeholder="请输入班级">
                        </div>
                        <div class="form-group">
                            <label class="control-label">电话</label>
                            <input name="add_mobile" class="form-control" type="text" placeholder="请输入电话">
                        </div>
                        <div class="form-group">
                            <label class="control-label">email</label>
                            <input name="add_email" class="form-control" type="text" placeholder="请输入电子邮箱">
                        </div>
                        <div class=" modal-footer">
		                    <button type="submit" class="btn btn-success">提交</button>
		                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
		                </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

	<!-- 修改个人信息 -->
	<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="modifyMatModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">修改个人信息</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <!-- 提交表单到后台 -->
                    <form role="form" action="${pageContext.request.contextPath }/modifyInfoServlet" method="post">
                        <div class="form-group">
                            <label class="control-label">学号(无法修改)</label>
                            <input id="id" name="id" class="form-control" type="text" readonly="readonly">
                        </div>
                        <div class="form-group">
                            <label class="control-label">姓名</label>
                            <input id="name" name="name" class="form-control" type="text" placeholder="修改后的姓名">
                        </div>
                        <div class="form-group">
                            <label class="control-label">性别</label>
                            <input id="gender" name="gender" class="form-control" type="text" placeholder="修改后的性别">
                        </div>
                        <div class="form-group">
                            <label class="control-label">班级</label>
                            <input id="grade" name="grade" class="form-control" type="text" placeholder="修改后的班级">
                        </div>
                        <div class="form-group">
                            <label class="control-label">电话</label>
                            <input id="mobile" name="mobile" class="form-control" type="text" placeholder="修改后的电话">
                        </div>
                        <div class="form-group">
                            <label class="control-label">邮箱</label>
                            <input id="email" name="email" class="form-control" type="text" placeholder="修改后的邮箱">
                        </div>
                        <div class=" modal-footer">
		                    <button type="submit" class="btn btn-success">保存</button>
		                    <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
		                </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 删除的信息提示 -->
    <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">确认删除</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <label class="control-label" id="del_label"></label>
                    <label id="dia_mat_del_id" class="control-label" data-visible="false" hidden='true'></label>
                </div>
                <div class="modal-footer">
                   	<input id="del_id" name="del_id" value="" type="hidden">
                   	<button type="submit" class="btn btn-success" data-dismiss="modal" onclick="del(-1)">删除</button>
                   	<button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 高级查找(图片) -->
    <div class="modal fade" id="photoSearchModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">选择照片</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath }/photoSearchServlet">
                        <input class="form-control" type="file" accept="image/png, image/jpeg,image/jpg" name="filePath"/>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success" >查找</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    
    
    <script type="text/javascript">
	    function chat(){
			$("#chatModal").modal("show");
		}
    	function addNew() {
			$("#addModal").modal("show");
		}
    	function modify(num){
    		// 页内传值
    		var table = document.getElementById("info_table");
    		var row = table.rows[num];
    		// 获取表格中对应该行的数据
    		var id = row.cells[0].innerHTML;
    		var name = row.cells[1].innerHTML;
            var gender = row.cells[2].innerHTML;
            var grade = row.cells[3].innerHTML;
    		var mobile = row.cells[4].innerHTML;
    		var email = row.cells[5].innerHTML;
    		// 向模态框中传值
    		$("#id").val(id);
    		$("#name").val(name);
            $("#gender").val(gender);
            $("#grade").val(grade);
    		$("#mobile").val(mobile);
    		$("#email").val(email);
    		
    		$("#modifyModal").modal("show");
    	}
    	function del(num){
    		if(num==-1){
    			var id = $("#del_id").val();
    			alert(id+",删除成功！");
    			// 跳转到处理的Servlet界面进行删除
    			window.location.href = "${pageContext.request.contextPath }/deleteInfoServlet?del_id="+id;
    		}
    		// 将学号传入隐藏的input中
    		var table = document.getElementById("info_table");
    		var row = table.rows[num];
    		var id = row.cells[0].innerHTML;
    		$("#del_id").val(id);   
    		// 动态显示提示的删除信息
    		var label = document.getElementById("del_label");
    		label.innerHTML = '确认删除学号为<i>'+id+'</i>的信息吗？';
    		$("#delModal").modal("show");
    	}
    	function searchByPhoto(){
    		$("#photoSearchModal").modal("show");
    	}
    </script>
</body>

</html>

<script type="text/javascript">
    function forward(page){
    	// page是下一次要跳转的页（从1开始）
        alert(page);
        window.location.href="${pageContext.request.contextPath }/infoManager.jsp?currentPageNum="+(page-1);
    }

    $("#page").initPage(${num},${current_page+1},forward);
</script>