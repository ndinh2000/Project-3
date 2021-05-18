<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>--%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Pet Shop | Contact</title>
    <link rel="stylesheet" href="myStyle.css">
</head>

<body>
<p>this is jsp</p>
<div id="header"><h1><img src="images/shopLogo.png"></h1></div>
<div id = "top-nav-bar">
    <ul>
        <li><a href="./"><h3> Home </h3></a></li>
        <li><a href="./DogsJSP"><h3> Dogs </h3></a></li>
        <li><a href="./CatsJSP"><h3> Cats </h3></a></li>
        <li><a class="active" href="./ContactJSP"><h3> Contact </h3></a></li>
    </ul>
</div>

<div id="main">
    <div>
        <h3 style="text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 5px;">About Us</h3>
        <p style="margin-left: 34px; margin-right:34px;font-size: 20px;margin-top: 0;">Thank you for visiting our store! We pride ourselves in providing adorable animals for loving homes. At our online store, you can check out the best animals to fit you and your family’s lifestyle. All our pets go home fully vaccinated and with pedigrees. You’re guaranteed to go home with an amazing furry-friend for life! We're currently open for online orders and accepting forms of Visa payment!</p>
    </div>

    <div>
        <h3 style="text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 5px;">Our Team</h3>
        <ul style="margin-left: 34px;font-size: 20px;margin-top: 0;">
            <li>Anqi Zhong - Founder of The Pet Shop</li>
            <li>Nham N. Dinh - Chief Executive Officer</li>
            <li>Yasemin Turkkan - Chief Financial Officer</li>
        </ul>
    </div>

    <div>
        <h3 style="text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 5px;">Plan Your Visit</h3>
        <ul style="margin-left: 34px;font-size: 20px;margin-top: 0;">
            <li>5365 Alton Pkwy, Irvine, CA 92604</li>
            <li>Hours: 10 am - 7 pm every day</li>
        </ul>
        <div class="col-3 col-s-5 img-container" style="width:100%; text-align: center;">
            <img style = "max-width: 100%; max-height: 100%; object-fit: contain;"; src="./images/Logo/Map.jpg">
        </div>
    </div>

    <div>
        <h3 style="text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 5px;">Get In Touch!</h3>
        <ul style="margin-left: 34px;font-size: 20px;">
            <li>Email: ThePetShop@gmail.com</li>
            <li>Phone: 999-545-9023</li>
        </ul>
    </div>

    <div>
        <h3 style="text-align: left;font-size: 1.5em;margin-left: 34px;margin-bottom: 5px;">Additional Content Credits</h3>
        <p style="margin-left: 34px; margin-right:34px;font-size: 20px;margin-top: 0;">Some of the writings and pictures for the animals on our website came from the websited below.</p>
        <ul style="margin-left: 34px;font-size: 20px;">
            <li>OC Animal Care Website</li>
            <li>Irvine Animal Shelter Website</li>
        </ul>
    </div>
</div>

</body>
</html>