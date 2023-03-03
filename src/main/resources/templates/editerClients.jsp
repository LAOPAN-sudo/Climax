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
<title>Modifier un Client</title>
</head>
<body>
<div class="container mt-5">
<center><div class="card-body">
<form class="col-sm-6" action="updateclients" method="post">
 <div class="form-group">
 <label class="control-label">ID Client :</label>
 <input type="number" name="idclient" value="${clients.idclient}" readonly class="form-control"/>
 </div>
 <div class="form-group">
 <label class="control-label">Nom Partenaire :</label>
 <input type="text" name="nompartenaire" value="${clients.nompartenaire}" class="form-control"/>
 </div>
  <div class="form-group">
 <label class="control-label">Nom Client :</label>
 <input type="text" name="nomclient" value="${clients.nomclient}" class="form-control"/>
 </div>
  <div class="form-group">
 <label class="control-label">Prenom Client :</label>
 <input type="text" name="prenomclient" value="${clients.prenomclient}" class="form-control"/>
 </div>
 <div class="form-group">
 <label class="control-label">Age :</label>
 <input type="number" name="age" value="${clients.age}" class="form-control"/>
 </div> 
  <div class="form-group">
 <label class="control-label">Profession :</label>
 <input type="text" name="profession" value="${clients.profession}" class="form-control"/>
 </div>
<div class="form-group">
 <label class="control-label">Salaire :</label>
 <input type="number" name="salaire" value="${clients.salaire}" class="form-control"/>
 </div> 
 </div>
 <div>
 <button type="submit" class="btn btn-primary">Modifier</button>
</div>
</form>
</div>
</center>
<br/>
<center><a href="ListeClients">Liste Clients</a></center>
</div>
</body>
</html>