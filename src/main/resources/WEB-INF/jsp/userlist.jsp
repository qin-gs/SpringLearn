<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>a page</h2>
<c:forEach items="${users}" var="user">
    <c:out value="${user.name}"/><br>
    <c:out value="${user.age}"/><br>
</c:forEach>
