<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- get result from request -->
<c:set var="products" value="${requestScope.products}"/>
<c:set var="pageNum" value="${requestScope.pageNum}"/>
<c:set var="maxPage" value="${requestScope.maxPage}"/>

<jsp:useBean id="search1" class="bean.SearchBean" scope="request"/>

<div class="f-container-xl container-body-search d-flex-grow-2">

	<!-- if load data from database is failed -->
	<c:if test="${products == null}">
		<p class="load-data-failed-message">Data load form database is failed.<br>
			Please check connection with database and reload application.
		</p>
	</c:if>
	
	<!-- if load data from database is successed -->
	<c:if test="${products != null}">
		<c:if test="${maxPage == 0}">
			<p class="load-data-failed-message">No products is found.</p>
		</c:if>
		<c:if test="${maxPage > 0}">
			<div class="f-row">
				<c:forEach var="product" items="${products}">
					<div class="f-col-md-6 f-col-xl-4">
						<div class="oneproduct">
							<a href="${pageContext.request.contextPath}/InformationProductController?id=${product.id}">
								<img alt="${product.name}" src="${product.src}"/>
								<h2><c:out value="${product.name}"/></h2>
							</a>
							<div class="product-description">
								${product.description}
							</div>													
							<div class="product-details-wrap">
						       	<div class="product-brand">
									${product.brand}
							    </div>
						       	<div class="product-details">								
									<a href="${pageContext.request.contextPath}/InformationProductController?id=${product.id}">Detail</a>
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
							href="${pageContext.request.contextPath}/SearchController2?category=${search1.category}&&search=${search1.search}&&page=${pageNum-1}"
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
							href="${pageContext.request.contextPath}/SearchController2?category=${search1.category}&&search=${search1.search}&&page=${page}"
							>${page}</a>
					</c:forEach>
					
					<!-- if pageNum = maxPage then hidden next btn -->	
					<c:if test="${pageNum != maxPage }">
						<a class="pagination-item pagination-item-btn"
							href="${pageContext.request.contextPath}/SearchController2?category=${search1.category}&&search=${search1.search}&&page=${pageNum+1}"
							><i class="bi bi-arrow-right-circle"></i></a>
					</c:if>
				</div>
			</div>
		</c:if>
	</c:if>
</div>