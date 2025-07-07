<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../bootstrap_links.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Upload Resume - CareerCrafter</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
    <style>
        body {
            background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('<c:url value="/images/background.webp"/>');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #212529;
            margin: 0;
            padding: 0;
        }

        .upload-card {
            background-color: rgba(255, 255, 255, 0.95);
            padding: 40px;
            max-width: 600px;
            margin: 100px auto;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
        }

        .upload-card h2 {
            color: #0d6efd;
            font-weight: 700;
            text-align: center;
            margin-bottom: 30px;
        }

        label {
            font-weight: 600;
            margin-bottom: 6px;
        }

        .form-control {
            padding: 12px 15px;
            border-radius: 10px;
            border: 1px solid #ced4da;
        }

        .form-control:focus {
            border-color: #0d6efd;
            box-shadow: none;
        }

        .btn-submit {
            width: 100%;
            padding: 12px;
            border-radius: 30px;
            font-weight: 600;
            background: linear-gradient(to right, #007bff, #0d6efd);
            color: white;
            border: none;
            transition: background 0.3s ease-in-out;
        }

        .btn-submit:hover {
            background: linear-gradient(to right, #0056b3, #084ec1);
        }

        .alert {
            margin-top: 20px;
            text-align: center;
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
                <li class="nav-item"><a class="nav-link" href="/user/jobs">Browse Jobs</a></li>
                <li class="nav-item"><a class="nav-link" href="/user/profile">Manage Profile</a></li>
                <li class="nav-item"><a class="nav-link active" href="/user/upload_resume">Upload Resume</a></li>
                <li class="nav-item"><a class="nav-link" href="/user/my_applications">My Applications</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Upload Resume Form -->
<div class="container">
    <div class="upload-card">
        <h2>Upload Your Resume</h2>
        <form action="/user/upload-resume" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label>Select Resume (PDF, DOC, etc.)</label>
                <input type="file" name="file" class="form-control" required />
            </div>
            <div class="mb-3">
                <label>Description</label>
                <input type="text" name="description" class="form-control" placeholder="e.g. My updated resume">
            </div>
            <button type="submit" class="btn-submit">Upload</button>
        </form>

        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>
    </div>
</div>

</body>
</html>
