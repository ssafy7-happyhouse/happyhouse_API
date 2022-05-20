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
    <%@ include file="inc/header.jsp" %>
    <!-- 상단 Header End -->

    <div class="container p-3 my-3 border text-center">
        <h1>${requestScope.message}</h1>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <p>${requestScope.messageDetail}</p>
        <br>
        <button type="button" class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/index.jsp'">메인 페이지로</button>
    </div>

    <!-- 하단 Footer 조각페이지 -->
    <%@ include file="inc/footer.jsp" %>
    <!-- 하단 Footer End -->

</body>

</html>