<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="parts/importstatic.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bodyPay.css"/>
<script defer type="text/javascript" src="${pageContext.request.contextPath}/js/payvalidate.js"></script>

<title>Pay your cart</title>
</head>
<body class="d-flex-column">
	<jsp:include page="parts/header.jsp"></jsp:include>
	<jsp:include page="parts/bodyPay.jsp"></jsp:include>
	<jsp:include page="parts/footer.jsp"></jsp:include>
</body>
</html>