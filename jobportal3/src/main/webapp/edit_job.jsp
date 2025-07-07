<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="bootstrap_links.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Job - CareerCrafter</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
    <style>
        body {
            background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('<c:url value="/images/background.webp"/>');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #000;
            margin: 0;
            padding: 0;
        }

        .edit-job-container {
            max-width: 700px;
            margin: 100px auto;
            background: rgba(255, 255, 255, 0.95);
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
        }

        h2 {
            text-align: center;
            color: #0d6efd;
            font-weight: bold;
            margin-bottom: 30px;
        }

        label {
            font-weight: 600;
        }

        .form-control {
            border-radius: 8px;
        }

        .btn-submit {
            background: linear-gradient(to right, #007bff, #0d6efd);
            color: white;
            border-radius: 30px;
            padding: 10px 30px;
            font-weight: bold;
            transition: background 0.3s ease-in-out;
        }

        .btn-submit:hover {
            background: linear-gradient(to right, #0958b0, #0a66c2);
        }

        .btn-back {
            display: inline-block;
            margin-top: 20px;
            background-color: #6c757d;
            color: white;
            padding: 8px 20px;
            border-radius: 30px;
            text-decoration: none;
        }

        .btn-back:hover {
            background-color: #5a6268;
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
                <li class="nav-item"><a class="nav-link" href="/employer/jobs">Manage Jobs</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/post-job">Post Job</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/applications">View Applications</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/update-company">Update Company</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Edit Job Form -->
<div class="container edit-job-container">
    <h2><i class="fas fa-edit"></i> Edit Job</h2>

    <form action="/employer/update-job" method="post">
    <input type="hidden" name="id" value="${job.id}" />

    <div class="mb-3">
        <label>Title</label>
        <input type="text" name="title" class="form-control" value="${job.title}" required />
    </div>

    <div class="mb-3">
        <label>Company</label>
        <input type="text" name="company.companyName" class="form-control" value="${job.company.companyName}" required />
    </div>

    <div class="mb-3">
        <label>Location</label>
        <input type="text" name="location" class="form-control" value="${job.location}" required />
    </div>

    <div class="mb-3">
        <label>Salary</label>
        <input type="number" step="0.01" name="salary" class="form-control" value="${job.salary}" required />
    </div>

    <div class="mb-3">
        <label>Description</label>
        <textarea name="description" class="form-control" required>${job.description}</textarea>
    </div>

    <button type="submit" class="btn btn-success">Update Job</button>
</form>
    

    <div class="text-center">
        <a href="/employer/jobs" class="btn-back"><i class="fas fa-arrow-left"></i> Back</a>
    </div>
</div>

</body>
</html>
