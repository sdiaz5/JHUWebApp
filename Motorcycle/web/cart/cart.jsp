<jsp:include page="/includes/header.jsp" />


<section class="cart">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Your cart</h1>
<p id="message">${message}</p>
  <c:choose>
      <c:when test="${cart == null}">
          <p>Your cart is empty.</p>
      </c:when>
      <c:otherwise>
        <table style="table-layout:fixed;">
           <tr style="text-align: left;">
            <th style="width:20%;">Qty</th>
            <th>Name</th>
            <th>Price</th>
            <th>Amount</th>
            <th>&nbsp;</th>
         </tr>
          <c:forEach var="item" items="${cart.cartItems}">
            <tr style="width:auto; text-align: left;" class="cart_row">
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
                      <td>${item.product.priceCurrencyFormat}</td>
                      <td>${item.totalCurrencyFormat}</td>
                  </c:when>
                  <c:otherwise>
                      <td>${item.product.type}: ${item.product.name}</td>
                      <td>${item.product.priceCurrencyFormat}</td>
                      <td>${item.totalCurrencyFormat}</td>
                  </c:otherwise>
              </c:choose>
      
              <td>
                  <form action="<c:url value='/order/removeItem' /> " method="post">
                  <input type="hidden" name="productNumber" 
                         value="<c:out value='${item.product.productNumber}'/>">
                  <input type="submit" value="Remove">
                </form>                  
              </td>
            </tr>
          </c:forEach>
            </table>
      </c:otherwise>
  </c:choose>

<form action="<c:url value='/catalog'/>" method="get" id="float_left">
  <input type="submit" value="Continue Shopping">
</form>
  
<c:if test="${emptyCart == null}">
    <form action="<c:url value='/cart/shipping.jsp'/>" method="post">
      <input type="submit" value="Checkout">
    </form>
</c:if>
</section>


<jsp:include page="/includes/footer.jsp" />