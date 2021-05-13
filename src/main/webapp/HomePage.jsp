<%--
  Created by IntelliJ IDEA.
  User: YaseminTurkkan
  Date: 5/12/2021
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Pet Shop | Home</title>
    <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/myStyle.css' />
</head>

<body>
<%--    getID(req, resp);--%>
<div id="header"><h1><img src="./images/Logo/shopLogo.png"></h1></div>
<div id = "top-nav-bar">
    <ul>
        <li><a class="active" href="index.html"><h3> Home </h3></a></li>
        <li><a href="dogs.html"><h3> Dogs </h3></a></li>
        <li><a href="cats.html"><h3> Cats </h3></a></li>
        <li><a href="contact.html"><h3> Contact </h3></a></li>
    </ul>
</div>






</body>
</html>
