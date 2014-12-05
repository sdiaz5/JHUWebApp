<jsp:include page="/includes/header.jsp" />


<section class="cart">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<h1>Your cart</h1>
  <c:choose>
      <c:when test="${emptyCart != null}">
          <p>Your cart is empty.</p>
      </c:when>
      <c:otherwise>
        <table>
           <tr>
            <th>Qty</th>
            <th>Description</th>
            <th>Price</th>
            <th>Amount</th>
            <th>&nbsp;</th>
         </tr>
          <c:forEach var="item" items="${cart.cartItems}">
            <tr class="cart_row">
              <td>
                <form action=" " method="post">
                  <input type="hidden" name="productNumber" 
                         value="<c:out value='${item.product.productNumber}'/>">
                  <input type=text name="quantity" 
                         value="<c:out value='${item.quantity}'/>" id="quantity">
                  <input type="submit" value="Update">
                </form>                  
              </td>
              <td>${item.product.description}</td>
              <td>${item.product.priceCurrencyFormat}</td>
              <td>${item.totalCurrencyFormat}</td>
            
              <td>
                <form action=" " method="post">
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

    <form action="<c:url value='/cart/user.jsp'/>" method="post">
      <input type="submit" value="Checkout">
    </form>
</c:if>
</section>


<jsp:include page="/includes/footer.jsp" />