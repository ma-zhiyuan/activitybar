<%@ page language="java"
	import="java.util.*,me.ifma.activitybar.entity.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>MyActivity</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="scripts/style.css">
<link rel="stylesheet" type="text/css" href="scripts/bootstrap.min.css">
<script type="text/javascript" src="scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>

</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<%
		User u = ((User)request.getSession().getAttribute("user"));
		
	 %>
	<br>
	<div class="col-md-10 col-md-offset-1">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">
					我的活动&nbsp;<span class="badge"><%=u.getItems().size() %></span>
				</h3>
			</div>
			<div class="panel-body">
				<c:forEach items="${sessionScope.user.items }" var="activity">
					<div class="wrap">
						<div class="content-gallery">
							<div class="boxgrid caption">
								<a href="activity/detail?id=${activity.id}" target="_BLANK"><img
									src="${activity.surface}" /></a>
								<div class="cover boxcaption">
									<h3>${activity.name}</h3>
									<p>
										${activity.place}<br /> <a
											href="activity/detail?id=${activity.id}" target="_BLANK">More
											Detail</a>
									</p>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<br>
	</div>

	<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
