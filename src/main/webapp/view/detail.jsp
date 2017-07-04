<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>Activity Detail</title>

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
	<div class="container">
		<c:if test="${empty requestScope.activity }">
			<div>该活动已不存在</div>
		</c:if>
		<!-- 基本信息 -->
		<div>
			<div class="col-md-4">
				<img src="${requestScope.activity.surface }" width="400px"
					height="248px">
			</div>
			<div class="col-md-8">
				<table class="table table-bordered">
					<tr>
						<td colspan="4" align="center">活&nbsp;动&nbsp;信&nbsp;息&nbsp;</td>
					</tr>
					<tr>
						<td>活动名</td>
						<td width=280px>${requestScope.activity.name }</td>
						<td>人数</td>
						<td>${requestScope.activity.personnum }</td>
					</tr>
					<tr>
						<td>价格</td>
						<td>¥&nbsp;${requestScope.activity.cost }</td>
						<td>日期</td>
						<td><fmt:formatDate value="${requestScope.activity.date}"
								pattern="yyyy年MM月dd日" /></td>
					</tr>
					<tr>
						<td>地点</td>
						<td>${requestScope.activity.place }</td>
						<td>联系方式</td>
						<td>${requestScope.activity.contact}</td>
					</tr>
					<tr>
						<td>参与人</td>
						<td colspan="3">
							<c:forEach items="${requestScope.users }" var="userone">
								<span>${userone.name }、</span>
							</c:forEach>
						</td>
					</tr>
					<tr>
					<td colspan="4" align="right"><a class="btn btn-success"
						href="item/add?id=${activity.id }">参加活动</a></td>
					</tr>
				</table>
			</div>
		</div>
		<div>
			<div class="row feature col-md-12 col-md-offset-0">
				<div class="panel panel-primary ">
					<div class="panel-heading">
						<h3 class="panel-title">活动详细介绍</h3>
					</div>
					<div class="panel-body">
						${requestScope.activity.detail }
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>
