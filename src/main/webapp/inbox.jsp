<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
   <!DOCTYPE html>
<%@taglib prefix="diary" uri="/WEB-INF/myTags.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="general.css">
    <link rel="stylesheet" href="inboxstyle.css">
    <title>Your diaries</title>
</head>

<body>
	
    <div class="container-fluid px-0">

        <!-- ---------------Navigation bar-------------- -->
        
        <jsp:include page="navbar.jsp" flush="true"/>

        <!-- ----------End of navigation-------------------- -->

        <!-- ----------------Overview--------------- -->

        <div id="inbox-overview">
            <div class="rounded-5 py-2 ps-3 pe-2 bg-primary overview-pill">
                No of diaries
                <span class="bg-light text-dark inside-data">${sessionScope.user.inbox.size()}</span>
            </div>
            <div class="rounded-5 py-2 ps-3 pe-2 bg-primary overview-pill">
                Last edited
                <span class="bg-light text-dark inside-data">${sessionScope.user.getEditedDateinFormat()}</span>
            </div>
            <a id="new-diary" class="rounded-5 py-2 ps-2 pe-2 bg-primary overview-pill text-decoration-none" href="newdiarypage">
                <span class="plus">+</span>
                New Diary
            </a>
        </div>

        <!-- ------------------End of overview--------------------- -->
        
        <!--  ----------------- Inbox ----------------------------- -->

        <div class="container inbox-container">
        <div id="inbox-holder">
         
         
        <!--  Adding dynamic contents using custom tag --> 
        <c:if test="${sessionScope.logged_in}">
        <diary:addDiaries></diary:addDiaries>
        </c:if>
            
        </div>
    </div>
    
    <!-- -----------------End of Inbox--------------------- -->
        
  <!-- -------------------Footer-------------------- -->

  <section class="footer">
    <div class="row d-flex align-items-center align-items-md-start justify-content-center gap-5">
        <div class="col-12 col-lg-4">
            <div class="card">
                <h1>Contact us</h1>
                <a href="">
                    <p>+91 9090232325</p>
                </a>
                <a href="">
                    <p>diaryapp@gmail.com</p>
                </a>
            </div>
        </div>
        <div class="col-12 col-lg-4">
            <div class="card">
                <h1>Report</h1>
                <a href="">
                    <p>Click here to report</p>
                </a>
            </div>
        </div>
        <div class="col-12 col-lg-4">
            <div class="card">
                <h1>Our Services</h1>
                <a href="">
                    <p>Diary app</p>
                </a>
                <a href="">
                    <p>Web development</p>
                </a>
                <a href="">
                    <p>Medicine</p>
                </a>
            </div>
        </div>
    </div>
</section>

<!-- ------------End of footer----------------- -->
    </div>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>