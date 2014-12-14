<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp" />

<section>
    <h1>Order History:</h1>

    <c:if test="${confirmationNumbers.isEmpty()}">
        <p class = "message">You have no past orders.  Please place an order.</p>
    </c:if>


    <c:if test="${!confirmationNumbers.isEmpty()}">
    <table>


    <c:forEach var="confNum" items="${confirmationNumbers}">
    <tr>
        <td>
            <a href="<c:url value = '/order/${confNum}'/>">Order Number: ${confNum}</a>
        </td>
    </tr>
    </c:forEach>

    </table>
    </c:if>

</section>

<jsp:include page="/includes/footer.jsp" />