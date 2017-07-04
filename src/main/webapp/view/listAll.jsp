<%@ page language="java" import="java.util.*,me.ifma.activitybar.entity.*,me.ifma.activitybar.bean.Page" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ActivityBar</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="${basepath}scripts/style.css" rel="stylesheet"
	type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="scripts/bootstrap.min.css">
<script type="text/javascript" src="scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>

</head>

<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="clear"></div>
	<div class="container">
		<div class="" class="container">
			<div class="col-md-offset-1 col-md-10">
				<form action="activity/getAll" method="get">
					<input type="text" name="keyword" placeholder="请输入关键字"
						value="${requestScope.activitys.cretiera.keyword }" size="12">
					&nbsp;&nbsp;费用：<input type="text" name="minprice" size="2"
						value="${requestScope.activitys.cretiera.minPrice==0? '':requestScope.activitys.cretiera.minPrice }">-
					<input type="text" name="maxprice" size="2"
						value="${requestScope.activitys.cretiera.maxPrice==999999? '':requestScope.activitys.cretiera.maxPrice}">&nbsp;&nbsp;
					<select name="orderby" class="btn btn-Info">
						<option value="1">以费用升序排列</option>
						<option value="2">以费用降序排列</option>
						<option value="3">以时间升序排列</option>
						<option value="4">以时间降序排列</option>
					</select> <span class="col-xs-1"></span> <a
						href="activity/getAll?keyword=${requestScope.activitys.cretiera.keyword }&minprice=${requestScope.activitys.cretiera.minPrice}&maxprice=${requestScope.activitys.cretiera.maxPrice}&orderby=${requestScope.activitys.cretiera.orderBy}&pageNo=1">首页</a>
					<c:if test="${requestScope.activitys.cretiera.pageNo eq 1}">
						<span>上一页</span>
					</c:if>
					<c:if test="${requestScope.activitys.cretiera.pageNo > 1}">
						<a href="activity/getAll?keyword=${requestScope.activitys.cretiera.keyword }&minprice=${requestScope.activitys.cretiera.minPrice}&maxprice=${requestScope.activitys.cretiera.maxPrice}&orderby=${requestScope.activitys.cretiera.orderBy}&pageNo=${requestScope.activitys.cretiera.pageNo-1 }">上一页</a>
					</c:if>
						&nbsp;&nbsp;第${requestScope.activitys.cretiera.pageNo }/${requestScope.activitys.cretiera.maxPageNo }页
					<c:if test="${requestScope.activitys.cretiera.pageNo == requestScope.activitys.cretiera.maxPageNo}">
						<span>下一页</span>
					</c:if>
					<c:if test="${requestScope.activitys.cretiera.pageNo < requestScope.activitys.cretiera.maxPageNo}">
						<a href="activity/getAll?keyword=${requestScope.activitys.cretiera.keyword }&minprice=${requestScope.activitys.cretiera.minPrice}&maxprice=${requestScope.activitys.cretiera.maxPrice}&orderby=${requestScope.activitys.cretiera.orderBy}&pageNo=${requestScope.activitys.cretiera.pageNo+1 }">下一页</a>
					</c:if>
					<a href="activity/getAll?keyword=${requestScope.activitys.cretiera.keyword }&minprice=${requestScope.activitys.cretiera.minPrice}&maxprice=${requestScope.activitys.cretiera.maxPrice}&orderby=${requestScope.activitys.cretiera.orderBy}&pageNo=${requestScope.activitys.cretiera.maxPageNo }">末页</a>&nbsp;&nbsp;
						跳至<input type="text" name="pageNo" size="1" value="${requestScope.activitys.cretiera.pageNo }">页 
						<input type="submit" value="搜索" class="btn btn-Success">
				</form>
			</div>
		</div>
		<br><br>
		<div class="row" class="">
			<c:if test="${empty requestScope.activitys }">
				<div>您给的查询条件太多啦！</div>
			</c:if>
			<c:forEach items="${requestScope.activitys.items }" var="activity">
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
	</div>
	<br>
	<div class="clear"></div>

	<jsp:include page="../foot.jsp"></jsp:include>
</body>


</html>
