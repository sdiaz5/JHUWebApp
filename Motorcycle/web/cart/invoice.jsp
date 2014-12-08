<jsp:include page="/includes/header.jsp" />


<section class="cart">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Your invoice</h1>
<p> ${user.firstName} below is the invoice for your purchase. You can go back to
    your cart to edit its contents or continue to pay with a credit card.</p>

<table>
  <tr>
    <th>Date</th>
    <td>${invoice.invoiceDateDefaultFormat}</td>
    <td></td>
  </tr>
  <tr>
      <td colspan="3"><hr></td>
  </tr>
  <tr>
     <th>Qty</th>
    <th>Name</th>
    <th>Price</th>
    <th>Amount</th>
    <th>&nbsp;</th>
 </tr>

  <c:forEach var="item" items="${cart.cartItems}">
            <tr style="width:auto;" class="cart_row">
              <td> ${item.quantity} </td>
              <c:choose>
                  <c:when test = "${item.product.type == 'MOTORCYCLE'}">
                      <td>${item.product.bikeType}: ${item.product.brand} ${item.product.name}</td>
                      <td>${item.product.priceCurrencyFormat}</td>
                      <td>${item.totalCurrencyFormat}</td>
                  </c:when>
                  <c:otherwise>
                      <td>${item.product.type}: ${item.product.name}</td>
                      <td>${item.product.priceCurrencyFormat}</td>
                      <td>${item.totalCurrencyFormat}</td>
                  </c:otherwise>
              </c:choose>
            </tr>
          </c:forEach>

  <tr>
    <th>Total:</th>
    <td></td>
    <td>${invoice.invoiceTotalCurrencyFormat}</td>
  </tr>
</table>

<form action="<c:url value='/cart/cart.jsp'/>" method="get" id="float_left">
  <input type="submit" value="Go to Cart">
</form>

<form action="<c:url value='/cart/credit_card.jsp' />" method="post">
     <input type="submit" value="Continue">
</form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />