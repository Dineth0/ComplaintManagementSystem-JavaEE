<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.dto.UserDTO" %>
<%@ page import="lk.ijse.gdse.dto.ComplaintDTO" %>
<%@ page import="java.util.List" %>


<html>
<head>
    <title>UserDashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <link href="user.css" rel="stylesheet" type="text/css">

</head>
<body>
<%
    ComplaintDTO complaint = (ComplaintDTO) request.getAttribute("complaint");
%>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");

%>
<header>
    <nav class="navbar">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src="assests/cmsLogo.png" alt="Logo" width="50" height="44" class="d-inline-block align-text-top">
                Complaint Management System
            </a>
            <div  class="d-flex align-item-center" style="gap: 10px">
                <label class="form-label text-white mb-0">You</label>
                <input type="text" name="name" value="<%= user.getName() %>" readonly class="form-control w-auto">

            </div>
            <form action="LogoutServlet" method="get">
                <button type="submit" class="btn ms-2">Logout</button>
            </form>
        </div>
    </nav>
</header>
<main class="col-md-12 ms-sm-auto px-4">
    <div class="pt-5">
        <h1 class="text-center" style="color: white">Welcome to the User Dashboard</h1>


        <div class="card mt-5 shadow-sm" style="border-radius: 20px;">
            <div class="card-header form-header  text-black" style="background-color: #3E5879; ">
                <h5 class="mb-0 text-center">Save Complaints</h5>
            </div>
            <% String status = (String) request.getAttribute("status");
            %>
            <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
                <div id="statusToast" class="toast align-items-center text-white bg-success border-0" role="alert" aria-live="assertive" aria-atomic="true">
                    <div class="d-flex">
                        <div class="toast-body" id="toast-body">

                        </div>
                        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                </div>
            </div>



            <div class="card-body">
                <form action="ComplaintServlet" method="POST" style="border-radius: 20px;">
                    <div class="row g-3">

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
                            <textarea class="form-control" id="complaint" name="complaint" placeholder="Enter Complaint" required><%= complaint != null ? complaint.getComplaint() : "" %></textarea>
                        </div>
                        <div class="col-md-6">
                            <label for="date" class="form-label">Date</label>
                            <input type="date" name="date" class="form-control" id="date" value="<%= complaint != null ? complaint.getDate() : "" %>" required>
                        </div>


                        <fieldset class="border p-4 rounded shadow-sm">
                            <legend class="float-none w-auto px-3">Admins Only</legend>
                            <div class="row">
                                <div class="col-md-6 md-3">
                                    <label for="status" class="form-label">Status</label>
                                    <input type="text" name="status" class="form-control" id="status" value="Still Not Seen Complaint" readonly>

                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="remark" class="form-label">Remark</label>
                                    <input type="text" name="remark" class="form-control" id="remark" value="No"  readonly>
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

                    <input type="hidden" id="uid" name="uid" value="<%= user.getId() %>" />



                </form>
            </div>
        </div>

    </div>
    <div class="card mt-5 shadow-sm">
        <div class="card-header  text-black" style="background-color: #3E5879;">
            <h5 class="mb-0 text-center">Your Complaints</h5>
        </div>
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
                    <tr onclick="fillForm('<%= complaintDTO.getUserName()%>','<%= complaintDTO.getTitle()%>', '<%= complaintDTO.getComplaint() %>', '<%= complaintDTO.getDate() %>', '<%= complaintDTO.getId() %>', '<%= complaintDTO.getStatus()%>', '<%= complaintDTO.getRemark()%>')">

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
                <button type="button" onclick="clearForm()" class="btn btn-secondary">New Complaint</button>

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
    window.addEventListener('load', function() {
        const urlParams = new URLSearchParams(window.location.search);
        const status = urlParams.get('status');

        // if (status === 'success') {
        //     // remove the ?status=success param and reload clean page
        //     window.history.replaceState({}, document.title, window.location.pathname);
        // }
    });

    const status = '<%= (status != null) ? status : "" %>';


    const toast = document.getElementById('statusToast');
    const toastBody = document.getElementById('toast-body');

    if (status === "saved") {
        toastBody.innerText = "Complaint Saved Successfully";
        toast.classList.replace('bg-danger', 'bg-success');
        new bootstrap.Toast(toast).show();
    } else if (status === "updated") {
        toastBody.innerText = "Complaint updated successfully";
        toast.classList.replace('bg-danger', 'bg-primary');
        new bootstrap.Toast(toast).show();
    } else if (status === "deleted") {
        toastBody.innerText = "Complaint Deleted successfully";
        toast.classList.replace('bg-danger', 'bg-warning');
        new bootstrap.Toast(toast).show();
    } else if (status === "fail") {
        toastBody.innerText = "fail";
        toast.classList.replace('bg-success', 'bg-danger');
        new bootstrap.Toast(toast).show();
    }


    if (status === "success") {
        document.getElementById('toast-body').innerText = "Complaint saved successfully!";
        document.getElementById('statusToast').classList.replace('bg-danger', 'bg-success');
        new bootstrap.Toast(document.getElementById('statusToast')).show();
        document.getElementById('name').value = "";
        document.getElementById('title').value = "";
        document.getElementById('complaint').value = "";
        document.getElementById('date').value = today;
        document.getElementById('id').value = "";
        document.getElementById('status').value = "Still Not Seen Complaint";
        document.getElementById('remark').value = "No";
    }


</script>


</body>
</html>