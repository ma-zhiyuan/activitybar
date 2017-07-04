<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>modifyUserInfo</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="scripts/bootstrap.min.css">
<script type="text/javascript" src="scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>

</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<br>
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">修改个人信息</h3>
			</div>
			<div class="panel-body">
				<div class="container">
					<form action="user/modifyInfo" method="post">
						<div class="form-group">
							<label for="gender" class="col-sm-2 control-label">性别</label>
							<c:if test="${sessionScope.user.gender==1 }">
								<input type="text" id="gender" name="gender" value="男">
							</c:if>
							<c:if test="${sessionScope.user.gender==0 }">
								<input type="text" id="gender" name="gender" value="女">
							</c:if>
						</div>
						<div class="form-group">
							<label for="age" class="col-sm-2 control-label">年龄</label>
								<input type="text" id="age" name="age" value="${sessionScope.user.age }">
						</div>
						<input type="submit" class="btn btn-info" value="更新">
					</form>
				</div>
			</div>
		</div>
		<br>
	</div>

	<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
