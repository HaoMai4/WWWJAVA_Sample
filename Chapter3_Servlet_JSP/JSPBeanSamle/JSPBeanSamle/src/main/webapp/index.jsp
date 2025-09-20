<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Bean Sample</title>
</head>
<body>
<h1><%= "JSP Bean Sample" %>
</h1>
<jsp:useBean id="date" class="java.util.Date"/>
<p>
    The date/time is <%=date%>
    <jsp:useBean id="student" class="iuh.fit.se.beans.Student">
        <jsp:setProperty name="student" property="firstName" value="Nguyen"/>
        <jsp:setProperty name="student" property="lastName" value="An"/>
        <jsp:setProperty name="student" property="age" value="20"/>
    </jsp:useBean>
<p>
    Student First Name:
    <jsp:getProperty name="student" property="firstName"/>
</p>

<p>
    Student Last Name:
    <jsp:getProperty name="student" property="lastName"/>
</p>

<p>
    Student Age:
    <jsp:getProperty name="student" property="age"/>
</p>
</body>
</html>