<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Complaint Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #74ebd5, #ACB6E5);
            height: 100vh;
        }
        .login-card {
            border: none;
            border-radius: 20px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
        }
        .form-control:focus {
            box-shadow: none;
            border-color: #4A90E2;
        }
        .btn-primary {
            background-color: #4A90E2;
            border: none;
        }
        .btn-primary:hover {
            background-color: #357ABD;
        }
    </style>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="col-md-5">
        <div class="card login-card p-4">
            <div class="card-body">
                <h3 class="text-center mb-4">CMS Login</h3>
                <form action="LoginServlet" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control rounded-3" id="username" name="username" placeholder="Enter your username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control rounded-3" id="password" name="password" placeholder="Enter your password" required>
                    </div>
                    <div class="mb-3">
                        <label for="role" class="form-label">Role</label>
                        <input type="text" class="form-control rounded-3" id="role" name="role" placeholder="Enter user or admin" required>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary rounded-3">Login</button>
                    </div>
<%--                    <%--%>
<%--                        String error = request.getParameter("error");--%>
<%--                        if (error != null) {--%>
<%--                    %>--%>
<%--                    <div class="alert alert-danger mt-3 rounded-3" role="alert">--%>
<%--                        Invalid username or password.--%>
<%--                    </div>--%>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
