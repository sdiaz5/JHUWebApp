    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Motorcycle Rentals 4 U</title>
<link href="tooplate_style.css" rel="stylesheet" type="text/css" />

</head>
<body>

<div id="tooplate_wrapper">
	
    <div id="tooplate_menu">
        <ul>
            <li><a href="index.html" class="current">Home</a></li>
            <li><a href="motorcycle.jsp">Motorcycle</a></li>
            <li><a href="accessories.jsp">Accessories</a></li>
            <li><a href="account.jsp">My Account</a></li>
            <li><a href="cart.jsp">Checkout</a></li>
        </ul>    	
    </div> <!-- end of menu -->
    
    <div id="tooplate_middle">
    	<div id="mid_title">
            <br>Welcome to Motorcycle Sales!</br>	
        </div>

    </div> <!-- end of middle -->
    
    <form name="reg_form" id= "reg_form" action=" " method="post">
            <fieldset>
            <legend>User Information:</legend>
            Name:   <input type="text" id="name" size="30" name="name" value=" " required><br>
            Username: <input type="text" id="username" size="30" name="username" value=" " required><br>
            E-mail: <input type="email" id="email" size="30" name="email" value=" " required><br>
            </fieldset>

            <div class="center">
                <input type="submit" value="Register User" class="action">
            </div>
    </form>
</body>
</html>