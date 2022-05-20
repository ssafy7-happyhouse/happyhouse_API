<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ssafy.happyhouse.model.dto.User" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HappyHouse</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sidogugundong.js"></script>
</head>

<body>
    <!-- 상단 Header 조각 페이지 -->
    <%@ include file="../inc/header.jsp" %>
    <!-- 상단 Header End -->

    <section class="content">
        <div class="container">
            <div class="register">
                <div>
                    <div class="text-center mt-4 font-weight-bold">
                        <h1>회원 정보</h1>
                    </div>
                    <form action="${pageContext.request.contextPath}/user/updateInfoView" method="get">
                        <div class="register-item">
                            <label for="_id">아이디</label>
                            <input type="text" class="form-control" id="_id" placeholder="${requestScope.dto.id}" readonly>
                        </div>
                        <div class="register-item">
                            <label for="_pw">비밀번호</label>
                            <input type="password" class="form-control" id="_pw" placeholder="****" readonly>
                        </div>
                        <div class="register-item">
                            <label for="_name">이름</label>
                            <input type="text" class="form-control" id="_name" placeholder="${requestScope.dto.name}" readonly>
                        </div>
                        <div class="register-item">
                            <label for="_address">주소</label>
                            <input type="text" class="form-control" id="_address" placeholder="${requestScope.dto.address}" readonly>
                        </div>
                        <div class="register-item">
                            <label for="_phone">전화번호</label>
                            <input type="tel" class="form-control" id="_phone" placeholder="${requestScope.dto.phone}" readonly>
                        </div>
                        <div class="register-item">
                            <label for="_favorite">관심지역</label>
                            <input type="text" class="form-control" id="_favorite" placeholder="${requestScope.dto.favorite}" readonly>
                        </div>
                        <div>
                            <input type="submit" id="update-btn" class="btn btn-primary mt-4" value="회원정보 수정하기">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <!-- 하단 Footer 조각페이지 -->
    <%@ include file="../inc/footer.jsp" %>
    <!-- 하단 Footer End -->
</body>

</html>