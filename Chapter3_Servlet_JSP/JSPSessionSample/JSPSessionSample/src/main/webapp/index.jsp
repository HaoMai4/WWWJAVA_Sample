<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Session Sample</title>
</head>
<body>
<h1>JSP Session Sample</h1>
<!-- HTML form -->
<form action="session" method="post">
    Add new item: <input type="text" name="item"/>
    <input type="submit" value="Submit"/>
</form>
<hr>
<!-- Display all "To Do" item from session -->
<b>List Items:</b><br/>
<ol>
    <%
        List<String> items = (List<String>) request.getAttribute("todoList");
        if (items != null) {
            for (String temp : items) {
                out.println("<li>" + temp + "</li>");
            }
        }
    %>
</ol>
</body>
</html>