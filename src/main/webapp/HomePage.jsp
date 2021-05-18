<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: YaseminTurkkan
  Date: 5/12/2021
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>--%>

<jsp:useBean id="idbean" scope="application"
             class="com.mycompany.PA3.GetID" />

<%
    Integer user_id = (Integer) session.getAttribute("user_id");
    if (user_id == null) {
        int next_id = idbean.getId();
        session.setAttribute("user_id", next_id);
        idbean.incId();
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Pet Shop | Home</title>
    <style><%@include file="/myStyle.css"%></style>
</head>

<body>
<p>this is jsp</p>
<p>
    Your user_id is <%= session.getAttribute("user_id") %>,
    next user_id is <jsp:getProperty name="idbean" property="id"/>
</p>
<div id="header">
    <h1>
        <img src="<%=request.getContextPath()%>/images/Logo/shopLogo.png">
    </h1>
</div>
<div id = "top-nav-bar">
    <ul>
        <li><a class="active" href=><h3> Home </h3></a></li>
        <li><a href=./DogsJSP><h3> Dogs </h3></a></li>
        <li><a href=./CatsJSP><h3> Cats </h3></a></li>
        <li><a href=./ContactJSP><h3> Contact </h3></a></li>
    </ul>
</div>
<jsp:include page="/Last5" flush="true"/>
<jsp:include page="/Products" flush="true"/>
</body>
</html>