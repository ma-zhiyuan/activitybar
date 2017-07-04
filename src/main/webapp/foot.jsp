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

<title>My JSP 'foot.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="${basepath}scripts/style.css" rel="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript"
	src="${basepath}scripts/jquery-3.1.1.min.js"></script>
</head>

<body>
	<div class="clear"></div>
	<div class="footer">
		<div class="wrap">
			<h3>SAY HELLO</h3>
			<p>找到您喜欢的活动，</p>

			<div class="footerlinks">
				<div class="share">
				
					<li><a href="#" target="_blank" title="Facebook"><img
							src="img/facebook.png"></a></li>
					<li><a href="#" target="_blank" title="twitter"><img
							src="img/twitter.png"></a></li>
					<li><a href="#" target="_blank" title="tumblr"><img
							src="img/tumblr.png"></a></li>
					<li><a href="#" target="_blank" title="QQ"><img
							src="img/QQ.png"></a></li>
					
				</div>
			</div>
		</div>
	</div>
	<div class="footer1">
		<p class="link">
			<span>© All rights Reserved | Designed by 焦乐乐 </span>
		</p>
	</div>
</body>
</html>
