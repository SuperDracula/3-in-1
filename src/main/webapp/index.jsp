<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/download.css" type="text/css">
</head>
<body>
    <h1>Murach's Java Servlets and JSP - Combined Projects</h1>
    <ul>
        <li><a href="${pageContext.request.contextPath}/cart/shop.html">Shopping Cart</a></li>
        <li><a href="${pageContext.request.contextPath}/download?action=viewAlbums">Music Download &amp; Cookies</a></li>
        <li><a href="${pageContext.request.contextPath}/emailList?action=join">Email List &amp; Survey</a></li>
    </ul>
</body>
</html>