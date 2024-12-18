<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 19.12.2024
  Time: 02:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%@include file="header.jsp"%>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <input class="input-filter" placeholder="Filter by name" type="text" />
            <div>
                <a href="#">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Rafael Nadal</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Roger Federer</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Rafael Nadal</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Roger Federer</span></td>
            </tr>
            <tr>
                <td>Rafael Nadal</td>
                <td>Roger Federer</td>
                <td><span class="winner-name-td">Rafael Nadal</span></td>
            </tr>
        </table>

        <div class="pagination">
            <a class="prev" href="#"> < </a>
            <a class="num-page current" href="#">1</a>
            <a class="num-page" href="#">2</a>
            <a class="num-page" href="#">3</a>
            <a class="next" href="#"> > </a>
        </div>
    </div>
</main>
<%@include file="footer.jsp"%>
</body>
</html>