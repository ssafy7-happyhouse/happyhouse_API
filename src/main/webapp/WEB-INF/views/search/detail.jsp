<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HappyHouse</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b6bf28ccf06310f5ad87ecb34261f1be&libraries=services"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/initMap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/sidogugundong.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/detail.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />


</head>
<body>
	<!-- 상단 Header 조각페이지 -->
	<%@ include file="../inc/header.jsp" %>
	<!-- 상단 Header End -->
	
	<!-- 중앙 버튼 Start -->
	<div class="container-fluid bg-dark text-center">
		<div class="option-div">
			<form action="/">
				<div>
					<input type="hidden" name="action" value="init"> <input
						type="submit" id="searchbtn" value="홈으로">
				</div>
			</form>
		</div>
	</div>
	<!-- 중앙 버튼 End -->

	<div class="content">
		<div class="content-section">
			<div class="row">
				<div class="col-lg-1"></div>
				<div class="apartment-list col-lg-2 m-5">
					<h1 id="search-title">거래 내역</h1>
					<hr>
					<h2 id="apt-name"></h2>
					<br>
					<ul class="apartment-list-ul p-0"
						style="overflow: scroll; height: 500px;">
						<c:forEach var="dto" items="${list}">
							<li>
								<div>금액 : ${dto.dealAmount } 만원</div>
								<div>거래년월 : ${dto.dealYear }/${dto.dealMonth }</div>
								<div>면적 : ${dto.area }</div>
								<div>층수 : ${dto.floor } 층</div>
							</li>
							<hr>
						</c:forEach>
					</ul>
				</div>
				<div class="map col-lg-7 mt-5">
					<div class="container text-center mt-2">
						<div id="map" class="mt-2" style="width: 100%; height: 500px;"></div>
					</div>
				</div>
				<div class="col-lg-1"></div>
			</div>
		</div>
	</div>
 <input type="hidden" name="lat" value="${lat }"> 
 <input	type="hidden" name="lng" value="${lng }">
 
	<!-- 하단 Footer 조각페이지 -->
	<%@ include file="../inc/footer.jsp" %>
</body>

</html>