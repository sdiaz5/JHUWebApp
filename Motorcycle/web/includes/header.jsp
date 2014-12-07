<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>

<html>
<head>
    <meta charset="utf-8">
    <title>Motorcycles R Us</title>
    <link rel="stylesheet" href="<c:url value='/styles/main.css'/> ">
    <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
</head>

<body>

    <header>
        <img src="<c:url value='/images/open_road.jpg'/>" 
             alt="Motorcycles R Us"><br>
        <h1>Motorcycles R Us</h1>
        <h2>The best quality motorcycles and accessories!</h2>
    </header>
            
    <nav id="nav_bar">
        <ul>
            <li><a href="<c:url value='/index.jsp'/>">
                Home</a></li>
            <li><a href="<c:url value='/catalog' />">
                  Browse Inventory</a></li>
            
            <c:choose> 
                <c:when test="${user == null}">
                    <li><a href="<c:url value='/login.jsp' />">
                        Login</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value='/account.jsp'/>">
                        My Account</a></li>
                    <li><a href="<c:url value='/cart/cart.jsp'/>">
                        Checkout</a></li>
                        <li><form class="inline" method='post' name="logout" action="<c:url value='/user/logout'/>"><a href="javascript: document.logout.submit();">
                                    Logout</a></form></li>
                </c:otherwise>
            </c:choose>
        </ul>  
    </nav> 
             