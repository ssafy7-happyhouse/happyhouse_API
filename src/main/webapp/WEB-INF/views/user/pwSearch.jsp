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
                        <h1>비밀번호 찾기</h1>
                    </div>
                    <form id="form_id" action="${pageContext.request.contextPath}/user/pwSearch" method="post">
                        <div class="register-item">
                            <label for="_id">아이디</label><span>*</span>
                            <input type="text" name="id" class="form-control" id="_id" placeholder="아이디를 입력해주세요." required="required">
                        </div>
                        <div class="register-item">
                            <label for="_name">이름</label><span>*</span>
                            <input type="text" name="name" class="form-control" id="_name" placeholder="이름을 입력해주세요." required="required">
                        </div>
                        <div class="register-item">
                            <label for="_phone">전화번호</label><span>*</span>
                            <input type="tel" name="phone" class="form-control" id="_phone" placeholder="010-xxxx-xxxx" required="required">
                        </div>
                        <div>
                            <input type="submit" id="pwSearch-btn" class="btn btn-warning mt-4" value="비밀번호 찾기">
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