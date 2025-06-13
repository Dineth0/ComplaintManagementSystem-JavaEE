<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 6/12/2025
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.dto.UserDTO" %>
<%
  UserDTO userDTO = (UserDTO) session.getAttribute("admin");
  if(userDTO == null || !userDTO.getRole().equalsIgnoreCase("admin")){
    response.sendRedirect("login.jsp");
  }
%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Admin</h1>
  </body>
</html>
