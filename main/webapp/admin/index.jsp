<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminindex.css"/>
<title>Insert title here</title>
</head>
<body>
	<% 
		String email = (String) session.getAttribute("user"); 
		String username = email== null ? "error session" : email.substring(0, email.indexOf('@'));	
	%>
	<header>
		<img alt="banner" src="${pageContext.request.contextPath}/assets/it-banner.png"/>
		<h3>Wellcome <%= username %></h3>
		<a href="${pageContext.request.contextPath}/logout">Log out</a>
	</header>
	<main class="f-container-fluid">
		<div class="f-row">
			<aside class="f-col-md-3 f-col-xl-2 aside">
				<a class="active" href="">Dashboard</a>
				<a href="">Staff Manager</a>
			</aside>
			<section class="f-col-md-9 f-col-xl-10 table-staff">
				<table>
					<thead>
						<tr>
							<th colspan="4">Members of team</th>
						</tr>					
					</thead>
					<tbody>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Role</th>
							<th>Mail</th>
						</tr>
						<tr>
							<td>1</td>
							<td><%=username %></td>
							<td>Developer</td>
							<td><%=email %></td>
						</tr>
						<tr>
							<td>1</td>
							<td><%=username %></td>
							<td>Developer</td>
							<td><%=email %></td>
						</tr>
						<tr>
							<td>1</td>
							<td><%=username %></td>
							<td>Developer</td>
							<td><%=email %></td>
						</tr>
						<tr>
							<td>1</td>
							<td><%=username %></td>
							<td>Developer</td>
							<td><%=email %></td>
						</tr>
					</tbody>						
				</table>
			</section>
		</div>			
	</main>	
</body>
</html>