<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../bootstrap_links.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Company - CareerCrafter</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
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

        .form-container {
            max-width: 600px;
            margin: 100px auto;
            padding: 40px;
            background: rgba(255, 255, 255, 0.95);
            border-radius: 20px;
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
            margin-bottom: 5px;
            display: block;
        }

        .form-control {
            width: 100%;
            padding: 10px 12px;
            font-size: 1rem;
            border: 1px solid #ced4da;
            border-radius: 5px;
            margin-bottom: 20px;
            box-sizing: border-box;
            transition: border-color 0.3s ease;
        }
        .form-control:focus {
            border-color: #0d6efd;
            outline: none;
            box-shadow: 0 0 5px rgba(13,110,253,0.5);
        }

        .btn-submit {
            display: block;
            width: 100%;
            padding: 12px 0;
            background: linear-gradient(to right, #007bff, #0d6efd);
            color: white;
            border: none;
            border-radius: 30px;
            font-weight: 600;
            font-size: 1.1rem;
            cursor: pointer;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            transition: background 0.3s ease, transform 0.2s ease;
        }
        .btn-submit:hover {
            background: linear-gradient(to right, #0a66c2, #0958b0);
            transform: translateY(-2px);
        }

        /* Navbar styles same as posted_jobs.jsp */
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
                <li class="nav-item"><a class="nav-link" href="/employer/jobs">Manage Jobs</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/post-job">Post Job</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/applications">View Applications</a></li>
                <li class="nav-item"><a class="nav-link active" href="/employer/update-company">Update Company</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Form Container -->
<div class="container form-container">
    <h2><i class="fas fa-building"></i> Update Company Details</h2>

    <form:form action="/employer/update-company" modelAttribute="employer" method="post">

        <!-- Hidden Employer Fields -->
        <form:hidden path="id" />
        <form:hidden path="email" />
        <form:hidden path="password" />
        <form:hidden path="role" />

        <!-- Designation -->
        <label for="designation">Designation</label>
        <form:input path="designation" id="designation" cssClass="form-control" placeholder="Enter Designation" />

        <!-- Phone -->
        <label for="phone">Phone</label>
        <form:input path="phone" id="phone" cssClass="form-control" placeholder="Enter Phone Number" />

        <!-- Company Name -->
        <label for="companyName">Company Name</label>
        <form:input path="company.companyName" id="companyName" cssClass="form-control" placeholder="Enter Company Name" />

        <!-- Industry -->
        <label for="industry">Industry</label>
        <form:input path="company.industry" id="industry" cssClass="form-control" placeholder="Enter Industry" />

        <!-- Location -->
        <label for="location">Location</label>
        <form:input path="company.location" id="location" cssClass="form-control" placeholder="Enter Location" />

        <!-- Submit Button -->
        <button type="submit" class="btn-submit">Update</button>

    </form:form>
</div>

</body>
</html>
