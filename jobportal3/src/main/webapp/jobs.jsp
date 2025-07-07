<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="bootstrap_links.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Jobs - CareerCrafter</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
    <style>
        body {
            background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('<c:url value="/images/background.webp"/>');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
        }

        .job-container {
            background-color: rgba(255, 255, 255, 0.97);
            padding: 40px;
            margin: 80px auto;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            max-width: 1100px;
        }
        .table-title{
        background-color: blue;
        }

        h3 {
            font-weight: 700;
            color: #0d6efd;
            text-align: center;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead {
            background-color: #003366;
            color: white;
        }

         thead th {
            padding: 14px;
            text-align: center;
            font-size: 1rem;
            background-color:black!important;
            color:white!important;
        }

        tbody tr {
            background-color: #f4f7fa;
            transition: background 0.3s ease;
        }

        tbody tr:nth-child(even) {
            background-color: #e9eff5;
        }

        tbody tr:hover {
            background-color: #dbe7f2;
        }

        td {
            text-align: center;
            padding: 12px;
            vertical-align: middle;
        }

        .btn-apply {
            padding: 5px 12px;
            font-size: 0.9rem;
        }

        .alert {
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
                <li class="nav-item"><a class="nav-link active" href="/user/jobs">Browse Jobs</a></li>
                <li class="nav-item"><a class="nav-link" href="/user/profile">Manage Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="/user/upload_resume">Upload Resume</a></li>
                <li class="nav-item"><a class="nav-link" href="/user/my_applications">My Applications</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Job Listing Section -->
<div class="container job-container">
    <h3>Available Jobs</h3>

    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>

    <table class="table border shadow-sm">
        <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Location</th>
                <th>Employer</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="job" items="${jobs}">
                <tr>
                    <td>${job.title}</td>
                    <td>${job.description}</td>
                    <td>${job.location}</td>
                    <td>${job.employerEmail}</td>
                    <td>
                        <form action="/user/apply" method="post">
                            <input type="hidden" name="jobId" value="${job.id}" />
                            <button type="submit" class="btn btn-primary btn-sm btn-apply">Apply</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
