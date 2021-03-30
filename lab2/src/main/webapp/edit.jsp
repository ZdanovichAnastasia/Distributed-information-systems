<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 12.02.2021
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form method="post">
        <label>Enter the name of the serial:
            <input type="text" name="name"><br/>
        </label>
        <label>Enter the rating of the serial:
            <input type="text" name="rating"><br/>
        </label>
        <p style="color:${'red'}"><%= request.getAttribute("wrongRating")%></p>
        <label>How many episodes in the serials?
            <input type="number" name="count"><br/>
        </label>
        <p style="color:${'red'}"><%= request.getAttribute("wrongCount")%></p>
        <%
            ArrayList<String> listSerial = (ArrayList<String>) request.getAttribute("SerialsNamesList");
            out.println("<label>Select the name of the serial you want to edit: <select name=\"nameS\">");
            if (listSerial != null && !listSerial.isEmpty()) {
                for (String s : listSerial) {
                    out.println("<option>"+s+"</option>");
                }
            }
            out.println("</select><br />\n" +
                    "</label>");
        %><br/>
        <input type="submit" name="editS" value="Edit Serial">
    </form>
</div>
<div>
    <button onclick="location.href='/lab2_war/'">Back to main</button>
</div>
</body>
</html>
