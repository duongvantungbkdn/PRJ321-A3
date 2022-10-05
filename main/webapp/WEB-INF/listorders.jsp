<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="parts/importstatic.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bodyListOrders.css"/>

<title>Your orders list</title>
</head>
<body class="d-flex-column">
	<jsp:include page="parts/header.jsp"></jsp:include>
	<jsp:include page="parts/bodyListOrders.jsp"></jsp:include>
	<jsp:include page="parts/footer.jsp"></jsp:include>
</body>
</html>