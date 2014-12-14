    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <aside>
        <c:choose><c:when test="${user != null && product.quantity != 0}">
            <form method="post" action="<c:url value='/order/addItem'/>" style="display:inline;">
                <input type="hidden" name="productNumber" value="${product.productNumber}">
                <input type="image" src="<c:url value='/images/addbutton.jpg'/>" 
                    width="200" alt="Add to Cart">
            </form>
        </c:when></c:choose>
        <form method="post" action="<c:url value='/catalog/index.jsp'/>" style="display:inline;">
            <input type="image" src="<c:url value='/images/shoppingbutton.jpg'/>" 
                   width="200" alt="Continue Shopping">
        </form>           
    </aside>