<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/custom-tag.tld" prefix="custom" %>
<!DOCTYPE html>
<html>
<head>
    <title>Custom Tag Demo</title>
</head>
<body>
<h2>Custom format:</h2>
<custom:today format="dd/MM/yyyy HH:mm" />

<h2>Repeat Tag:</h2>
<custom:repeat times="10">
    <p>This will be repeated 3 times!</p>
</custom:repeat>
</body>
</html>