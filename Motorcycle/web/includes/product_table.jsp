<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test = "${product.type == 'MOTORCYCLE'}">
        <img src="${product.imageURL}" width="233" height="175" alt="Motorcycle Image">
        <h2>Brand: ${product.brand}</h2>
        <h2>Name: ${product.name}</h2>
        <h2>Type: ${product.bikeType}</h2>
        <p>Description: ${product.description}</p>
        <p>Condition: ${product.condition}</p>
        <p>Product Number: ${product.productNumber}</p>
        <p>Quantity Available: ${product.quantity}</p>
        <p>Price: ${product.priceCurrencyFormat}</p>
    </c:when>
    <c:otherwise>
        <img src="${product.imageURL}" width="175" height="175" alt="Product Image">
        <h2>Name: ${product.name}</h2>
        <h2>Size: ${product.size}</h2>
        <p>Description: ${product.description}</p>
        <p>Product Number: ${product.productNumber}</p>
        <p>Quantity Available: ${product.quantity}</p>
        <p>Price: ${product.priceCurrencyFormat}</p>
    </c:otherwise>
</c:choose>