    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <aside id="sidebar">
        <c:choose><c:when test="${user != null}">
            <form method="post" action="<c:url value='/order/addItem'/>">
                <input type="hidden" name="productNumber" value="${motorcycle.productNumber}">
                <input type="image" src="<c:url value='/images/addbutton.jpg'/>" 
                       width="200" alt="Add to Cart">
            </form>
        </c:when></c:choose>
            <a href="<c:url value='/catalog/index.jsp'/>">
                <img src="<c:url value='/images/shoppingbutton.jpg'/>"
                     width="200" alt="Continue Shopping" class="top_margin"
            </a>
    </aside>
