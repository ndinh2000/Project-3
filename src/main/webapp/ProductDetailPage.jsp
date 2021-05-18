<%--
  Created by IntelliJ IDEA.
  User: azhon
  Date: 5/17/2021
  Time: 7:10 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<jsp:useBean id="idbean" scope="application"
             class="com.mycompany.PA3.GetID" />

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Pet Shop</title>
    <style><%@include file="/myStyle.css"%></style>
    <%
        response.setContentType("text/html;charset=UTF-8");
        String url1 = "/titleHeader";
        RequestDispatcher rd = request.getRequestDispatcher(url1);
        rd.include(request, response);
    %>
</head>

<body>
<p>this is jsp</p>
<c:set var = "petID" value="${param.pet_id}"/>
<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                   url = "jdbc:mysql:// localhost:3306/"
                   user = "root"  password = "root"/>

<sql:query dataSource = "${snapshot}" var = "result">
    SELECT name, age,gender,price,pet_id,message,profile_picture
    FROM petstore.pet
    WHERE pet_id = ?
    <sql:param value= "${petID}"/>
</sql:query>

<div id='main'>
    <c:forEach var = "row" items = "${result.rows}">
        <div class="row" style="text-align: left">
            <div class="col-3 col-s-5">
                <div class="profile">
                    <img src= "${row.profile_picture}">
                </div>
            </div>
            <div class="col-3 col-s-5" style="text-align: left; padding-top: 35px;">
                <p>Name: ${row.name}</p>
                <p>Age:  ${row.age}</p>
                <p>Gender:  ${row.gender}</p>
                <p>Price: $ ${row.price}</p>
                <p>ID:  ${row.pet_id}</p>
                <p> ${row.name}'s Message: </p>
                <p>&emsp; ${row.message}</p>
            </div>
        </div>
    </c:forEach>
    <form action=/PA3/Cart method='post'>
        <div class='addToCartButton'>
            <button name= pet_id value=${petID}>
                Add to Cart
            </button>
        </div>
    </form>
</div>

</body>
</html>