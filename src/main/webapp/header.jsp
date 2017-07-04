<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>ActivityBar</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<link rel="stylesheet" type="text/css" href="scripts/bootstrap.min.css">
<script type="text/javascript" src="scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#regname").blur(function(){
			var name = $(this).val();
			name=$.trim(name);
			var url = "user/verifyName";
			var args={"time":new Date(),"name":name};
			$.get(url,args,function(data){
				$("#regname-msg").text(data);
			});
		});
		$("#regist-btn").click(function(){
			var pwd = $("#regpassword").val();
			var pwd2 = $("#regpassword2").val();
			if(pwd!=pwd2){
				alert("两次密码不一致!");
				return false;
			}
			if(pwd2.length < 8){
				alert("长度不能小于8");
				return false;
			}
		});
	});
</script>
</head>

<body>
	<nav class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">ActivityBar</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="admin.jsp">管理员登陆</a></li>
					<li><a href="#" data-toggle="modal" data-target="#about-modal">关于</a></li>
				</ul>
				<!-- 登陆信息 -->
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${empty sessionScope.user }">
						<li><a href="" class="btn" data-toggle="modal" data-target="#loginWindow">登陆</a></li>
						<li><a href="" class="btn" data-toggle="modal" data-target="#registWindow">注册</a></li>
					</c:if>
					<c:if test="${!empty sessionScope.user }">
						<li><a href="view/info.jsp" class="btn" data-toggle="modal" ><img src="img/yonghu.png" width="17px" height="17px">欢迎您，${sessionScope.user.name }</a></li>
						<li><a href="user/showMyActivity">我的活动</a></li>
						<li><a href="user/logout" class="btn" data-toggle="modal" >退出</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>

	<!-- 登陆框 -->
	<div class="modal fade" id="loginWindow" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">用户登录</h4>
				</div>
				<div class="modal-body  col-md-offset-2">
					<!-- 用户登录表单 -->
					<form action="user/login" method="post" role="form" id="login" class="form-horizontal">
						<div class="form-group">
						    <label for="name" class="col-sm-2 control-label">用户名</label>
						    <div class="col-sm-6">
					    		<input name="name" type="text" class="form-control" id="name" placeholder="用户名">
						    </div>
						</div>
						<div class="form-group">
						  	  <label for="password" class="col-sm-2 control-label">密码</label>
							   <div class="col-sm-6">
						    		<input name="password" type="password" class="form-control" id="password" placeholder="密码">
						    </div>
				   		</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit"  form="login" class="btn btn-primary">登录</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 注册框 -->
	<div class="modal fade" id="registWindow" tabindex="-1" role="dialog"
		aria-labelledby="registForm" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="registForm">用户注册</h4>
				</div>
				<div class="modal-body col-md-offset-0">
					<!-- 用户注册表单 -->
					<form action="user/regist" method="post" role="form" id="regist" class="form-horizontal">
						<div class="form-group">
						    <label for="regname" class="col-sm-2 control-label">用户名</label>
						    <div class="col-sm-5">
					    		<input name="regname" type="text" class="form-control" id="regname" placeholder="用户名">
						    </div>
						    <label for="regname" class="col-sm-4 control-label" id="regname-msg"></label>
						</div>
						<div class="form-group">
						  	  <label for="regpassword" class="col-sm-2 control-label">密码</label>
							   <div class="col-sm-5">
						    		<input name="regpassword" type="password" class="form-control" id="regpassword" placeholder="密码">
						    </div>
				   		</div>
						<div class="form-group">
						  	  <label for="regpassword" class="col-sm-2 control-label">确认密码</label>
							   <div class="col-sm-5">
						    		<input name="regpassword2" type="password" class="form-control" id="regpassword2" placeholder="确认密码">
						    </div>
				   		</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit"  form="regist" class="btn btn-primary" id="regist-btn">注册</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="about-modal" tabindex="-1" role="dialog" aria-labelledby="modal-label" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
						<h4 class="modal-title" id="modal-label">关于</h4>
					</div>
					<div class="modal-body">
						<div class="col-center-block">
							<p style="text-indent: 2em;"><b>ActivityBar是针对大学生在校活动的日益丰富，单调线下的活动日程安排目前已经不能满足大学生的需求的情况设计而成。</b></p>
							<br />
							<p style="text-indent: 2em;"> <b>本系统包括WEB端和微信公众号端。前端页面基于框架BootStrap设计，具有响应式结构布局，对各种设备予以适配，界面美观、易用；后台使用JAVAEE相关技术、Spring、SpringMVC、Mybatis等框架技术，数据库使用MySql数据库进行数据存储处理，快速、高效。</b></p>
							<br />
							<p style="text-indent: 2em;"><b>如果在使用中遇到疑惑或Bug欢迎联系我们～</b></p>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
