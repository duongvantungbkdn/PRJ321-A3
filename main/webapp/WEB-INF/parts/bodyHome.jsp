<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- read user parameter on session and application-->
<c:set var="user" value="${sessionScope.user}"></c:set>
<c:set var="cart" value="${sessionScope.cart}"></c:set>

<!-- get result from request -->
<c:set var="products" value="${requestScope.products}"/>
<c:set var="pageNum" value="${requestScope.pageNum}"/>
<c:set var="maxPage" value="${requestScope.maxPage}"/>

<div class="f-container-xl container-body-home d-flex-grow-2">

	<!-- if load data from database is failed -->
	<c:if test="${products == null}">
		<p class="load-data-failed-message">Data load form database is failed.<br>
			Please check connection with database and reload application.
		</p>
	</c:if>
	
	<!-- if load data from database is successed -->
	<c:if test="${products != null}">
		<c:if test="${maxPage == 0}">
			<p class="load-data-failed-message">List of products is empty.</p>
		</c:if>
		<c:if test="${maxPage > 0}">
			<div class="f-row">
				<div class="f-col-md-8 f-col-lg-9 leftcontent">
					<div class="f-row">
						<c:forEach var="product" items="${products}">
							<div class="f-col-md-6 f-col-xl-4">
								<div class="oneproduct">
									<a href="${pageContext.request.contextPath}/InformationProductController?id=${product.id}">
										<img alt="${product.name}" src="${product.src}"/>
										<h2><c:out value="${product.name}"/></h2>
									</a>
									<div class="type-and-brand">
										<p>${product.type}</p>
										<p>${product.brand}</p>
									</div>													
									<div class="price-details">
								       	<div class="price-number">
											<c:out value="$${product.price}"/>
									    </div>
									       	<div class="add-cart">								
												<a href="${pageContext.request.contextPath}/AddToCartController?action=add&&id=${product.id}">Add to Cart</a>
											</div>
									</div>
								</div>					
							</div>
						</c:forEach>									
					</div>				
					
					<!-- ///// PAGINATION////////// -->
					<div class="pagination-wrap">
						<div class="pagination f-col-md-10 f-col-md-8 f-col-xl-6">
							<!-- if pageNum = 1 then hidden previous btn -->	
							<c:if test="${pageNum != 1 }">
								<a class="pagination-item pagination-item-btn"
									href="${pageContext.request.contextPath}/ListController?page=${pageNum-1}"
									><i class="bi bi-arrow-left-circle"></i></a>
							</c:if>
							
							<!-- just display 5 page selection around current page -->
							<c:if test="${maxPage <= 5}">
								<c:set var="begin" value="${1}"></c:set>
								<c:set var="end" value="${maxPage}"></c:set>
							</c:if>
							<c:if test="${maxPage > 5}">
								<c:if test="${pageNum <= 3}">
									<c:set var="begin" value="${1}"></c:set>
									<c:set var="end" value="${5}"></c:set>
								</c:if>	
								<c:if test="${pageNum >= maxPage - 2}">
									<c:set var="begin" value="${maxPage-4}"></c:set>
									<c:set var="end" value="${maxPage}"></c:set>
								</c:if>
								<c:if test="${pageNum > 3 && pageNum < maxPage - 2}">
									<c:set var="begin" value="${pageNum-2}"></c:set>
									<c:set var="end" value="${pageNum+2}"></c:set>
								</c:if>							
							</c:if>
							
							<c:forEach var="page" begin="${begin}" end="${end}" >	
								<!-- active if page = pageNum -->						
								<c:if test="${page == pageNum}">
									<c:set var="active" value="active"></c:set>
								</c:if>
								<c:if test="${page != pageNum}">
									<c:set var="active" value=""></c:set>
								</c:if>
								<a class="pagination-item pagination-item-number ${active}"
									href="${pageContext.request.contextPath}/ListController?page=${page}"
									>${page}</a>
							</c:forEach>
							
							<!-- if pageNum = maxPage then hidden next btn -->	
							<c:if test="${pageNum != maxPage }">
								<a class="pagination-item pagination-item-btn"
									href="${pageContext.request.contextPath}/ListController?page=${pageNum+1}"
									><i class="bi bi-arrow-right-circle"></i></a>
							</c:if>
						</div>
					</div>
				</div>
				<div class="f-col-md-4 f-col-lg-3 rightcontent">
					<div class="cart">
						<div class="cart-header">
							Your Cart (
								<c:if test="${cart == null}">0</c:if>
								<c:if test="${cart != null}">${cart.size}</c:if>
							)
						</div> 
						
						<c:if test="${cart == null}">
							<p class="cart-message">Your cart is empty</p>
						</c:if>
						<c:if test="${cart != null}">
							<c:if test="${cart.size == 0}">
								<p class="cart-message">Your cart is empty</p>
							</c:if>
							
							<c:if test="${cart.size > 0}">
								<div class="cart-content">
								
									<!-- display cart --> 
									<c:forEach var="productCart" items="${cart.items}">
										<div class="cart-item">
											<div class="cart-item-name-wrap">
												<a class="cart-item-name" 
													href="${pageContext.request.contextPath}/InformationProductController?id=${product.id}">${productCart.name}</a>
												<a class="cart-item-delete" 
													href="${pageContext.request.contextPath}/AddToCartController?action=remove&&id=${productCart.id}">x</a>
											</div>
																				
											<div class="cart-item-detail">
												<a href="${pageContext.request.contextPath}/InformationProductController?id=${product.id}">
													<img alt="${productCart.name}" src="${productCart.src}"/>
												</a>
												<div class="cart-item-quantity">
													<a class="minus" href="${pageContext.request.contextPath}/AddToCartController?action=descrease&&id=${productCart.id}">-</a>
													<p>${productCart.number}</p>
													<a class="add" href="${pageContext.request.contextPath}/AddToCartController?action=add&&id=${productCart.id}">+</a>
												</div>
												<div class="cart-item-total-price">
													$<fmt:formatNumber maxFractionDigits="2" 
														value="${productCart.price * productCart.number}"/>
												</div>
											</div> 						
										</div>
									</c:forEach>							
								</div>
								<div class="cart-total">
									<div class="cart-total-sum">
										<p>Total</p>
										<p>$${cart.amount}</p>						
									</div>
									<div class="cart-total-pay">
										<a class="cart-total-pay-btn"
											href="${pageContext.request.contextPath}/ListController?action=pay">Pay now</a>
									</div>					
								</div>
							</c:if>
						</c:if>	
					</div>
				</div>
			</div>
		</c:if>
	</c:if>
</div>