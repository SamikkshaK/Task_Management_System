<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="bootstrap_links.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CareerCrafter Home</title>
    <style>
        body {
            background: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)), url('images/background.webp');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #212529;
        }

        .welcome-card {
            background-color: rgba(255, 255, 255, 0.95);
            padding: 40px 30px;
            border-radius: 20px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
            margin-top: 70px;
        }

        h1 {
            font-weight: 700;
            font-size: 2.8rem;
            color: #111;
        }

        p.lead {
            font-size: 1.25rem;
            color: #444;
        }

        .career-img {
            border-radius: 15px;
            border: 3px solid #222;
            max-width: 90%;
            height: auto;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.25);
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="home.jsp">CareerCrafter</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="register.jsp">Register</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Main Content -->
<div class="container d-flex justify-content-center">
    <div class="welcome-card text-center col-md-10">
        <h1 class="text-primary">Welcome to Career Crafter</h1>
        <p class="lead">Connecting job seekers and employers on one platform.</p>
        <a href="register.jsp" class="btn btn-success me-3">Get Started</a>
    </div>
</div>

</body>
</html>
