<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Heath's Baseball Cards</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">       
        <link href="${pageContext.request.contextPath}/css/homepage.css" rel="stylesheet">  

    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Heath's Baseball Cards</h1>            
        </div>
        <div class="container">
            <a href="${pageContext.request.contextPath}/manufactInfo">Manufacturer</a>
            <hr>
            <div id="createCard">
                <sf:form class="form-horizontal" role="form" modelAttribute="card" action="createCard" method="POST">
                    <div class="form-group">
                        <label for="create-cardNum" class="col-md-2 control-label">Card Number: </label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" path="cardNumber" placeholder="Card Number"/>
                        </div>
                    </div> 
                    <div class="form-group">
                        <label for="create-card-name" class="col-md-2 control-label">Player's Name: </label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" path="playerName" placeholder="Player's Name"/>
                        </div>
                    </div> 
                    <div class="form-group">
                        <label for="create-card-year" class="col-md-2 control-label">Year: </label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" path="year" placeholder="Year"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-card-manuf" class="col-md-2 control-label">Manufacturer: </label>
                        <div class="col-md-8">
                            <select class="form-control" name="manufactId" path="manufact" id="manufact-dropdown">
                                <option disabled selected>Choose Manufacturer</option>
                                <c:forEach var="currentManufact" items="${manufactList}">
                                    <option value="${currentManufact.manufactId}">
                                        <c:out value="${currentManufact.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div> 
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-4">
                            <a href="${pageContext.request.contextPath}/" class="btn btn-default" id="card-cancel-button" >
                                Cancel
                            </a>
                        </div>
                        <div class="col-md-4">
                            <input type="submit" id="create-button" class="btn btn-default" value="Create Card"/>
                        </div>
                    </div>
                </sf:form>
            </div>
            <div id="display-cards">
               <h4>Current Cards</h4>
                    <ul class="list-group" id="errorMessages"></ul>
                    <table id="sightTable" class="table table-hover table-striped" frame="box" style="text-align: center">
                        <tr style="background-color: darkGrey">
                            <th width="10%" style="text-align: center">ID</th>
                            <th width="15%" style="text-align: center">Number</th>
                            <th width="30%" style="text-align: center">Player's Name</th>
                            <th width="20%" style="text-align: center">Year</th>
                            <th width="25%" style="text-align: center">Manufacturer</th>
                        </tr>
                        <c:forEach var="currentCard" items="${cards}">
                            <tr>
                                <td>
                                    <c:out value="${currentCard.cardId}"/> 
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentCard.cardNumber}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentCard.playerName}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentCard.year}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentCard.manufactuer.name}"/> 
                                </td>                               
                            </tr>
                        </c:forEach>
                    </table> 
            </div>
        </div>
    </body>
</html>
