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

<title>My JSP 'addActivity.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="scripts/bootstrap.min.css">
<script type="text/javascript" src="scripts/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#creator").blur(function(){
			var name = $(this).val();
			name=$.trim(name);
			var url = "user/verifyCreatorName";
			var args={"time":new Date(),"name":name};
			$.get(url,args,function(data){
				$("#regname-msg").text(data);
			});
		});
		$(":submit").click(function(){
			var msg = $("#regname-msg").text();
			if(msg=="用户不存在"){
				alert("用户不存在，请确认后输入!");
				return false;
			}
		});
	});
</script>
</head>

<body>
	<jsp:include page="admin-index.jsp"></jsp:include>
	<br>
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">活动信息</h3>
			</div>
			<div class="panel-body">
				<div class="container col-md-10 col-md-offset-1">
					<form name="addActivity" class="form-horizontal"
						action="activity/addActivity" method="post" role="form"
						enctype="multipart/form-data">
						<c:if test="${!empty requestScope.activity }">
							<input type="hidden" name="id"
								value="${requestScope.activity.id }">
						</c:if>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label"></label>
							<div class="col-sm-10">
								<h3>增加活动</h3>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">活动名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="name"
									placeholder="请输入活动名" name="name"
									value="${requestScope.activity.name }">
							</div>
						</div>
						<div class="form-group">
							<label for="creator" class="col-sm-2 control-label">创建者</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="creator"
									placeholder="请输入创建者用户名" name="creator"
									value="${requestScope.creator.name }">
							</div>
							<label for="regname" class="col-sm-5 col-md-offset-0 control-label" id="regname-msg"></label>
						</div>
						<div class="form-group">
							<label for="date" class="col-sm-2 control-label">活动时间</label>
							<div class="col-sm-10">
								<input type="date" class="form-control" id="date" placeholder=""
									name="date" value="${requestScope.activity.date }">
							</div>
						</div>
						<div class="form-group">
							<label for="surface" class="col-sm-2 control-label">封面</label>
							<div class="col-sm-10">
								<img src="${requestScope.activity.surface }" width="420px" height="270px">
								 <input type="file" class="form-control" id="surface" name="surface">
							</div>
						</div>
						<div class="form-group">
							<label for="place" class="col-sm-2 control-label">地点</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="place"
									placeholder="请输入地点" name="place"
									value="${requestScope.activity.place }">
							</div>
						</div>
						<div class="form-group">
							<label for="personnum" class="col-sm-2 control-label">人数</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="personnum"
									placeholder="请输入人数" name="personnum"
									value="${requestScope.activity.personnum }">
							</div>
						</div>

						<div class="form-group">
							<label for="cost" class="col-sm-2 control-label">费用</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="cost" name="cost"
									value="${requestScope.activity.cost }">
							</div>
						</div>
						<div class="form-group">
							<label for="contact" class="col-sm-2 control-label">联系人</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="contact"
									name="contact" value="${requestScope.activity.contact }">
							</div>
						</div>

						<div class="form-group">
							<label for="detail" class="col-sm-2 control-label">详细介绍</label>
							<div class="col-sm-10">
								<textarea cols=57 rows=4 name="detail" id="detail">${requestScope.activity.detail }</textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-info">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<br>
	</div>
</body>
</html>
