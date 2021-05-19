<%--
  Created by IntelliJ IDEA.
  User: ndinh
  Date: 5/15/2021
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<html>
<head>
  <title>The Pet Shop | Dogs</title>
  <style><%@include file="/myStyle.css"%></style>
  <link rel='stylesheet' type='text/css' href='https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css' />
</head>
<p>this is jsp</p>
<body>
<sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
                   url = "jdbc:mysql:// localhost:3306/"
                   user = "root"  password = "root"/>

<sql:query dataSource = "${snapshot}" var = "result">
  SELECT pet_id,name, age, gender, price, SUBSTRING(message, 1, 65) AS message, profile_picture
  FROM petstore.pet
  WHERE pet_id LIKE 'D%';
</sql:query>

<div id="header">
  <h1>
    <img src="<%=request.getContextPath()%>/images/Logo/shopLogo.png">
  </h1>
</div>
<div id = "top-nav-bar">
  <ul>
    <li><a href=./><h3> Home </h3></a></li>
    <li><a class="active" href=./DogsJSP><h3> Dogs </h3></a></li>
    <li><a href=./CatsJSP><h3> Cats </h3></a></li>
    <li><a href=./ContactJSP><h3> Contact </h3></a></li>
  </ul>
</div>
<div id="main">
  <div class="row">
    <c:forEach var = "row" items = "${result.rows}">
      <div class="col-3 col-s-5 featuredPets">
        <a href="/PA3/ProductDetail?pet_id=${row.pet_id}">
          <div style="height: 275px;">
            <img src="${row.profile_picture}">
          </div>
          <h3>${row.name} - $${row.price}</h3>
        </a>
        <p>
            ${row.message}
        </p>
        <jsp:include page="/GetRating?pet_id=${row.pet_id}" flush="true"/>
        <hr class="solid">
      </div>
    </c:forEach>
  </div>
</div>
</body>
</html>