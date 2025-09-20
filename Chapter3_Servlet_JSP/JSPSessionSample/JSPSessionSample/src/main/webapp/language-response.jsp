<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Language Response</title>
</head>
<body>
<h1>Language Response</h1>
<%
    Cookie[] cookies = request.getCookies();
    String favLang = null;
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("favoriteLanguage".equals(c.getName())) {
                favLang = c.getValue();
                break;
            }
        }
    }
%>
<% if (favLang != null) { %>
<p>
    Your favorite language is:
    <b><%= favLang %></b>
</p>
<% } %>
</body>
</html>
