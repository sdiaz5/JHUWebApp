    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <aside id="sidebar">
        <form method="post" action="<c:url value='/order/addItem'/>">
            <input type="hidden" name="productNumber" value="${product.productNumber}">
            <input type="image" src="<c:url value='/images/addtocart.gif'/>" 
                   width="113" alt="Add to Cart">
        </form>
            <a href="<c:url value='/catalog/index.jsp'/>">Browse</a>
    </aside>
