<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="bootstrap_links.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile - CareerCrafter</title>
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

        .form-card {
            background-color: rgba(255, 255, 255, 0.96);
            padding: 40px;
            max-width: 600px;
            margin: 100px auto;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
        }

        .form-card h3 {
            text-align: center;
            color: #0d6efd;
            font-weight: 700;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: 600;
            display: block;
            margin-bottom: 6px;
        }

        .form-control {
            width: 100%;
            padding: 12px 15px;
            font-size: 1rem;
            border: 1px solid #ced4da;
            border-radius: 10px;
            box-shadow: none;
        }

        .form-control:focus {
            border-color: #0d6efd;
            outline: none;
        }

        .btn-submit {
            width: 100%;
            padding: 12px;
            border-radius: 30px;
            font-weight: 600;
            font-size: 1.1rem;
            background: linear-gradient(to right, #007bff, #0d6efd);
            color: #fff;
            border: none;
            transition: background 0.3s ease-in-out;
        }

        .btn-submit:hover {
            background: linear-gradient(to right, #0056b3, #084ec1);
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
                <li class="nav-item"><a class="nav-link active" href="/user/profile">Manage Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="/user/upload_resume">Upload Resume</a></li>
                <li class="nav-item"><a class="nav-link" href="/user/my_applications">My Applications</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Form Content -->
<div class="container">
    <div class="form-card">
        <h3>Update Your Profile</h3>
        <form:form method="post" action="/user/update-profile" modelAttribute="seeker">
            <form:hidden path="id"/>

            <div class="form-group">
                <label for="fullName">Full Name</label>
                <form:input path="fullName" cssClass="form-control" placeholder="John Doe" id="fullName"/>
            </div>

            <div class="form-group">
                <label for="education">Education</label>
                <form:input path="education" cssClass="form-control" placeholder="B.Tech in Computer Science" id="education"/>
            </div>

            <div class="form-group">
                <label for="experience">Experience</label>
                <form:input path="experience" cssClass="form-control" placeholder="2 years at ABC Corp" id="experience"/>
            </div>

            

            <button type="submit" class="btn-submit">Update Profile</button>
        </form:form>
    </div>
</div>

</body>
</html>
