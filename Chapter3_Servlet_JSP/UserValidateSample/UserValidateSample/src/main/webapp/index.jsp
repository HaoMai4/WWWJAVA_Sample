<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Register User</title></head>
<body>
<h2>User Registration</h2>
<c:if test="${not empty errors}">
    <ul style="color: red;">
        <c:forEach var="error" items="${errors}">
            <li>${error.propertyPath}: ${error.message}</li>
        </c:forEach>
    </ul>
</c:if>
<form action="${pageContext.request.contextPath}/register" method="post">
    Name: <input type="text" name="name" value="${user.name}"/> <br/>
    Email: <input type="text" name="email" value="${user.email}"/> <br/>
    Country: <input type="text" name="country" value="${user.country}"/> <br/>
    <input type="submit" value="Register"/>
</form>

</body>
</html>