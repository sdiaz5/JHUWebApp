<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp" />

<section>
    <c:if test="${product.quantity == 0}">
        <p class = "message">We're sorry! This item is out of stock. Please come back later!</p>
    </c:if>
    <jsp:include page="/includes/product_table.jsp" />
</section>

<jsp:include page="/includes/column_right_button.jsp" />
<jsp:include page="/includes/footer.jsp" />