
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.dto.UserDTO" %>
<%@ page import="lk.ijse.gdse.dto.ComplaintDTO" %>
<%@ page import="java.util.List" %>


<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">

  <style>
    body{
      background: linear-gradient(to right,#0F1035, #201658)
    }
  </style>

</head>
<body>
<main class="col-md-12 ms-sm-auto px-4">
  <div class="pt-5">
    <h1 class="text-center" style="color: white">Welcome to the Admin Dashboard</h1>


    <div class="card mt-5 shadow-sm">
      <div class="card-header  text-black">
        <h5 class="mb-0 text-center">Save Complaints</h5>
      </div>

      <%
        String status = request.getParameter("status");
        if ("success".equals(status)) {
      %>
      <div class="alert alert-success text-center mt-3">Complaint saved successfully!</div>
      <%
      } else if ("fail".equals(status)) {
      %>
      <div class="alert alert-danger text-center mt-3">Failed to save complaint. Please try again.</div>
      <%
      } else if ("error".equals(status)) {
      %>
      <div class="alert alert-warning text-center mt-3">An error occurred while saving the complaint.</div>
      <%
        }
      %>


      <div class="card-body">
        <form action="Admin" method="POST">
          <div class="row g-3">
            <%
              ComplaintDTO complaint = (ComplaintDTO) request.getAttribute("complaint");
            %>
            <div class="col-md-6">
              <label for="name" class="form-label">User Name</label>
              <input type="text" class="form-control" id="name" name="userName" placeholder="Enter name" value="<%= complaint != null ? complaint.getUserName() : "" %>"  required>
            </div>
            <div class="col-md-6">
              <label for="title" class="form-label">Complaint Title</label>
              <textarea class="form-control" id="title" name="title" placeholder="Enter Title" required><%= complaint != null ? complaint.getTitle() : "" %></textarea>
            </div>
            <div class="col-md-6">
              <label for="complaint" class="form-label">Complaint</label>
              <textarea t class="form-control" id="complaint" name="complaint" placeholder="Enter Complaint" required><%= complaint != null ? complaint.getComplaint() : "" %></textarea>
            </div>
            <div class="col-md-6">
              <label for="date" class="form-label">Employee Image</label>
              <input type="date" name="date" class="form-control" id="date" value="<%= complaint != null ? complaint.getDate() : "" %>" required>
            </div>
            <fieldset class="border p-4 rounded shadow-sm">
              <legend class="float-none w-auto px-3">Admins Only</legend>
              <div class="row">
                <div class="col-md-6 md-3">
                  <label for="status" class="form-label">Status</label>
                  <input type="text" name="status" class="form-control" id="status" value="<%= complaint != null ? complaint.getStatus() : "" %>" required>

                </div>

                <div class="col-md-6 mb-3">
                  <label for="remark" class="form-label">Remark</label>
                  <input type="text" name="remark" class="form-control" id="remark" value="<%= complaint != null ? complaint.getRemark() : "" %>"  required>
                </div>
              </div>
            </fieldset>

          </div>
          <div class="text-center mt-4">
            <button type="submit" id="save" name="action" value="save" class="btn btn-black">Submit Complaint</button>
            <button type="submit" id="update" name="action" value="update" class="btn btn-black">Update Complaint</button>
            <button type="submit" id="delete" name="action" value="delete" class="btn btn-black">Delete Complaint</button>

          </div>
          <input type="hidden" id="id" name="id" value="<%= complaint != null ? complaint.getId() : "" %>" />
          <input type="hidden" id="uid" name="uid" value="<%= complaint != null ? complaint.getUid() : "" %>" />

        </form>
      </div>
    </div>

  </div>
  <div class="card mt-5 shadow-sm">

    <div class="card-body">
      <div class="table-responsive" id="table">
        <table class="table table-hover table-bordered align-middle text-center" id="employee-table">
          <thead class="table-light">
          <tr>
            <th>User Name</th>
            <th>Title</th>
            <th>Complaint</th>
            <th>Date</th>
            <th>Status</th>
            <th>Remark</th>
          </tr>
          </thead>
          <tbody id="tbody">
          <%
            List<ComplaintDTO> complaintList = (List<ComplaintDTO>) request.getAttribute("complaints");
            if(complaintList != null){
              for (ComplaintDTO complaintDTO : complaintList) {
          %>
          <tr onclick="fillForm('<%= complaintDTO.getUserName()%>',' <%= complaintDTO.getTitle()%>', '<%= complaintDTO.getComplaint() %>', '<%= complaintDTO.getDate() %>', '<%= complaintDTO.getId() %>', '<%= complaintDTO.getStatus() %>', '<%= complaintDTO.getRemark() %>')">

            <td><%= complaintDTO.getUserName()%></td>
            <td><%= complaintDTO.getTitle()%></td>
            <td><%= complaintDTO.getComplaint() %></td>
            <td><%= complaintDTO.getDate() %></td>
            <td><%= complaintDTO.getStatus() %></td>
            <td><%= complaintDTO.getRemark() %></td>
          </tr>
          <%
              }
            }
          %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>

<script>
  const today = new Date().toISOString().split("T")[0];
  document.getElementById('date').value = today;

  function  fillForm(name,title,complaint,date,id, status, remark){
    document.getElementById('name').value = name;
    document.getElementById('title').value = title;
    document.getElementById('complaint').value = complaint;
    document.getElementById('date').value = date;
    document.getElementById('id').value = id;
    document.getElementById('status').value = status;
    document.getElementById('remark').value = remark;

  }
</script>

</body>
</html>
