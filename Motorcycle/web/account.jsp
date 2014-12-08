<jsp:include page="/includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section >

<h1>My Account</h1>

<!-- Edit User Info -->
<h2>Edit user information: </h2>
<div class="resp_note"> ${account_message} </div>

<form action="<c:url value='/user/setUser' />" method=post>
    
    <label>First Name</label>
    <input type="text" name="firstName"  maxlength=20 
               value="${user.firstName}" required>
    <p class="required">*</p><br>
    
    <label>Last Name</label>
    <input type="text" name="lastName" value="${user.lastName}" required>
    <p class="required">*</p><br>
    
    <label>Email Address</label>
    <input type="email" name="email" value="${user.email}" required>
    <p class="required">*</p><br>

    <label>Street Address</label>
    <input type="text" name="street" value="${user.contactInfo.street}" required> 
    <p class="required">*</p><br>
    
    <label>City</label>
    <input type="text" name="city" value="${user.contactInfo.city}" required>
    <p class="required">*</p><br>
    
    <label>State</label>
    <input type="text" name="state" value="${user.contactInfo.state}" required>
    <p class="required">*</p><br>
    
    <label>Zip Code</label>
    <input type="text" name="zip" value="${user.contactInfo.zipCode}" required>
    <p class="required">*</p><br>
    
    <label>&nbsp;</label>
    <input type="submit" value="Continue">
</form>
</section>

<!-- Edit password -->
<section>
    <div class="resp_note">${pass_message}</div>
    <h2> Change Password </h2>
    <form action="<c:url value='/user/changePass' />" method="post">
        Old Password:<input type="password" name="pass" value="" required><br>
        New Password:<input type="password" name="npass" value="" required><br>
        Re-Type Password:<input type="password" name="rpass" value="" required><br>
        <label> &nbsp; </label>
        <input type="submit" value="UpdatePassword">
    </form>
</section>
    
<!-- View Invoice History -->


<!--TODO Eli: Add javascript to verify password.-->

<jsp:include page="/includes/footer.jsp" />