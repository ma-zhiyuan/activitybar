<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>confirmCapcha</title>

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
				<h3 class="panel-title">验证码</h3>
			</div>
			<div class="panel-body">
				<div class="alert alert-info" role="alert">
					<div align="center">
						<div class="container col-md-5 col-md-offset-4">
							<form action="user/confirmEmail" method="get">
								<b>请输入验证码:</b> <input type="text" name="capcha" size="10">
								<input class=" btn btn-info" type="submit" value="验证">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<br>
	</div>

	<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
