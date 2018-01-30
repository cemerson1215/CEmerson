<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Org</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">       
        <link href="${pageContext.request.contextPath}/css/homepage.css" rel="stylesheet">  
    </head>
    <body>
        <div class="container" id="mainHeaderDiv">
            <h1 id="mainHeading" class="text-center">Add a New Org</h1>
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
                                <li><a href="${pageContext.request.contextPath}/" id="createDvdButton" class="btn btn-default">Home Lair</a></li>
                            </ul>
                        </div>                       
                    </li>
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
            <hr>
            <div id="createPowerFormDiv">
                <sf:form class="form-horizontal" role="form" modelAttribute="Org" action="createOrgForm" method="POST">
                    <div class="form-group">
                        <label for="create-power-descrp" class="col-md-2 control-label">Name: </label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" path="name" placeholder="Org Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-power-descrp" class="col-md-2 control-label">Description </label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" path="description" placeholder="Org Description"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-power-descrp" class="col-md-2 control-label">Address </label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" path="address" placeholder="Org Address"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-power-descrp" class="col-md-2 control-label">Email </label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" path="email" placeholder="Org Email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-power-descrp" class="col-md-2 control-label">Phone </label>
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" path="phone" placeholder="Org Phone"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-4">
                            <input type="hidden" id="create-power-id">
                            <a href="${pageContext.request.contextPath}/" class="btn btn-default" id="create-cancel-button" >
                                Cancel
                            </a>
                        </div>
                        <div class="col-md-4">
                            <input type="submit" id="create-button" class="btn btn-default" value="Create Org"/>
                        </div>
                    </div>
                </sf:form>

                <div id="currentOrgTableDiv">
                    <hr>                
                    <h4>Current Orgs</h4>
                    <ul class="list-group" id="errorMessages"></ul>
                    <table id="sightTable" class="table table-hover table-striped" frame="box" style="text-align: center">
                        <tr style="background-color: darkGrey">
                            <th width="10%" style="text-align: center">ID</th>
                            <th width="15%" style="text-align: center">Name</th>
                            <th width="20%" style="text-align: center">Description</th>
                            <th width="20%" style="text-align: center">Address</th>
                            <th width="20%" style="text-align: center">Email</th>
                            <th width="15%" style="text-align: center">Phone</th>
                        </tr>
                        <c:forEach var="currentOrg" items="${orgList}">
                            <tr>
                                <td>
                                    <c:out value="${currentOrg.orgId}"/> 
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentOrg.name}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentOrg.description}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentOrg.address}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentOrg.email}"/> 
                                </td>
                                <td>
                                    <c:out value="${currentOrg.phone}"/> 
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
        <script src="${pageContext.request.contextPath}/js/newfile.js"></script>
        <script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>

        <script>tinymce.init({selector: 'textarea', // change this value according to your HTML
                menu: {
                    file: {title: 'File', items: 'newdocument'},
                    edit: {title: 'Edit', items: 'undo redo | cut copy paste pastetext | selectall'},
                    insert: {title: 'Insert', items: 'link media | template hr'},
                    view: {title: 'View', items: 'visualaid'},
                    format: {title: 'Format', items: 'bold italic underline strikethrough superscript subscript | formats | removeformat'},
                    table: {title: 'Table', items: 'inserttable tableprops deletetable | cell row column'},
                    tools: {title: 'Tools', items: 'spellchecker code'}
                }});</script>

        <script type="text/javascript" src="<your installation path>/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript">
tinyMCE.init({
        theme : "advanced",
        mode : "textareas",
        plugins : "fullpage",
        theme_advanced_buttons3_add : "fullpage"
});
</script>

<form method="post" action="somepage">
        <textarea name="content" style="width:100%">
        </textarea>
</form>
    </body>
</html>


