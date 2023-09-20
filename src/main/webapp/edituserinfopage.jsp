<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
#edituserinfo {
	width: 90vw;
	max-width: 350px;
}

#edituserinfo .form-control, #edituserinfo a {
	font-size: .7rem;
}

#edituserinfo #hint, #edituserinfo #error-msg {
	font-size: .75rem;
	color: red;
}
</style>
</head>

<body>

	<div class="container-fluid px-0">

		<!-- ---------------Navigation bar-------------- -->

		<jsp:include page="navbar.jsp" flush="true" />

		<!-- ----------End of navigation-------------------- -->

		<!-- ----------Form for new diary -->

		<div
			class="container vh-100 d-flex align-items-center justify-content-center">

			<form action="edituser" method="POST" id="edituserinfo"
				class="form shadow p-3 rounded">
				<span class="fs-6 d-block text-center mb-2 text-primary">Edit
					user info</span>
				<c:if test="${sessionScope.invalidPassword}">
					<p id="error-msg" class="text-center">You entered wrong
						password !</p>
				</c:if>
				<p id="hint" class="text-center d-none">Enter similar new
					password</p>
				<input type="text" class="form-control mb-3" name="username"
					id="username" placeholder="Enter username"
					value="${sessionScope.user.getUsername()}" required> <input
					type="email" class="form-control mb-3" name="email" id="email"
					placeholder="Enter email id"
					value="${sessionScope.user.getEmailId()}" required> <input
					type="password" class="form-control mb-3" name="oldpassword"
					id="oldpassword" placeholder="Enter Old Password" required>

				<!-- -----------Change password----------- -->

				<a href="#" id="link-change-password"
					class="link-primary text-center d-block mb-3"
					onclick="showChangePassword()">Want to change Password ?</a>
				<div id="change-password" class="d-none">
					<input type="password" class="form-control mb-3" name="newpassword"
						id="newpassword" placeholder="Enter New Password"> <input
						type="password" class="form-control mb-3" id="reenteredpassword"
						placeholder="Re-Enter New Password"> <a href="#"
						id="keep-old-password"
						class="link-primary text-center d-block mb-3"
						onclick="keepOldPassword()">keep Old Password</a>
				</div>

				<!-- ---------------------------------- -->

				<input type="submit" value="Edit"
					class="btn btn-sm btn-primary d-block mx-auto px-3"
					onclick="validateForm(event);">
			</form>

		</div>

	</div>
	<script src="js/bootstrap.min.js"></script>
	<script>
		function showChangePassword() {
			document.getElementById("link-change-password").classList
					.add("d-none");
			document.getElementById("change-password").classList
					.remove("d-none");
		}
		function keepOldPassword() {
			document.getElementById("link-change-password").classList
					.remove("d-none");
			document.getElementById("change-password").classList.add("d-none");
		}

		function validateForm(event) {
			let hint = document.getElementById("hint").classList.add("d-none");
			let changePasswordContainer = document
					.getElementById("change-password");

			if (!changePasswordContainer.classList.contains("d-none")) {

				let newPassword = document.getElementById("newpassword").value
						.trim();
				let reenteredpassword = document
						.getElementById("reenteredpassword").value.trim();

				if (newPassword === "" || reenteredpassword === "") {
					hint = document.getElementById("hint");
					hint.innerText = "Please fill out new password !"
					hint.classList.remove("d-none");
					event.preventDefault();
					return false;
				}
				if (newPassword !== reenteredpassword) {
					hint = document.getElementById("hint");
					hint.innerText = "Please Enter same new password !"
					hint.classList.remove("d-none");
					event.preventDefault();
					return false;
				}

			} else {
				document.getElementById("newpassword").value = "";
				document.getElementById("newpassword").value = "";
			}

			return true;

		}
	</script>
</body>

</html>