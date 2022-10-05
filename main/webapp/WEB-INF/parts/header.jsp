<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:useBean id="search1" class="bean.SearchBean" scope="request"/>
<jsp:setProperty name="search1" property="*"/>

<!-- read user parameter on session -->
<c:set var="user" value="${sessionScope.user}"></c:set>
<div class="header">
<div class="f-container-logo-wrap">
	<div class="f-container-xl">
		<div class="f-row">
			<div class="f-col-md-4 f-col-lg-3">
				<p class="logo-text">PRJ321</p>
				<p class="logo-wellcome">Wellcome to my website</p>
			</div>
			<div class="f-col-md-8 f-col-lg-9">
				<form class="form-search" action="${pageContext.request.contextPath}/SearchController2?page=1" method="post">
					<select name="category" class="select-category-search">
						<option selected value="">Categories</option>
					  	<option value="apple">Apple</option>
					  	<option value="samsung">Samsung</option>
					  	<option value="sony">Sony</option>
					  	<option value="nokia" >Nokia</option>
					</select>
			        <input class="input-search" type="search" placeholder="Search" aria-label="Search" name="search">
			        <button class="btn-search" type="submit"><i class="bi bi-search"></i></button>
			    </form>
			</div>
		</div>
	</div>
</div>
<nav class="f-container-navbar-wrap">
	<div class="f-container-xl">
		<div class="navbar-wrap">
			<div class="navbar-first-order-mobile">
				<a class="navbar-toggle-button" href="">
					<span class="bar"></span>
					<span class="bar"></span>
					<span class="bar"></span>
				</a>
				
				<!-- if user hasn't been login then display login and register button -->
				<c:if test="${user == null }">
					<div class="navbar-items-list-user">
						<a class="navbar-item btn-login" 
							href="${pageContext.request.contextPath}/ListController?action=login">Login</a>
						<a class="navbar-item btn-register" 
							href="${pageContext.request.contextPath}/ListController?action=register">Register</a>
					</div>
				</c:if>
				
				<!-- if user was logged in then display wellcome user -->
				<c:if test="${user != null }">
					<div class="navbar-items-list-user">
					
						<!-- if name don't exist, use email instead for wellcome message -->
						<div class="navbar-wellcome-message">Wellcome 							
							<p>
								<c:if test="${user.name==null || user.name==''}">
									${user.email}
								</c:if>
								<c:if test="${user.name!=null}">
									${user.name}
								</c:if>
								<i class="bi bi-caret-down-fill"></i>
							</p>
							<div class="user-action-wrap">
								<a class="btn-order" 
									href="${pageContext.request.contextPath}/UserController?action=showorders">
									<i class="bi bi-card-checklist"></i>  Your orders</a>
								<a class="btn-logout" 
									href="${pageContext.request.contextPath}/UserController?action=dologout">
									<i class="bi bi-box-arrow-right"></i>  Logout</a>
							</div>						
						</div>
						
						
					</div>
				</c:if>
				
			</div>				
			<div class="navbar-items-list navbar-items-list-nav hide">
				<a class="navbar-item" href="${pageContext.request.contextPath}/ListController">Home</a>	
				<a class="navbar-item" href="${pageContext.request.contextPath}/ListController?action=product">Product</a> 
				<a class="navbar-item" href="${pageContext.request.contextPath}/ListController?action=about">About us</a> 
			</div>			
		</div>		
	</div>
</nav>
</div>