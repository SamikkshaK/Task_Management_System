<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="bootstrap_links.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Post a Job - CareerCrafter</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <style>
        body {
            background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('<c:url value="/images/background.webp"/>');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            color: #000;
        }

        .form-container {
            background: rgba(255, 255, 255, 0.95);
            max-width: 700px;
            margin: 100px auto;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
            color: #000;
        }

        h2 {
            text-align: center;
            color: #0d6efd;
            font-weight: 700;
            margin-bottom: 30px;
        }

        label {
            font-weight: 600;
            color: #000;
        }

        .form-control {
            border-radius: 10px;
            padding: 10px 15px;
            color: #000;
        }

        .btn-primary {
            background: linear-gradient(to right, #007bff, #0d6efd);
            border: none;
            font-weight: 600;
            padding: 10px 25px;
            border-radius: 30px;
            width: 100%;
            transition: 0.3s ease;
        }

        .btn-primary:hover {
            background: linear-gradient(to right, #0a66c2, #0958b0);
            transform: translateY(-2px);
        }

        .navbar .nav-link.active {
            font-weight: bold;
            color: #fff !important;
            background-color: rgba(255, 255, 255, 0.2);
            border-radius: 5px;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="#">CareerCrafter</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link active" href="/employer/post-job">Post Job</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/jobs">Manage Jobs</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/applications">View Applications</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/update-company">Update Company</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Post Job Form -->
<div class="container form-container">
    <h2><i class="fas fa-plus-circle"></i> Post a New Job</h2>
    <form action="/employer/post-job" method="post">
    <div class="mb-3">
        <label>Job Title</label>
        <input type="text" name="title" class="form-control" required>
    </div>
    
    <div class="mb-3">
        <label>Location</label>
        <input type="text" name="location" class="form-control" required>
    </div>

    <div class="mb-3">
        <label>Salary (INR)</label>
        <input type="text" name="salary" class="form-control" required>
    </div>

    <div class="mb-3">
        <label>Description</label>
        <textarea name="description" class="form-control" rows="4" required></textarea>
    </div>
    <c:if test="${not empty user and user.role == 'EMPLOYER'}">
    <div class="mb-3">
        <label>Company</label>
        <input type="text" class="form-control" value="${user.company.companyName}"  />
    </div>
</c:if>

    <button type="submit" class="btn btn-primary">
        <i class="fas fa-paper-plane"></i> Post Job
    </button>
</form>
    
    
    
</div>

</body>
</html>
