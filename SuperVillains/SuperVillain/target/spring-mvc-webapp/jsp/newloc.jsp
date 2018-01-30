<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">       
        <link href="${pageContext.request.contextPath}/css/homepage.css" rel="stylesheet">  
    </head>
    <body>
        <div class="container" id="mainHeaderDiv">

            <h1 id="mainHeading" class="text-center">Add a new Location</h1>
            <hr>
        </div>
        <div class="container">           
            <div class="col-md-offset-2 col-md-2">
                <a href="${pageContext.request.contextPath}/" id="createDvdButton" class="btn btn-default">Home Page</a>
            </div>
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/createpower" id="createPowerButton" class="btn btn-default">New Power!</a>
            </div>
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/createorg" id="createOrgButton" class="btn btn-default">New Org!</a>
            </div>
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/createvillain" id="createVillainButton" class="btn btn-default">New Villain!</a>
            </div>
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/createsighting" id="createSightingButton" class="btn btn-default">New Sighting!</a>
            </div>
            <!--            <div class="col-md-offset-2 col-md-3">
                <svg xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none" 
                     viewBox="0 10 132 136">
                <path d="M125.2,2.8C57.6,2.8,2.8,42.1,2.8,84.6c0,33.3,33.9,61.6,81.1,72.2c2.8,27.2-2.5,71.7-16.1,88.4c20.5-23,42.4-61.8,50.4-84 c2.3,0,4.7,0.2,7,0.2c67.6,0,122.3-34.4,122.3-76.8S192.8,2.8,125.2,2.8z" />
                </svg>
                <blockquote class="bubble">
                    <p>Did someone say <em>chimichangas?</em>
                </blockquote>
            </div>-->
            <br/>
            <div id="NewVillainDiv"> 
                <hr>

            </div>





        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

