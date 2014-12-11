<jsp:include page="/includes/header.jsp" />


<section class="cart">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Your invoice</h1>
<h2>Confirmation Number: ${confirmationNumber}<h2>       


<table>
  <tr>
    <th>Date</th>
    <td>${invoiceDate}</td>
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

  <c:forEach var="item" items="${pastInvoice.cartItems}">
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
    <td></td>
    <td>${pastInvoice.cartTotalCurrencyFormat}</td>
  </tr>
</table>

<form action="<c:url value='/order/pastOrders'/>" method="get" id="float_left">
  <input type="submit" value="Go to Past Orders">
</form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />