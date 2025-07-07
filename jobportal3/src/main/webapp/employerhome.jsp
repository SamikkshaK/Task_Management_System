<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="bootstrap_links.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employer Dashboard - CareerCrafter</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
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

        .dashboard-box {
            max-width: 650px;
            margin: 80px auto;
            background: rgba(255, 255, 255, 0.95);
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        h2 {
            color: #0d6efd;
            font-weight: 700;
            margin-bottom: 10px;
        }

        p {
            font-size: 1.1rem;
            color: #444;
            margin-bottom: 30px;
        }

        .btn-custom {
            display: block;
            margin: 12px auto;
            padding: 12px 30px;
            width: 80%;
            border-radius: 30px;
            font-size: 1.1rem;
            font-weight: 600;
            color: white;
            text-decoration: none;
            transition: background 0.3s ease-in-out, transform 0.2s ease;
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
            outline: none;
            border: none;
        }

        .btn-post {
            background: linear-gradient(to right, #007bff, #0056b3);
        }

        .btn-post:hover {
            background: linear-gradient(to right, #0056b3, #004494);
        }

        .btn-manage {
            background: linear-gradient(to right, #339af0, #1c7ed6);
        }

        .btn-manage:hover {
            background: linear-gradient(to right, #1c7ed6, #1864ab);
        }

        .btn-applications {
            background: linear-gradient(to right, #4dabf7, #339af0);
        }

        .btn-applications:hover {
            background: linear-gradient(to right, #339af0, #1c7ed6);
        }

        .btn-company {
            background: linear-gradient(to right, #228be6, #1c7ed6);
        }

        .btn-company:hover {
            background: linear-gradient(to right, #1c7ed6, #1864ab);
        }

        .btn-custom:hover {
            transform: translateY(-2px);
        }

        .btn-custom:focus {
            outline: none;
            box-shadow: none;
        }

        i {
            margin-right: 8px;
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
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Dashboard Card -->
<div class="dashboard-box">
    <h2>Welcome, ${user.fullName}!</h2>
    <p>You are logged in as <strong>Employer</strong>. Manage your jobs and applications below.</p>

    <a href="/employer/post-job" class="btn-custom btn-post">
        <i class="fas fa-plus-circle"></i> Post Job
    </a>
    <a href="/employer/jobs" class="btn-custom btn-manage">
        <i class="fas fa-tasks"></i> Manage Jobs
    </a>
    <a href="/employer/applications" class="btn-custom btn-applications">
        <i class="fas fa-users"></i> View Applications
    </a>
    <a href="/employer/update-company" class="btn-custom btn-company">
        <i class="fas fa-building"></i> Update Company
    </a>
</div>

</body>
</html>
