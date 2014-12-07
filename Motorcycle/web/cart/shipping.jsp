<jsp:include page="/includes/header.jsp" />

<section class="cart">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Enter your name and contact information</h1>

<form action="<c:url value='/user/setShipping' />" method=post>
    <p id="required">Required <span class="required">*</span></p>
    
    <label>First Name</label>
    <input type="text" name="firstName"  maxlength=20 
               value="${user.firstName}" disabled="disabled">
    <p class="required"></p><br>
    
    <label>Last Name</label>
    <input type="text" name="lastName" value="${user.lastName}" disabled="disabled">
    <p class="required"></p><br>
    
    <label>Email Address</label>
    <input type="email" name="email" value="${user.email}" disabled="disabled">
    <p class="required"></p><br>

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

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />