<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- read user parameter on session and application-->
<c:set var="listOrders" value="${sessionScope.listOrders}"/>
<c:set var="sizeList" value="${listOrders.size()}"/>

<div class="f-container-md container-body-list-order d-flex-grow-2">
	<h1>Your orders list</h1>
	<c:if test="${listOrders == null}">
		<p class="load-data-failed-message">You have not placed any orders yet.</p>
	</c:if>
	<c:if test="${listOrders != null}">
		<c:if test="${sizeList == 0 }">
			<p class="load-data-failed-message">You have not placed any orders yet.</p>
		</c:if>
		<c:if test="${sizeList > 0 }">
			<c:set var="orderNumber" value="${1}"></c:set>
			<c:forEach var="order" items="${listOrders}">
				<h2>Order number ${orderNumber}: ID ${order.orderId}, 
				date ${order.orderDate.getDate()}/${order.orderDate.getMonth()+1}/${order.orderDate.getYear()+1900}, 
				status ${order.status}</h2>
				<table>
					<tr>
						<td colspan="3" class="table-header">Customer's information</td>
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
					<c:forEach var="orderProduct" items="${order.lp}">
						<tr>
							<td><a href="${pageContext.request.contextPath}/InformationProductController?id=${orderProduct.productId}">
								${orderProduct.nameProduct}</a></td>
							<td class="text-center">${orderProduct.amountProduct}</td>
							<td class="text-center">$${orderProduct.priceProduct}</td>
						</tr>
						<c:set var="total" value="${total + (orderProduct.amountProduct*orderProduct.priceProduct)}"></c:set>
					</c:forEach>
					<tr>
						<td colspan="2" class="text-bold">Order Total</td>
						<td class="text-center text-bold">$${total}</td>
					</tr>
					<c:set var="orderNumber" value="${orderNumber+1}"></c:set>
				</table>
			</c:forEach>
		</c:if>
	</c:if>
</div>