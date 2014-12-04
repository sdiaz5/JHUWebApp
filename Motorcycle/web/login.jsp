<jsp:include page="/includes/header.jsp" />


<section>

<h1>Login Form</h1>
<div class='error'> ${message} </div>
<p>Please enter a username and password to continue.</p>
<form action="user/login" method="post">
    <label>Username</label>
    <input type="text" name="username"><br>
    <label>Password</label>
    <input type="password" name="password"><br>
    <label>&nbsp;</label>
    <input type="submit" value="Login">
</form>
<Button onclick='window.location.href="/JHUWebApp/newuser.jsp"'>
                New User</Button>

</section>


<jsp:include page="/includes/footer.jsp" />