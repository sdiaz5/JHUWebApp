    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <aside id="sidebar">
        <c:choose><c:when test="${user != null}">
            <form method="post" action="<c:url value='/order/addItem'/>">
                <input type="hidden" name="productNumber" value="${product.productNumber}">
                <input type="image" src="<c:url value='/images/addtocart.gif'/>" 
                       width="113" alt="Add to Cart">
            </form>
        </c:when></c:choose>
            <a href="<c:url value='/catalog/index.jsp'/>">Browse</a>
    </aside>
