<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 19.12.2024
  Time: 02:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>

<body>
<%@include file="header.jsp" %>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <form method="get" action="${pageContext.request.contextPath}/matches" class="form-matches">
                <label>
                    <input class="input-filter" name="filter" placeholder="Filter by name"
                           type="text" value="${param.filter}"/>
                </label>
                <button class="btn-filter" type="submit">Search</button>

                <%--<div>--%>
                <%--</div>--%>

            </form>
            <a href="${pageContext.request.contextPath}/matches">
                <button class="btn-filter">Reset Filter</button>
            </a>

        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach var="match" items="${requestScope.matches}">
                <tr>
                    <td>${match.id} ${match.playerOne.name}</td>
                    <td>${match.playerTwo.name}</td>
                    <td><span class="winner-name-td">${match.winner.name}</span></td>
                </tr>
            </c:forEach>

        </table>

        <div class="pagination">
            <c:if test="${requestScope.page > 1}">

                <a class="prev" href="?page=${requestScope.page - 1}&filter=${param.filter}"> < </a>
            </c:if>

            <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                <c:choose>
                    <c:when test="${i == requestScope.page}">
                        <span class="num-page current" href="">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a class="num-page" href="?page=${i}&filter=${param.filter}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.page < requestScope.totalPages - 1}">
                <a class="next" href="?page=${requestScope.page + 1}&filter=${param.filter}"> > </a>
            </c:if>
        </div>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>
