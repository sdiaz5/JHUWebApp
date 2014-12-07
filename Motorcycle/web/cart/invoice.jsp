<jsp:include page="/includes/header.jsp" />


<section class="cart">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Your invoice</h1>

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
    <th>Description</th>
    <th>Price</th>
    <th>Amount</th>
    <th>&nbsp;</th>
 </tr>

  <c:forEach var="item" items="${cart.cartItems}">
            <tr class="cart_row">
              <td>
                  <form action="<c:url value='/order/updateItem' /> " method="post">
                  <input type="hidden" name="productNumber" 
                         value="<c:out value='${item.product.productNumber}'/>">
                  <input type=text name="quantity" 
                         value="<c:out value='${item.quantity}'/>" id="quantity">
                  <input type="submit" value="Update">
                </form>                  
              </td>
              <c:choose>
                  <c:when test = "${item.product.type == 'MOTORCYCLE'}">
                      <td>${item.product.bikeType}: ${item.product.brand} ${item.product.name}</td>
                      <td>${item.product.description}</td>
                      <td>${item.product.priceCurrencyFormat}</td>
                      <td>${item.totalCurrencyFormat}</td>
                  </c:when>
                  <c:otherwise>
                      <td>${item.product.type}: ${item.product.name}</td>
                      <td>${item.product.description}</td>
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

<form action="<c:url value='/cart/user.jsp' />" method="post" id="float_left">
     <input type="submit" value="Edit Information">
</form>

<form action="<c:url value='/cart/credit_card.jsp' />" method="post">
     <input type="submit" value="Continue">
</form>

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />