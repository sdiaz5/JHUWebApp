<jsp:include page="/includes/header.jsp" />


<section>
<div class='error'> ${message} </div>
    <form name="reg_form" id= "reg_form" action="/JHUWebApp/user/register" method="post">
            <fieldset>
            <legend>User Information</legend>
            First Name:<input type="text" id="firstName" size="30" name="firstName" value="${user.firstName}" required><br>
            Last Name: <input type="text" id="lastName" size="30" name="lastName" value="${user.lastName}" required><br>
            Username:  <input type="text" id="username" size="30" name="username" value="${user.userName}" required><br>
            Password: <input type ="text" id="password" size="30" name="password" value="" required><br>
            E-mail: <input type="email" id="email" size="30" name="email" value="${user.email}" required><br>
            </fieldset>
            <input type="submit" value="Register User" class="action">
    </form>

</section>


<jsp:include page="/includes/footer.jsp" />