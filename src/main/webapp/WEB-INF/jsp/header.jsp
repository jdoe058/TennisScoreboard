<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 19.12.2024
  Time: 01:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="header">
  <section class="nav-header">
    <div class="brand">
      <div class="nav-toggle">
        <img src="${pageContext.request.contextPath}/images/menu.png" alt="Logo" class="logo">
      </div>
      <span class="logo-text">TennisScoreboard</span>
    </div>
    <div>
      <nav class="nav-links">
        <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
      </nav>
    </div>
  </section>
</header>