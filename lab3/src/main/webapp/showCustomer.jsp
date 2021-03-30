
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 27.02.2021
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <%
        Object number = request.getAttribute("customerNumber");
        Object name = request.getAttribute("customerName");
        Object zip_code= request.getAttribute("customerZipCode");
        Object city= request.getAttribute("customerCity");
        if(name==null || number == null || zip_code == null || city==null ){
           out.println("Incorrect input of customer number");
        }
        else {
            out.print("<p>Customer number: " + number + "</p>");
            out.print("<p>Customer name: " + name + "</p>");
            out.print("<p>Customer zip-code: " + zip_code + "</p>");
            out.print("<p>Customer city: " + city + "</p>");
        }
    %>
</div>
</body>
</html>
