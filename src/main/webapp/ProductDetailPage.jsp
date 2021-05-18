<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
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
<%
    String pet_id = request.getParameter("pet_id");
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/"
            + "petstore", "root", "root");
    Statement stmt = con.createStatement();
    String sql = "SELECT name, age,gender,price,pet_id,message,profile_picture FROM pet "
            + "WHERE pet_id = '" + pet_id + "';";
    ResultSet rs = stmt.executeQuery(sql);


    while (rs.next()) {
%>
<div id='main'>
    <div class="row" style="text-align: left">
        <div class="col-3 col-s-5">
            <div class="profile">
                <img src=<%=rs.getString("profile_picture")%>>
            </div>
        </div>
        <div class="col-3 col-s-5" style="text-align: left; padding-top: 35px;">
            <p>Name: <%=rs.getString("name")%></p>
            <p>Age: <%=rs.getInt("age")%></p>
            <p>Gender: <%=rs.getString("gender")%></p>
            <p>Price: $<%=rs.getFloat("price")%></p>
            <p>ID: <%=rs.getString("pet_id")%></p>
            <p><%=rs.getString("name")%>'s Message: </p>
            <p>&emsp;<%=rs.getString("message")%></p>
        </div>
    </div>
    <form action=/PA3/Cart method='post'>
        <div class='addToCartButton'>
            <button name= pet_id value=<%=pet_id%>>
                Add to Cart
            </button>
        </div>
    </form>
</div>
<%}
    stmt.close();
%>

</body>
</html>