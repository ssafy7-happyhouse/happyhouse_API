<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HappyHouse</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b6bf28ccf06310f5ad87ecb34261f1be"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/sidogugundong.js"></script>
</head>

<body>
	<!-- 상단 Header 조각페이지 -->
	<%@ include file="inc/header.jsp"%>
	<!-- 상단 Header End -->

	<c:if test="${!empty password}">
		<script>
			alert("비밀번호는 [${password}]입니다.");
		</script>
	</c:if>
		<c:if test="${!empty pwNotFound}">
		<script>
			alert("${pwNotFound}]");
		</script>
	</c:if>
	<script type="text/javascript"></script>


	<!-- 검색바 Start-->
	<div class="container-fluid bg-dark text-center">
		<div class="option-div">
			<form action="/apartment/selectList" >
				<input type="hidden" name="action" value="selectList"> 
				<select	name="sido" id="sido" required>
					<option value="">시도선택</option>
					<c:forEach var = "sido" items="${sidoList}">
						<option value="${sido.sidoCode }">${sido.sidoName }</option>
					</c:forEach>
				</select> 
				<select name="gugun" id="gugun" required>
					<option value="">구군선택</option>
				</select> 
				<select name="dong" id="dong" required>
					<option value="">동선택</option>
				</select> <input type="text" id="searchContent" name="aptName"> <input
					type="submit" id="searchbtn" value="검색"> <input
					type="button" id="searchbtn"
					onclick="location.href='/apartment/aptList'" value="전체보기">
			</form>
		</div>
	</div>
	<!-- 검색바 End -->

	<!-- 지도 Start -->
	<div class="container text-center mt-2">
		<div id="map" class="mt-2" style="width: 100%; height: 500px;"></div>
		<script type="text/javascript" src="js/initMap.js"></script>
	</div>
	<!-- 지도 End -->

	<!-- 메인 페이지 하단 Start -->
	<div class="container">
		<div class="row">
			<div class="col-4">
				<div class="jumbotron mt-2 text-center">
					<h3>SSAFY 고지서 신청하고 포인트 받으세요 !</h3>
					<p>#100만원 당첨 기회</p>
					<p>#재산세 #주민세</p>
				</div>
			</div>
			<div class="col-4">
				<div class="jumbotron mt-2 text-center">
					<h3>지혜롭게 내 집 마련하기</h3>
					<li>돈을 많이 벌어야 집을 살 수 있다.</li>
					<li>그러기 위해선 좋은 직장을 가야 한다.</li>
					<li>그러기 위해선 열심히 공부해야 한다.</li>
				</div>
			</div>
			<div class="col-4">
				<div class="jumbotron mt-2 text-center">
					<h3>오늘의 뉴스</h3>
					<hr>
					<p>서울 아파트. 매매, 전세 다른 양상.. 상승.. 아시아경제</p>
				</div>
			</div>
		</div>
	</div>
	<!-- 메인 페이지 하단 End -->

	<!-- 하단 footer 조각페이지  -->
	<%@ include file="inc/footer.jsp"%>
	<!-- 하단 footer end -->
</body>

</html>