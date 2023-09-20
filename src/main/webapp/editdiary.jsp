<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="general.css">
    <link rel="stylesheet" href="inboxstyle.css">
    <title>Edit diary</title>
</head>
<body>
	
	<div class="container-fluid px-0">

		<!-- ---------------Navigation bar-------------- -->

		<jsp:include page="navbar.jsp" flush="true"/>
		
		<!-- ----------End of navigation-------------------- -->


		<!-- ----------Form for new diary -->

		<div
			class="container vh-100 d-flex align-items-center justify-content-center">

			<form action="editdiaryservlet" method="POST"
				class="form rounded row gy-2 gx-1 px-1 py-2 shadow border border-2 border-primary-subtle">
				
				<div class="form-group col-12">
					<input type="text" name="heading" id="heading"
						class="form-control border" placeholder="Enter heading"
						value="${param.noteheading}">
				</div>
				
				<div class="form-group col-12">
					<textarea name="content" id="content" class="form-control" rows="5"
						placeholder="Enter content here..">${param.notecontent}</textarea>
				</div>
								
				<div class="col-3">
				
				<!--  This field will not shown to the user. -->
					<input type="hidden" name="inboxid" value="${param.inboxid}" />
				</div>
				
				<div class="col-3">
				
					<input type="submit" value="Edit"
						class="btn btn-primary btn-sm d-block w-100 ms-auto">
				</div>
				<div class="col-3">
					<input type="submit" value="Delete" formaction="deletediary"
						class="btn btn-secondary btn-sm d-block w-100 me-auto">
				</div>
				<div class="col-3"></div>
			</form>

		</div>

	</div>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>