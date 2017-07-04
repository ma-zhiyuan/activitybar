<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>MSG</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br>
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">消息</h3>
			</div>
			<div class="panel-body">
				<div class="alert alert-info" role="alert">
					<div align="center">
						&nbsp;${requestScope.msg}&nbsp;--><a href="index.jsp">回首页</a>
					</div>
				</div>
			</div>
		</div>
		<br>
	</div>

	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
