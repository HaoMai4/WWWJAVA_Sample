<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Choose Favorite Language</title>
</head>
<body>
<h2>Select your favorite language</h2>
<form action="cookie" method="post">
    <select name="favoriteLanguage">
        <option value="Java">Java</option>
        <option value="Python">Python</option>
        <option value="C#">C#</option>
        <option value="Go">Go</option>
        <option value="JavaScript">JavaScript</option>
    </select>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
