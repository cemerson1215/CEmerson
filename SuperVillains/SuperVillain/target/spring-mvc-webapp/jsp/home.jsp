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

            <h1 id="mainHeading" class="text-center">Villain Sighting Home Page</h1>
            <hr>
        </div>
        <div class="container">           
            <div class="col-md-offset-3">
                <ul class="nav">
                    <li>
                        <a href="#">Power</a>
                        <div class="nav-column">
                            <h3>Power</h3>
                            <ul>
                                <li> <a href="${pageContext.request.contextPath}/createpower" id="createPowerButton" class="btn btn-default">New Power!</a></li>
                                <li><a>Edit a Power</a></li>
                                <li><a>Delete a Power</a></li>
                            </ul>
                        </div>                       
                    </li>
                    <li>
                        <a href="#">Org</a>                        
                        <div class="nav-column">
                            <h3>Organization</h3>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/createorg" id="createPowerButton" class="btn btn-default">New Org!</a></li>
                                <li><a>Edit an Org</a></li>
                                <li><a>Delete an Org</a></li>
                            </ul>
                        </div>                        
                    </li>
                    <li>
                        <a href="#">Villain</a>
                        <div class="nav-column">
                            <h3>Villain</h3>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/createvillain" id="createVillainButton" class="btn btn-default">New Villain!</a></li>
                                <li><a>Edit a Villain</a></li>
                                <li><a>Delete a Villain</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#">Location</a>
                        <div class="nav-column">
                            <h3>Location</h3>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/createloc" id="createLocButton" class="btn btn-default">New Location!</a></li>
                                <li><a>Edit a Location</a></li>
                                <li><a>Delete a Location</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <a href="#">Sighting</a>
                        <div class="nav-column">
                            <h3>Sighting</h3>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/createsighting" id="createSightingButton" class="btn btn-default">New Sighting!</a></li>
                                <li><a>Edit a Sighting</a></li>
                                <li><a>Delete a Sighting</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
            <br/>
            <div class="col-md-offset-2 col-md-3">
                <!--                <svg xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none" 
                                     viewBox="0 10 132 136">
                                <path d="M125.2,2.8C57.6,2.8,2.8,42.1,2.8,84.6c0,33.3,33.9,61.6,81.1,72.2c2.8,27.2-2.5,71.7-16.1,88.4c20.5-23,42.4-61.8,50.4-84 c2.3,0,4.7,0.2,7,0.2c67.6,0,122.3-34.4,122.3-76.8S192.8,2.8,125.2,2.8z" />
                                </svg>-->
                <!--                <blockquote class="bubble">
                                    <p>Did someone say <em>chimichangas?</em>
                                </blockquote>-->
            </div>
            <div id="SightingsTableDiv">
                <hr>
                <ul class="list-group" id="errorMessages"></ul>
                <table id="sightTable" class="table table-hover table-striped" frame="box" style="text-align: center">
                    <tr style="background-color: darkGrey">
                        <th width="20%" style="text-align: center">Date</th>
                        <th width="25%" style="text-align: center">Location</th>
                        <th width="20%" style="text-align: center">Villain List</th>
                        <th width="10%" style="text-align: center"></th>
                        <th width="25%" style="text-align: center"></th>
                    </tr>
                    <c:forEach var="currentSight" items="${sightList}">
                        <tr>
                            <td>
                                <c:out value="${currentSight.timeOfSight}"/> 
                                </a>
                            </td>
                            <td>
                                <c:out value="${currentSight.location.name}"/> <br>
                                Descrp:  <c:out value="${currentSight.location.description}"/>
                            </td>
                            <td>
                                <c:forEach var="currentVillain" items="${currentSight.villainsList}">
                                    <c:out value="${currentVillain.name}"/> <br>
                                </c:forEach>
                            </td>
                            <td>

                            </td>
                            <td>

                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

