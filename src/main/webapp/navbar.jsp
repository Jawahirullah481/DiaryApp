<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Navbar</title>
</head>
<body>
<nav class="navbar navbar-expand py-0" id="navbar-top">
			<div class="container-fluid px-0 px-sm-3 px-lg-5 py-0 py-lg-1">
				<a class="navbar-brand" href="index.html"> <img
					src="https://mir-s3-cdn-cf.behance.net/projects/202/4bda1d52157575.Y3JvcCwxMDAyLDc4NCwwLDk.png"
					alt=""> Diary App
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item"><a class="nav-link" href="logout">Log
								out</a></li>
						<li class="nav-item"><a class="nav-link" href="userinfo"
							id="user-initial"> ${sessionScope.userInitial} </a></li>
					</ul>
				</div>
			</div>
		</nav>
</body>
</html>