<html>
<link rel="stylesheet" type="text/css"
href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />
<script type="text/javascript"
src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Statistiques</title>
</head>
<body>
<div class="container mt-5">
<div class="card">
<center>
 <div class="card-header">
 Statistiques sur les Clients
 </div>
 </center>
 <div class="card-body">
    <table class="table table-striped">
        <tr>
            <th>Profession</th>
            <th>Salaire moyen</th>
        </tr>
        <c:forEach items="${averageSalaryByProfession.entrySet()}" var="entry">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<br/>
<br/>
<center><a href="showCreate">Accueil</a></center>
</body>
</html>