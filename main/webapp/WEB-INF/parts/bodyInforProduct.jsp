<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="product" value="${requestScope.product}"></c:set>

<div class="f-container-xl container-body">

	<!-- if load product is failed -->	
	<c:if test="${product==null}">
		<p class="load-data-failed-message">Don't load product</p>
	</c:if>
	
	<c:if test="${product!=null}">
		<div class="f-row product-name">
			<h2>${product.name}</h2>
		</div>
		<div class="f-row product-detail">
			<div class="f-col-md-7 f-col-lg-5 col-img">
				<img alt="${product.name}" src="${product.src}"/>
			</div>
			<div class="f-col-md-5 f-col-lg-7 col-info">
				<div class="info-wrap">
					<div class="price-wrap">
						<div class="price-number">
							<c:out value="$${product.price}"/>
						</div>
						<div class="type-and-brand">
							<p>${product.type}</p>
							<p>${product.brand}</p>
						</div>
					</div>									
					<p class="description">${product.description}</p>	
				</div>				
																	
				<div class="button-group">
			       	<a href="${pageContext.request.contextPath}/AddToCartController?action=add&&id=${product.id}">Add to Cart</a>
				</div>
			</div>
		</div>
	</c:if>
	
</div>