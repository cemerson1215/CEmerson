<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
        </div>
    </body>
</html>
