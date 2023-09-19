<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="general.css">
<link rel="stylesheet" href="inboxstyle.css">
<title>Your diaries</title>
<style>
.card * {
	font-size: .9rem;
}
</style>
</head>
<body>


	<%
		response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		if(session.getAttribute("logged_in") == null)
		{
			response.sendRedirect("index.html");
		}
	%>
	
	<div class="container-fluid px-0">

		<!-- ---------------Navigation bar-------------- -->

		<jsp:include page="navbar.jsp" flush="true"/>

		<!-- ----------End of navigation-------------------- -->

		<!-- ----------Form for new diary -->

		<div
			class="container vh-100 d-flex align-items-center justify-content-center">

			<div class="card py-2 shadow" style="width: 370px; max-width: 95%;">
				<span class="fs-5 fw-bold d-block text-center text-primary mb-1">user
					information</span>
				<div class="card-body p-1">
					<div class="row row-cols-2 gy-2">
						<div class="col-5 user-heading fw-bold">Usename</div>
						<div class="col-7 user-data text-secondary">${sessionScope.user.getUsername()}</div>
						<div class="col-5 user-heading fw-bold">Email id</div>
						<div class="col-7 user-data text-secondary">${sessionScope.user.getEmailId()}</div>
						<div class="col-5 user-heading fw-bold mb-3">Password</div>
						<div class="col-7 user-data text-secondary">*******</div>
						<div class="col-12">
							<a href="edituserinfopage"
								class="btn btn-primary btn-sm d-block mx-auto">Edit info</a>
						</div>
						<div class="col-12">
							<button type="button" class="btn btn-danger btn-sm d-block w-100" data-bs-toggle="modal" data-bs-target="#deleteModal">
								Delete Account
							  </button>   
							  
							  <!-- ---------code for modal popup---------- -->

							  <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModal" aria-hidden="true">
								<div class="modal-dialog">
								  <div class="modal-content">
									<div class="modal-header">
									  <h1 class="modal-title fs-5 text-primary" id="deleteModal">Delete Account</h1>
									  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body text-black">
									  Are you sure you want to delete the Account?
									</div>
									<div class="modal-footer">
									  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
									  <a href="deleteuser" class="btn btn-danger">Delete Account</a>
									</div>
								  </div>
								</div>
							  </div>

							  <!-- --------End for code----------- -->
							  
					</div>
					</div>
				</div>
			</div>

		</div>

	</div>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>