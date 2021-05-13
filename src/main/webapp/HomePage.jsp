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

<html>

<%--<c:set var = "id" scope = "session" value = "0"/>--%>

    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Pet Shop | Home</title>
    <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/myStyle.css' />
</head>

<body>
<%--<%=getID(request, response)%>--%>
<div id="header">
    <h1>
        <img src="<%=request.getContextPath()%>/images/Logo/shopLogo.png">
    </h1>
</div>

<div id = "top-nav-bar">
    <ul>
        <li><a class="active" href=><h3> Home </h3></a></li>
        <li><a href=PA2/DogsServlet><h3> Dogs </h3></a></li>
        <li><a href=PA2/CatsServlet><h3> Cats </h3></a></li>
        <li><a href=PA2/ContactServlet><h3> Contact </h3></a></li>
    </ul>
</div>
</html>

<%--<%--%>
<%--    String url1 = "/Last5";--%>
<%--    String url2 = "/Products";--%>
<%--    RequestDispatcher rd = request.getRequestDispatcher(url1);--%>
<%--    rd.include(request, response);--%>
<%--    rd = request.getRequestDispatcher(url2);--%>
<%--    rd.include(request, response);--%>
<%--%>--%>

<%--<jsp:include page="Last5"/>--%>

<%!
    void getID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        HttpSession session = request.getSession(true);
//        Integer user_id = (Integer) session.getAttribute("user_id");
//
//        String heading;
//
//        if (user_id == null) {
//            session.setAttribute("user_id", this.id);
//            this.id++;
//            heading = "Welcome, New-Comer, your ID is "
//                    + session.getAttribute("user_id")
//                    + ", nextID is " + this.id;
//        } else {
//            heading = "Welcome Back, your ID is "
//                    + session.getAttribute("user_id")
//                    + ", nextID is " + this.id;
//        }
//
//        /*  Print out heading for debugging  */
//        PrintWriter out = response.getWriter();
//        out.println(heading);
    }
%>