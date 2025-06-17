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
            border: 1px solid black;
            border-radius: 20px;
            box-shadow: 0 8px 20px rgba(10, 0, 0, 0.15);
            background: linear-gradient(to right, #00809D, #5459AC);

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
        .loginBtn{
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        input:focus{
            background-color: transparent;
            outline: white;
        }
        label{
            color: white;
        }
        input.form-control, select.form-control{
            background-color: #2A4167;
            border: 1px solid #4C6FAF;
            color: white;
            border-radius: 10px;
            transition: 0.3s;
        }

        input.form-control:focus, select.form-control:focus{
            background-color: #1f2f4d;
            border-color: #00BCD4;
            box-shadow: 0 0 5px #00BCD4;
            color: white;
        }
    </style>
</head>
<body>
<div class="container d-flex flex-column justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="col-md-5">
        <div class="card login-card p-4">
            <div class="card-body">
                <h3 class="text-center mb-4">Login</h3>
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
                        <select class="form-control" name="role"  id="role" required>
                            <option value="">Select Role</option>
                            <option value="user">User</option>
                            <option value="admin">Admin</option>
                        </select>
                    </div>
                    <div class="loginBtn">
                        <button type="submit" class="btn btn-primary rounded-3" id="login">Login</button>
                    </div>
                    <% if (request.getParameter("error") != null) { %>
                    <p class="text-danger mt-2">Invalid credentials</p>
                    <% } %>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
