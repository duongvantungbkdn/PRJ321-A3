<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
<script defer type="text/javascript" src="${pageContext.request.contextPath}/js/registervalidate.js"></script>
<title>Login</title>
</head>
<body>
	
	<% String from = (String) request.getAttribute("from"); %>
	
	<div class="register-wrap">	
		<h1>Create an account</h1>
		<h2 id="server-mesage" class="red"><%=request.getAttribute("errormessage") == null ? "" : request.getAttribute("errormessage")%></h2>
		
		<form action="${pageContext.request.contextPath}/UserController" method="post">
			<input type="hidden" name="action" value="doregister">
			<input type="hidden" name="from" value="<%=from%>">
			<table class="table-2">
				<tr class="f-row">
					<td class="f-col-md-3">User's name
						<p id="name-message" class="error-message hide red">Enter your name, please!.</p>
					</td>
					<td class="f-col-md-9"><input id="name-input" type="text" name="name" placeholder="Enter your name"></td>
				</tr>
				<tr class="f-row">
					<td class="f-col-md-3">User's email
						<p id="email-message" class="error-message hide red">Enter your email, please!</p>
					</td>
					<td class="f-col-md-9"><input id="email-input" type="text" name="email" placeholder="You must enter your email"></td>
				</tr>
				<tr class="f-row">
					<td class="f-col-md-3">User's password
						<p id="pass-message" class="error-message hide red">Enter your password, please!</p>
					</td>
					<td class="f-col-md-9"><input id="pass-input" type="password" name="pass" placeholder="You must enter your password"></td>
				</tr>
				<tr class="f-row">
					<td class="f-col-md-3">Confirm password
						<p id="confirm-pass-message" class="error-message hide red">Password confirm not the same.</p>
					</td>
					<td class="f-col-md-9"><input id="c-pass-input" type="password" name="c-pass" placeholder="Type your password again"></td>
				</tr>				
				
				<tr class="f-row">
					<td class="f-col-md-3">User's phone</td>
					<td class="f-col-md-9"><input id="phone-input" type="text" name="phone" placeholder="Enter your phone"></td>
				</tr>
				<tr class="f-row">
					<td class="f-col-md-3">User's address </td>
					<td class="f-col-md-9"><input id="address-input" type="text" name="address" placeholder="Enter your address"></td>
				</tr> 
			</table>
			<button id="btn-register-submit" type="submit">Register</button>
		</form>
		<div class="cancel-wrap">
			<a href="${pageContext.request.contextPath}/ListController" class="cancel-btn">Cancel</a>
			<p>Have an account <a href="${pageContext.request.contextPath}/ListController?action=login">Login</a></p>
		</div>
	</div>
</body>
</html>