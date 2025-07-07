<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="bootstrap_links.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Apply for Job - CareerCrafter</title>
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg mx-auto" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="text-center text-primary">Apply for Job</h3>
            <form action="applyJob" method="post">
                <div class="mb-3">
                    <label class="form-label">Job ID</label>
                    <input type="text" name="jobId" class="form-control" required />
                </div>
                <div class="mb-3">
                    <label class="form-label">Seeker ID</label>
                    <input type="text" name="seekerId" class="form-control" required />
                </div>
                <button type="submit" class="btn btn-success w-100">Apply</button>
                
            </form>
        </div>
    </div>
</div>

</body>
</html>
