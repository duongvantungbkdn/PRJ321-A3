<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- read user parameter on session and application-->
<c:set var="cart" value="${requestScope.cart}"/>
<c:set var="order" value="${requestScope.order}"/>

<div class="f-container-md container-body-paysuccess d-flex-grow-2">
	<c:if test="${cart == null || order == null}">
		<p class="load-data-failed-message">Sorry, something is wrong.</p>
	</c:if>
	<c:if test="${cart != null && order != null}">
		<h1>Information your order</h1>	
		<table>
			<tr>
				<td colspan="3" class="table-header">Customer's information</td>
			</tr>
			<tr>
				<td class="text-bold">Customer's Email</td>
				<td colspan="2">${order.userMail}</td>
			</tr>
			<tr>
				<td class="text-bold">Customer's Address</td>
				<td colspan="2">${order.address}</td>
			</tr>
			<tr>
				<td class="text-bold">Customer's Phone Number</td>
				<td colspan="2">${order.phoneNumber}</td>
			</tr>
			<c:set var="total" value="${0}"></c:set>
			<tr>
				<td colspan="3" class="table-header">Products' information</td>
			</tr>
			<tr>
				<td class="text-bold">Product name</td>
				<td class="text-center text-bold">Order number</td>
				<td class="text-center text-bold">Order price</td>
			</tr>
			<c:forEach var="product" items="${cart.items}">
				<tr>
					<td><a href="${pageContext.request.contextPath}/InformationProductController?id=${product.id}">
						${product.name}</a></td>
					<td class="text-center">${product.number}</td>
					<td class="text-center">$${product.price}</td>
				</tr>
				<c:set var="total" value="${total + (product.number*product.price)}"></c:set>
			</c:forEach>
			<tr>
				<td colspan="2" class="text-bold">Order Total</td>
				<td class="text-center text-bold">
					$<fmt:formatNumber maxFractionDigits="2" 
						value="${total}"/>
				</td>
			</tr>
		</table>
		<p class="cart-message">You ordered succeed. Your order will be processed as soon as possible. <br/>
		Do you want 
		<a href="${pageContext.request.contextPath}/ListController?page=1">continue</a> visit our store?</p>
	</c:if>
</div>