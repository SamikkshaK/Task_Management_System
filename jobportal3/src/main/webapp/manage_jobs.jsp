<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="bootstrap_links.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Posted Jobs - CareerCrafter</title>
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

        .jobs-container {
            max-width: 1000px;
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            font-size: 1rem;
        }

        thead {
            background-color: #1f3c88;
            color: white;
        }

        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #dee2e6;
        }

        tbody tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        .btn-sm {
            padding: 5px 12px;
            font-size: 0.85rem;
            border-radius: 20px;
        }

        .btn-edit {
            background-color: #0d6efd;
            color: white;
        }

        .btn-edit:hover {
            background-color: #0a58ca;
        }

        .btn-delete {
            background-color: #dc3545;
            color: white;
        }

        .btn-delete:hover {
            background-color: #bb2d3b;
        }

        .alert {
            text-align: center;
            margin-top: 20px;
            color: #555;
        }

        .btn-back {
            display: block;
            width: fit-content;
            margin: 30px auto 0;
            background: linear-gradient(to right, #007bff, #0d6efd);
            color: white;
            padding: 10px 25px;
            border: none;
            border-radius: 30px;
            font-weight: 600;
            text-decoration: none;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        .btn-back:hover {
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
                <li class="nav-item"><a class="nav-link" href="/employer/post-job">Post Job</a></li>
                <li class="nav-item"><a class="nav-link active" href="/employer/jobs">Manage Jobs</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/applications">View Applications</a></li>
                <li class="nav-item"><a class="nav-link" href="/employer/update-company">Update Company</a></li>
                <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- Jobs Table -->
<div class="container jobs-container">
    <h2><i class="fas fa-briefcase"></i> Jobs You've Posted</h2>

    <c:if test="${empty jobs}">
        <div class="alert">No jobs posted yet.</div>
    </c:if>

    <c:if test="${not empty jobs}">
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Location</th>
                    <th>Salary</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="job" items="${jobs}">
                    <tr>
                        <td>${job.title}</td>
                        <td>${job.description}</td>
                        <td>${job.location}</td>
                        <td>${job.salary}</td>
                        <td>
                            <form action="/employer/edit-job" method="get" style="display:inline;">
							    <input type="hidden" name="jobId" value="${job.id}" />
							    <button type="submit" class="btn btn-primary btn-sm">
							        <i class="fas fa-edit"></i> Edit
							    </button>
							</form>
							                            
                            <form action="/employer/delete-job" method="post" style="display:inline;">
                                <input type="hidden" name="jobId" value="${job.id}" />
                                <button type="submit" class="btn btn-sm btn-delete">
                                    <i class="fas fa-trash-alt"></i> Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <a href="/employer/home" class="btn-back"><i class="fas fa-arrow-left"></i> Back to Dashboard</a>
</div>

</body>
</html>
