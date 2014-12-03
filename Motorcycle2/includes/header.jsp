<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
<head>
<meta charset=utf-8" />
<title>Motorcycle Sales</title>
<link rel="stylesheet" href="<c:url value='/styles/tooplate_style.css'/>">

</head>
<body>

<div id="tooplate_wrapper">
	
    <div id="tooplate_menu">
        <ul>
            <li><a href="<c:url value='/index.html'/>" class="current">Home</a></li>
            <li><a href="<c:url value='/motorcycle.jsp'/>">Motorcycle</a></li>
            <li><a href="<c:url value='/accessories.jsp'/>">Accessories</a></li>
            <li><a href="<c:url value='/account.jsp'/>">My Account</a></li>
            <li><a href="<c:url value='/cart.jsp'/>">Checkout</a></li>
        </ul>    	
    </div> <!-- end of menu -->
    
    <div id="tooplate_middle">
    	<div id="mid_title">
            <br>Welcome to Motorcycle Sales!</br>	
        </div>

    </div> <!-- end of middle -->