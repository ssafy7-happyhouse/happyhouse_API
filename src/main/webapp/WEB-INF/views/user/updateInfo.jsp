<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!-- 상단 Header 조각페이지 -->
    <%@ include file="../inc/header.jsp" %>
    <!-- 상단 Header End -->

    <section class="content">
        <div class="container">
            <div class="register">
                <div>
                    <div class="text-center mt-4 font-weight-bold">
                        <h1>회원 정보 수정 페이지</h1>
                    </div>
                    <form action="${pageContext.request.contextPath}/user/updateInfo" method="post">
                        <div class="register-item">
                            <label for="_id">아이디</label>
                            <input type="text" name="id" class="form-control" id="_id" readonly autofocus="autofocus" value="${requestScope.dto.id}">
                        </div>
                        <div class="register-item">
                            <label for="_pw">비밀번호</label>
                            <input type="password" name="password" class="form-control" id="_pw" placeholder="영문 숫자 포함 6자리 이상" required>
                        </div>
                        <div class="register-item">
                            <label for="_name">이름</label>
                            <input type="text" name="name" class="form-control" id="_name" placeholder="User Name" value="${requestScope.dto.name}">
                        </div>
                        <div class="register-item">
                            <label for="_address">주소</label>
                            <input type="text" name="address" class="form-control" id="_address" placeholder="address" value="${requestScope.dto.address}">
                        </div>
                        <div class="register-item">
                            <label for="_phone">전화번호</label>
                            <input type="tel" name="phone" class="form-control" id="_phone" placeholder="010-xxxx-xxxx" value="${requestScope.dto.phone}">
                        </div>
                        <div class="register-item">
                            <label for="_favoirte">관심지역</label>
                            <input type="text" name="favorite" class="form-control" id="_favorite" placeholder="관심지역번호" value="${requestScope.dto.favorite}">
                        </div>
                        <div>
                            <input type="submit" id="update-btn" class="btn btn-success mt-4" value="수정">
                            <input type="button" id="cancel-btn" class="btn btn-primary mt-4" onclick="location.href='${pageContext.request.contextPath}/user?action=showInfo'" value="취소">
                            <input type="button" id="remove-btn" class="btn btn-danger mt-4" onclick="location.href='${pageContext.request.contextPath}/user?action=deleteInfo'" value="회원 탈퇴">
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