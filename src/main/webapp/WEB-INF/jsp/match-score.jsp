<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 19.12.2024
  Time: 02:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">${requestScope.match.playerOne.name}</td>
                    <td class="table-text">0</td>
                    <td class="table-text">${requestScope.match.score.getPlayerScore(0)}</td>
                    <td class="table-text">${requestScope.match.score.currentGame.getPlayerScore(0).title}</td>
                    <td class="table-text">
                        <form class="score-btn" method="post" action="match-score">
                            <input hidden="hidden" name="uuid" value="${param.uuid}">
                            <input hidden="hidden" name="playerId" value="0">
                            <button class="full-width-button" type="submit">Score</button>
                        </form>
                        <%--<div class="score-btn">Score</div>--%>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">${requestScope.match.playerTwo.name}</td>
                    <td class="table-text">0</td>
                    <td class="table-text">${requestScope.match.score.getPlayerScore(1)}</td>
                    <td class="table-text">${requestScope.match.score.currentGame.getPlayerScore(1).title}</td>
                    <td class="table-text">
                        <%--                        <div class="score-btn">Score</div>--%>
                        <form class="score-btn" method="post" action="match-score">
                            <input hidden="hidden" name="uuid" value="${param.uuid}">
                            <input hidden="hidden" name="playerId" value="1">
                            <button class="full-width-button" type="submit">Score</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>
<%@include file="footer.jsp" %>
</body>
</html>