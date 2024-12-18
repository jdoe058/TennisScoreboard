<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 19.12.2024
  Time: 02:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | New Match</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<main>
    <div class="container">
        <div>
            <h1>Start new match</h1>
            <div class="new-match-image"></div>
            <div class="form-container center">
                <form method="post" action="#">
                    <p style="color: red;">Sample error message</p>
                    <label class="label-player" for="playerOne">Player one</label>
                    <input class="input-player" placeholder="Name" type="text" id="playerOne"
                           name="playerOne" pattern="[A-Za-z]\. [A-Za-z]+" required
                           title="Enter a name in the format n. surname ">
                    <label class="label-player" for="playerTwo">Player two</label>
                    <input class="input-player" placeholder="Name" type="text" id="playerTwo"
                           name="playerTwo" pattern="[A-Za-z]\. [A-Za-z]+" required
                           title="Enter a name in the format n. surname ">
                    <input class="form-button" type="submit" value="Start">
                </form>
            </div>
        </div>
    </div>
</main>
<%@include file="footer.jsp"%>
</body>
</html>