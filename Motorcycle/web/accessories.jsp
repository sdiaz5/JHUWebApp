<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Shopping</title>
<link href="tooplate_style.css" rel="stylesheet" type="text/css" />

</head>
<body>

<div id="tooplate_wrapper">
	
    <div id="tooplate_menu">
        <ul>
            <li><a href="index.html">Home</a></li>
            <li><a href="motorcycle.jsp">Motorcycle</a></li>
            <li><a href="accessories.jsp" class="current">Accessories</a></li>
            <li><a href="account.jsp">My Account</a></li>
            <li><a href="cart.jsp">Checkout</a></li>
        </ul>    	
    </div> <!-- end of menu -->
    
    <div id="tooplate_middle">
    	<div id="mid_title">
            <br>Welcome to Motorcycle Sales!</br>	
        </div>

    </div> <!-- end of middle -->
    
    <div id="tooplate_main">
    	<div class="col_w960">
            <h2>Motorcycle Accessories</h2>
                <form method="post" action=" " style="display: inline">
                    Motorcycle Helmet<br></br><img width="100" height="100" src="images/helmet.jpg" alt="image" /><br />
                    Price: $100<br />
                    Qty <input type="text" id="quantity" size="5" name="quantity" value="${product.quantity}" /> 
                    <input type="submit" value="Update" />
                </form>
                    <br></br>
                <form method="post" action=" " style="display: inline">
                    Motorcycle Jacket<br></br><img width="100" height="100" src="images/jacket.jpg" alt="image" /><br />
                    Price: $200<br />
                    Qty <input type="text" id="quantity" size="5" name="quantity" value="${product.quantity}" /> 
                    <input type="submit" value="Update" />
                </form>
                    <br></br>
                <form method="post" action=" " style="display: inline">
                    Motorcycle Gloves<br></br><img width="100" height="100" src="images/gloves.jpg" alt="image" /><br />
                    Price: $30<br />
                    Qty <input type="text" id="quantity" size="5" name="quantity" value="${product.quantity}" /> 
                    <input type="submit" value="Update" />
                </form>
        </div>
    </div> <!-- end of main -->
    
</div> <!-- end of wrapper -->

<div id="tooplate_footer_wrapper">
            <div id="tooplate_footer">
                Copyright © 2048 - Designed by Team A 
            </div> <!-- end of footer -->
</div>

</body>
</html>