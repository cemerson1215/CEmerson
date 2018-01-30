<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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

            <h1 id="mainHeading" class="text-center">Add a New Villain</h1>
            <hr>
        </div>
        <div class="container">           

            <div class="col-md-offset-3">
                <ul class="nav">
                    <li>
                        <a href="#">Home</a>
                        <div class="nav-column">
                            <h3>Home Page</h3>
                            <ul>
                                <li>  <a href="${pageContext.request.contextPath}/" id="createDvdButton" class="btn btn-default">Home Lair</a></li>
                            </ul>
                        </div>                       
                    </li>
                    <li>
                        <a href="#">Power</a>                        
                        <div class="nav-column">
                            <h3>Power</h3>
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/createpower" id="createPowerButton" class="btn btn-default">New Power!</a></li>
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
            <hr>
            <div id="createVillainFormDiv">
<!--              sf form goes here -->

                <div id="currentVilTableDiv">
                    <hr>                
                    <h4>Current Villains</h4>
                    <ul class="list-group" id="errorMessages"></ul>
                    <table id="vilTable" class="table table-hover table-striped" frame="box" style="text-align: center">
                        <tr style="background-color: darkGrey">
                            <th width="8%" style="text-align: center">ID</th>
                            <th width="23%" style="text-align: center">Name</th>
                            <th width="23%" style="text-align: center">Description</th>
                            <th width="23%" style="text-align: center">Powers</th>
                            <th width="23%" style="text-align: center">Organizations</th>
                        </tr>
                        <c:forEach var="currentVillain" items="${villainList}">
                            <tr>
                                <td>
                                    <c:out value="${currentVillain.id}"/> 
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentVillain.name}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentVillain.description}"/> 
                                </td>
                                <td>
                                    <c:forEach var="currentPower" items="${currentVillain.powers}">
                                        <c:out value="${currentPower.description}"/><br>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach var="currentOrg" items="${currentVillain.orgs}">
                                        <c:out value="${currentOrg.name}"/><br>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

