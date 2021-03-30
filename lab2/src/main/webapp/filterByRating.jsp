<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 09.03.2021
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Filter</title>
</head>
<body>
<div>
    <form method="post">
        <label>Enter the rating:
            <input type="text" name="rating"><br/>
        </label>
        <p style="color:${'red'}"><%= request.getAttribute("WrongRating")%></p>
        <input type="submit" name="filter" value="Display serials whose rating does not exceed the specified rating">
    </form>
</div>
<div>
    <button onclick="location.href='/lab2_war/'">Back to main</button>
</div>
</body>
</html>

