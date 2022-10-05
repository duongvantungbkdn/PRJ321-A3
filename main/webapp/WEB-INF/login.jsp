<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
<script defer type="text/javascript" src="${pageContext.request.contextPath}/js/loginvalidate.js"></script>

<title>Login</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
		String username = "", password = "", remember = "";
		if(cookies != null) {
			for(Cookie ck : cookies) {
				if(ck.getName().equals("ckuser")){
					username = ck.getValue();
				} else if (ck.getName().equals("ckrem")) {
					remember = ck.getValue();
				}
			}
		}
		
		String from = (String) request.getAttribute("from");
	%>
	<div class="login-wrap">
		<h1>Login to enjoy</h1>
		<h2 id="error-mesage" class="red"><%=request.getAttribute("errormessage") == null ? "" : request.getAttribute("errormessage")%></h2>
		<img alt="userlogin"
			src="${pageContext.request.contextPath}/assets/userlogin.png" />
		<form action="${pageContext.request.contextPath}/UserController" method="post">
			<input type="hidden" name="action" value="dologin">
			<input type="hidden" name="from" value="<%=from%>">
			<table>
				<tr class="f-row">
					<td class="f-col-md-3">Username (email)
						<p id="username-message" class="error-message hide red">Enter your username, please!</p>
					</td>
					<td class="f-col-md-9">
						<input id="username-input" type="text" name="username" value="<%=username%>"> <br />
					</td>
				</tr>
				<tr class="f-row">
					<td class="f-col-md-3">Password
						<p id="password-message" class="error-message hide red">Enter your password, please!</p>
					</td>
					<td class="f-col-md-9">
						<input id="password-input" type="password" name="password"> <br />
					</td>
				</tr>
			</table>	
			<div class="remember-forget-wrap">
				<div class="f-col-sm-6 remember-me">
					<input type="checkbox" name="remember" value="1" <%=remember.equals("1") ? "checked" : "" %>> Remember me
				</div>
				<p class="forgot-pass">Forgot <a href="">password</a>?</p>
			</div>	
			<button id="login-submit-btn" type="submit">Login</button>
		</form>
		<div class="cancel-wrap">
			<a href="${pageContext.request.contextPath}/ListController" class="cancel-btn">Cancel</a>
			<p>Create an account <a href="${pageContext.request.contextPath}/ListController?action=register">Register</a></p>
		</div>
	</div>
	
</body>
</html>