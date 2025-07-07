<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="bootstrap_links.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Applications Received - CareerCrafter</title>
    <meta charset="UTF-8">
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
            color: #212529;
        }

        .applications-container {
            background-color: rgba(255, 255, 255, 0.95);
            padding: 40px;
            border-radius: 20px;
            max-width: 1000px;
            margin: 100px auto;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
        }

        h3 {
            text-align: center;
            color: #0d6efd;
            font-weight: 700;
            margin-bottom: 30px;
        }

        table thead {
            background-color: #1f3c88;
            color: white;
        }

        table thead th {
            border: none;
            font-size: 1rem;
            text-align: center;
            background-color:black!important;
            color:white!important;
        }

        table tbody tr {
            background-color: #ffffff;
        }

        table tbody tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        table td {
            text-align: center;
            vertical-align: middle;
        }

        .alert {
            text-align: center;
            margin-top: 30px;
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
                <li class="nav-item"><a class="nav-link" href="/employer/post-job">Post Job</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/jobs">Manage Jobs</a></li>
                <li class="nav-item"><a class="nav-link active" href="/employer/applications">View Applications</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/update-company">Update Company</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Application Table -->
<div class="container applications-container">
    <h3>Applications</h3>

    <c:if test="${not empty applications}">
        <table class="table table-hover border shadow-sm">
            <thead>
                <tr>
                    <th>Job Title</th>
                    <th>Applicant Email</th>
                    <th>Date Applied</th>
                    <th>Resume</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="app" items="${applications}">
                    <tr>
                        <td>${app.job.title}</td>
                        <td>${app.seekerEmail}</td>
                        <td>${app.appliedDate}</td>
                        <td>
    <c:choose>
        <c:when test="${resumeMap[app.seekerEmail] != null}">
            <a href="${resumeMap[app.seekerEmail]}" target="_blank">Download</a>
        </c:when>
        <c:otherwise>No Resume</c:otherwise>
    </c:choose>
</td>
                        
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty applications}">
        <div class="alert alert-info">No applications yet.</div>
    </c:if>
</div>

</body>
</html>
