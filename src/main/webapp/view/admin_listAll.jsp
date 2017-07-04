<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>显示所有</title>

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
	<jsp:include page="admin-index.jsp"></jsp:include>
	<div class="container">
		<c:if test="${empty requestScope.activitys }">
			<div>您给的查询条件太多啦！</div>
		</c:if>
		<div class="container">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>活动名</th>
						<th>时间</th>
						<th>地点</th>
						<th>人数</th>
						<th>费用</th>
						<th>联系人</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.activitys }" var="activity">
						<tr>
							<td>${activity.id}</td>
							<td>${activity.name }</td>
							<td><fmt:formatDate value="${activity.date}"
								pattern="yyyy年MM月dd日" /></td>
							<td>${activity.place }</td>
							<td>${activity.personnum }人</td>
							<td>${activity.cost }元</td>
							<td>${activity.contact }</td>
							<td><a href="activity/to_update?id=${activity.id }">更新</a>
							 <a href="activity/delete?id=${activity.id }">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
