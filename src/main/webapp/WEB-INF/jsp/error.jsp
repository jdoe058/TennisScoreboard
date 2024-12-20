<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 20.12.2024
  Time: 04:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty requestScope.error}">
    <p style="color: darkred">${requestScope.error}</p>
</c:if>
<c:forEach var="error" items="${requestScope.errors}">
    <p style="color: darkred">${error}</p>
</c:forEach>
