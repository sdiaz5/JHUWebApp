<jsp:include page="/includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section class="cart">

<h1>Enter your credit card information</h1>

<form action="<c:url value='/order/thanks' />" method="post">
   <table>
   <tr> 
       <td><b>Credit card type</b></td>
      <td>
          <select name="creditCardType" size="1">
            <option value="Visa">Visa</option>
            <option value="Mastercard">Mastercard</option>
            <option value="AmEx">American Express</option>
            <option value="Discover">Discover</option>
          </select>
      </td>
   </tr>
   <tr> 
      <td><b>Card number (dddd-dddd-dddd-dddd)</b></td>
      <td><input type="text" pattern="\b\d{4}[ -]?\d{4}[ -]?\d{4}[ -]?\d{4}\b" size="20" name="creditCardNumber" 
                 maxlength="25" required></td>
   </tr>
   <tr> 
      <td><b>Expiration date (mm/yyyy)</b></td>
      <td><select name="creditCardExpirationMonth">
            <option value="01">01</option>
            <option value="02">02</option>
            <option value="03">03</option>
            <option value="04">04</option> 
            <option value="05">05</option>
            <option value="06">06</option>
            <option value="07">07</option>
            <option value="08">08</option> 
            <option value="09">09</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option> 
          </select>
          /
          <select name="creditCardExpirationYear">
            <option value="2014">2014</option>
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option> 
            <option value="2018">2018</option>
            <option value="2019">2019</option>
            <option value="2020">2020</option>

          </select>
      </td>
   </tr>
   <tr>
      <td></td>
      <td><input type="submit" value="Submit Order"></td>
   </tr>
   </table>
</form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />