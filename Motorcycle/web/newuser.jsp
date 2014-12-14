<jsp:include page="/includes/header.jsp" />


<section>
<div class='error'> ${message} </div>
    <form name="reg_form" id= "reg_form" action="/JHUWebApp/user/register" method="post">
            <fieldset style="display: inline-block;">
            <legend>User Information</legend>
            <label>First Name</label>
            <input type="text" id="firstName" size="30" name="firstName" value="${user.firstName}" required>
            <span class="required">*</span><br>
            
            <label>Last Name</label>
            <input type="text" id="lastName" size="30" name="lastName" value="${user.lastName}" required>
            <span class="required">*</span><br>
            
            <label>Username</label>
            <input type="text" id="username" size="30" name="username" value="${user.userName}" required>
            <span class="required">*</span><br>
            
            <label>Password</label>
            <input type ="password" id="password" size="30" name="password" value="" required>
            <span class="required">*</span><br>
            
            <label>E-mail</label>
            <input type="email" id="email" size="30" name="email" value="${user.email}" required>
            <span class="required">*</span><br>
            
            <label>&nbsp;</label>
            <input type="submit" value="Register User" class="action">
            </fieldset>
            

    </form>

</section>


<jsp:include page="/includes/footer.jsp" />