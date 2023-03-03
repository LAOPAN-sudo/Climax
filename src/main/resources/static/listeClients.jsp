<%@ page language="java" contentType="text/html; charset=windows-1256"
 pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css"
href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<script type="text/javascript"
src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Liste Clients</title>
</head>
<body>
<div class="container mt-5">
<div class="card">
<center>
 <div class="card-header">
 Liste des Clients
 </div>
 </center>
 <div class="card-body">
 
 <table class="table table-striped">
 <tr>
<th>ID</th><th>Nom Partenaire</th><th>Nom Client</th><th>Prenom Client</th><th>Age</th><th>Profession</th><th>Salaire</th><th>Suppression<th>Edition</th>
 </tr>
 <c:forEach items="${clients}" var="p">
 <tr>
 <td>${p.idclient }</td>
 <td>${p.nompartenaire }</td>
 <td>${p.nomclient }</td>
 <td>${p.prenomclient }</td>
 <td>${p.age }</td>
 <td>${p.profession }</td>
 <td>${p.salaire }</td>
<td><a onclick="return confirm('Etes-vous sûr ?')"
href="supprimerClient?id=${p.idclient }">Supprimer</a></td>
 <td><a href="modifierClient?id=${p.idclient }">Edit</a></td>
 </tr>
 </c:forEach>
 <center><a href="showCreate">Accueil</a></center> 
 </table>
 </div>
</div>
</div>
<br/>
<br/>

<div class="container mt-5">
<div class="card"><center>

	<form class="col-sm-6" action="average-salary" method="get" >
 		<div class="form-group" id="myForm1">
 		<label class="control-label">Profession :</label>
 		<input  required type="text" name="profession" class="form-control"/>
 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 		<div id="myForm9">
 		<center><button  type="submit" class="btn btn-primary">Calculer</button></center>
		</div> 
 	</form></center>
	<div>
 	<form class="col-sm-3" action="average-salary2" method="get" >
 		<div id="myForm9">
 		<center><button  type="submit" class="btn btn-primary btn-lg btn-block">Moyenne pour toutes les Professions</button></center>
		</div>
 	</form></div>
 </div>
 </div>
 	</div>
</div></center>
</body>
</html>
