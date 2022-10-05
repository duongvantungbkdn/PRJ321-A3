<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- read user parameter on session and application-->
<c:set var="user" value="${sessionScope.user}"></c:set>
<c:set var="cart" value="${sessionScope.cart}"></c:set>

<div class="body d-flex-grow-2">
<div class="f-container-md container-body-pay">
	<div class="f-row cart">
		<div class="f-col-xl-6 leftcontent">
			
			<c:if test="${cart == null}">
				<p class="cart-message">Your cart is empty</p>
			</c:if>
			<c:if test="${cart != null}">
				<c:if test="${cart.items.size() == 0}">
					<p class="cart-message">Your cart is empty</p>
				</c:if>
				
				<c:if test="${cart.items.size() > 0}">
					<table class="table-1">
						<tr>
							<th colspan="4" >Your Cart has ${cart.size} products and total amount $${cart.amount}</th>
						</tr>
						<tr>
							<th>Product in cart</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Amount</th>
						</tr>
						<c:forEach var="productCart" items="${cart.items}">
							<tr>
								<td>${productCart.name}</td>
								<td>$${productCart.price}</td>
								<td>${productCart.number}</td>
								<td>
									$<fmt:formatNumber maxFractionDigits="2" value="${productCart.price * productCart.number}"/>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="3">Total</td>
							<td>$${cart.amount}</td>
						</tr>
					</table>
				</c:if>
			</c:if>	
		</div>
		<div class="f-col-xl-6 leftcontent">
			<form action="${pageContext.request.contextPath}/PayController" method="post">
				<table class="table-2">
					<tr class="f-row">
						<td class="f-col-md-4 f-col-lg-3 f-col-xl-12">Customer's email
							<p id="email-message" class="error-message hide red">Enter your email, please!</p>
							<p id="email-invalid-message" class="error-message hide red">Email is invalid!</p>
						</td>
						<td class="f-col-md-8 f-col-lg-9 f-col-xl-12">
							<input id="email-input" type="text" name="email" placeholder="You must enter your email"
								value="${user==null ? '' : user.email}"
						></td>
					</tr>
					<tr class="f-row">
						<td class="f-col-md-4 f-col-lg-3 f-col-xl-12">Customer's phone
							<p id="phone-message" class="error-message hide red ">Enter your phone, please!</p>
						</td>
						<td class="f-col-md-8 f-col-lg-9 f-col-xl-12">
							<input id="phone-input" type="text" name="phone" placeholder="You must enter your phone"
								value="${user==null ? '' : user.phone}"
						></td>
					</tr>
					<tr class="f-row">
						<td class="f-col-md-4 f-col-lg-3 f-col-xl-12">Customer's address
							<p id="address-message" class="error-message hide red">Enter your address, please!</p>
						</td>
						<td class="f-col-md-8 f-col-lg-9 f-col-xl-12">
							<input id="address-input" type="text" name="address" placeholder="You must enter your address"
								value="${user==null ? '' : user.address}"
						></td>
					</tr>
					<tr class="f-row">
						<td class="f-col-md-4 f-col-lg-3 f-col-xl-12">Customer's discount code</td>
						<td class="f-col-md-8 f-col-lg-9 f-col-xl-12"><input type="text" name="discount" placeholder="Enter your discount code"></td>
					</tr>
				</table>
				<button id="btn-pay-submit" type="submit">Click here to pay!</button>
			</form>
			<c:if test="${user == null}">
				<div class="f-row pay-login-register-wrap">
					<p class="f-col-sm-6">To pay easier <a href="${pageContext.request.contextPath}/ListController?action=login&&from=pay">Login</a></p>
					<p class="f-col-sm-6">Create an account <a href="${pageContext.request.contextPath}/ListController?action=register&&from=pay">Register</a></p>
				</div>
			</c:if>			
		</div>
	</div>
</div>
</div>