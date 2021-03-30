<%@ page import="DataAccess.Models.SerialDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="BusinessLogic.Models.Serial" %><%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 02.03.2021
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <h1>Result</h1>
    <form method="post">
        <%
           ArrayList<Serial> listSerials = (ArrayList<Serial>) request.getAttribute("SerialsList");
            if(listSerials != null && !listSerials.isEmpty()){
                out.println("<ui>");
                for(Serial info: listSerials){
                    out.println("<li> Name: " + info.getName() + " &ensp;&ensp;Rating: " + info.getRating() + " &ensp;&ensp;Count episodes: " + info.getCount_episodes() + "</li>"); }
                out.println("</ui>");
            }else out.println("<p>There are no results yet!</p>");
        %>
    </form>
</div>
<div>
    <button onclick="location.href='/lab2_war/'">Back to main</button>
</div>
</body>
</html>
