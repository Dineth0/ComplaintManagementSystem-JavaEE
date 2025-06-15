<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 6/12/2025
  Time: 8:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.dto.UserDTO" %>
<%@ page import="lk.ijse.gdse.dto.ComplaintDTO" %>
<%@ page import="java.util.List" %>
<%
  UserDTO userDTO = (UserDTO) session.getAttribute("admin");
  if(userDTO == null || !userDTO.getRole().equalsIgnoreCase("admin")){
    response.sendRedirect("login.jsp");
  }
%>
<html>
  <head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">

  </head>
  <body>
  <h1 class="text-center" style="color: black">Welcome to the Admin Dashboard</h1>
  <div class="card mt-5 shadow-sm">

    <div class="card-body">
      <form action="Admin" method="get">
      <div class="table-responsive" id="table">
        <table class="table table-hover table-bordered align-middle text-center" id="employee-table">
          <thead class="table-light">
          <tr>
            <th>Action</th>
            <th>User Name</th>
            <th>Title</th>
            <th>Complaint</th>
            <th>Date</th>
          </tr>
          </thead>
          <tbody id="tbody">
          <%
            List<ComplaintDTO> complaintList = (List<ComplaintDTO>) request.getAttribute("complaints");
            if(complaintList != null){
              for (ComplaintDTO complaintDTO : complaintList) {
          %>
          <tr>
            <td>
              <a href="ComplaintViewServlet?id=<%= complaintDTO.getId() %>
                           &userName=<%= complaintDTO.getUserName() %>
                           &title=<%= complaintDTO.getTitle() %>
                           &complaint=<%= complaintDTO.getComplaint() %>
                           &date=<%= complaintDTO.getDate() %>"
                 class="btn btn-danger">View</a>
            </td>
            <td><%= complaintDTO.getUserName()%></td>
            <td><%= complaintDTO.getTitle()%></td>
            <td><%= complaintDTO.getComplaint() %></td>
            <td><%= complaintDTO.getDate() %></td>
          </tr>
          <%
              }
            }
          %>
          </tbody>
        </table>
      </div>
      </form>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>


  </body>
</html>

