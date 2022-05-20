<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 Header 조각페이지 -->
<div class="container-fluid bg-dark">
    <c:choose>
        <c:when test="${sessionScope.loginInfo == null}">
            <div class="container">
                <div class="nav justify-content-end">
                    <button type="button" id="header-signup" class="button btn-secondary" onclick="location.href='${pageContext.request.contextPath}/user/signUpView'">Sign Up</button>
                    <button type="button" id="header-login" class="button btn-secondary" data-toggle="modal" data-target="#loginModal">Login</button>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container">
                <div class="nav justify-content-end">
                    <button type="button" id="header-signup" class="button btn-secondary" onclick="location.href='${pageContext.request.contextPath}/user/logout'">Logout</button>
                    <button type="button" id="header-login" class="button btn-secondary" onclick="location.href='${pageContext.request.contextPath}/user/showInfo'">내정보조회</button>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<nav class="navbar navbar-expand-sm bg-light navbar-dark shadow">
    <div class="container">
        <a href="/">
            <img src="${pageContext.request.contextPath}/img/logo.jpg" class="logo" width="150" height="100">
        </a>

        <button class=" navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link text-dark" href="">공지사항</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="">오늘의 뉴스</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="">모든 거래</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="">관심 지역 설정</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-dark" href="">관심 지역 둘러보기</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid mt-2">
    <img class="img-fluid" src="${pageContext.request.contextPath}/img/main2.jpg" alt="" style="width: 100%; height: 300px;">
</div>
<!-- 상단 Header 조각페이지 end -->

<!-- The Modal Start -->
<div class="modal" id="loginModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">로그인</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div><!-- Modal body -->
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/user/login" method="post">
                    <div class="form-group">
                        <label for="id">아이디</label>
                        <input type="text" name="id" class="form-control" placeholder="아이디를 입력하세요." id="userId" required="required">
                    </div>
                    <div class="form-group">
                        <label for="pwd">비밀번호</label>
                        <input type="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요." id="userPw" required="required">
                    </div>
                    <div class="form-group form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox"> Remember me
                        </label>
                    </div>
                    <button type="submit" id="btnLogin" class="btn btn-success">로그인</button>
                    <button type="button" id="btnSignUp" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/user/signUpView'">회원가입</button>
                    <button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/user/pwSearchView'">비밀번호 찾기</button>
                </form>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" id="btnClose" class="btn btn-danger" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<!-- The Modal End -->