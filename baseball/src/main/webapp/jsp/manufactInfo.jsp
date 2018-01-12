<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Manufact Info</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">       
        <link href="${pageContext.request.contextPath}/css/homepage.css" rel="stylesheet">  
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Manufacturer Information</h1>            
        </div>
        <div class="container">
            <a href="${pageContext.request.contextPath}/">Home</a>
            <div id="createManufact">
                
            </div>
            <div id="listManufacts">
                <h4>Current Manufacturers</h4>
                <table id="manufactTable" class="table table-hover table-striped" frame="box" style="text-align: center">
                    <tr style="background-color: darkGrey">
                        <th width="15%" style="text-align: center">ID</th>
                        <th width="65%" style="text-align: center">Name</th>
                        <th width="20%" style="text-align: center"></th>
                    </tr>
                    <c:forEach var="currentManufact" items="${manufactList}">
                        <tr>
                            <td>
                                <c:out value="${currentManufact.manufactId}"/>
                            </td>
                            <td>
                                <c:out value="${currentManufact.name}"/>
                            </td>
                            <td>
                                Edit | Delete
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>               
    </body>
</html>
