<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Complaint Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
       nav{
           background: linear-gradient(to right,#4F959D, #648DB3);
           z-index: 1;
       }
       #logo{
           border-radius: 50%;
       }
       body{
           background: linear-gradient(to right,#123458, #006A71);
       }
       #main{
           border: 3px solid #F9CB43;
           background-color: #0A5EB0;
           width: 500px;
           height: 500px;
           box-shadow: #292828 10px 10px 2px;
           border-radius: 1rem;
           opacity: 0.7;

           display: flex;
           flex-direction: column;
           align-items: center;
           justify-content: flex-start;
           padding: 10px;
       }
       #loginBtn{
           display: flex;
           justify-content: center;
           margin-top: 50px;
       }
       #loginBtn {
           background-color: #788cee;
           border: none;
           color: white;
           transition: 0.3s ease;
       }
       #loginBtn:hover {
           background-color: #10c8d9;
           color: white;

       }
       #main img{
           width: 100%;
           height: 100%;
           object-fit: cover;
           border-radius: 1rem;
       }
    </style>
</head>
<body>
<nav class="navbar">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="assests/cmsLogo.png" id="logo" alt="Logo" width="50" height="44" class="d-inline-block align-text-top">
              Complaint Management System
        </a>
    </div>
</nav>
<main>
    <div class="container text-white p-5">
        <div class="row justify-content-center">
            <div class="col-md-6" id="main">
                <img src="assests/cms.png" alt="">
                <h1 style="text-align: center">Complaint Management System</h1>
                <a href="login.jsp" class="btn btn-secondary mt-3" id="loginBtn">Login</a>
            </div>
        </div>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>

</body>
</html>
