<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"//getscheme返回http
			+ request.getServerName() + ":" + request.getServerPort()//getservername，getservername
			+ path + "/";
%>
<!--代码块，获取应用根路径，存在basePAth中-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>"><!-- 设置页面中所有相对路径的基准路径 -->
<link href="${pageContext.request.contextPath}/assets/css/bootstrap.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/css/font-awesome.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/css/custom.css"
	rel="stylesheet" />

<style type="text/css">
.from-group {
	margin-top: 150px;
}

body {
	background: #214761;
	background-image: url("${pageContext.request.contextPath}/assets/img/login-body.png");
}

.title {
	color: #6C6CA6;
	line-height: 60px;
	height: 60px;
	border-bottom: 1px solid gray;
	font-weight: bold;
}

#logbox {
	border: 2px solid #EEEEEE;
	border-radius: 10px;
	padding-bottom: 10px;
	background-color: rgb(233, 242, 250);
}
</style>

<title>登录</title>


</head>

<body>
	<div id="wrapper">
		<form class="from-group"  id="loginForm" action="${pageContext.request.contextPath}/login.html"
			method="post"><!-- 表单，post提交敏感信息方法 -->
			<p class="col-lg-4 col-md-3"></p>
			<div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 " id="logbox">
			
				<p class="h1 text-center title">Tipsy state收银管理系统</p>
				
				<h2>登录：<span style="color:red;font-size: 12px;">${error}</span></h2><!-- ${error}：这是一个占位符，可能是用于显示登录过程中出现的错误信息。在实际运行时，服务器端会将这个占位符替换为实际的错误信息。 -->
				
				<div class="input-group">
					<span class="input-group-addon">
					    <span class="glyphicon glyphicon-user"></span>
					</span> <!-- 账户图标bootstrap框架图标 -->
					<input type="text" id="userCode" name="userCode" class="form-control" placeholder="账号"
						required="required" pattern="^[a-zA-Z0-9]{5,16}$" /><!-- placeholder文本框空时占位，require必填不可为空的验证 -->
				</div>
				
				<br />
				
				<div class="input-group">
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-certificate
			"></span></span> <input
						id="userPassWord" name="userPassWord" type="password" class="form-control"
						placeholder="密码" required="required" pattern="^[a-zA-Z0-9_-]{6,18}$" />
				</div>
				
				<br />
				
				<div class="row">
					<div class="col-lg-1"></div>
					<input type="submit"  class="btn btn-default col-lg-3" id="submitBtn"
						value="登录"/>
					<div class="col-lg-4"></div>
					<input type="reset" class="btn btn-default reset col-lg-3"
						value="重置" />
					<div class="col-lg-1"></div>
				</div>
				
			</div>
			<p class="col-lg-4 col-md-3"></p>
		</form>

	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/login.js"></script>
</body>
</html>
