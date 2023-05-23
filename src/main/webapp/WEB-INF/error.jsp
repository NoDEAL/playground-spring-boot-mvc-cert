<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>오류 발생</title>
</head>
<body>
<div>
    <h2>오류가 발생했습니다.</h2>
    <p>${error.throwable.message}</p>
    <c:if test="${!empty error.redirect}">
        <a href="${error.redirect}">확인</a>
    </c:if>
</div>
</body>
</html>
