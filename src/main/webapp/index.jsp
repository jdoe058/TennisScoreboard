<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Home</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>
<body>
<%@include file="WEB-INF/jsp/header.jsp"%>
<main>
    <div class="container">
        <h1>Welcome to Tennis Scoreboard</h1>
        <p>Manage your tennis matches, record results, and track rankings</p>
        <div class="welcome-image"></div>
        <div class="form-container center">
            <a class="homepage-action-button" href="${pageContext.request.contextPath}/new-match">
                <button class="btn start-match">
                    Start a new match
                </button>
            </a>
            <a class="homepage-action-button" href="${pageContext.request.contextPath}/matches">
                <button class="btn view-results">
                    View match results
                </button>
            </a>
        </div>
    </div>
</main>
<%@include file="WEB-INF/jsp/footer.jsp" %>
</body>
</html>