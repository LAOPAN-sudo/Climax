<%@ page language="java" contentType="text/html; charset=windows-1256"
 pageEncoding="windows-1256"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Creer Client</title>





</head>
<body>
<div class="container mt-2" >
<div class="card-body">
<center>
<div class="form-check">
  <input class="form-check-input" type="checkbox" value="" id="myCheckbox">
  <label class="form-check-label" for="flexCheckDefault">
    Creer un client ou Lire un fichier partenaire
  </label>
</div>
</center></br></br>
<center>
<form class="col-sm-6" action="saveclients" method="post" >



 <div class="form-group" id="myForm1">
 <label class="control-label">Nom Partenaire :</label>
 <input  required type="text" name="nompartenaire" class="form-control"/>
 </div>
  <div class="form-group" id="myForm2">
 <label class="control-label">Nom Client :</label>
 <input  required type="text" name="nomclient" class="form-control"/>
 </div>
  <div class="form-group" id="myForm3">
 <label class="control-label">Prenom Client :</label>
 <input  required type="text" name="prenomclient" class="form-control"/>
 </div>
 <div class="form-group" id="myForm4">
 <label class="control-label">Age :</label>
 <input  required type="number" name="age" min="1" max="100" class="form-control"/>
 </div> 
 <div class="form-group" id="myForm5">
 <label class="control-label">Profession :</label>
 <input  required type="text" name="profession" class="form-control"/>
 </div>
 <div class="form-group" id="myForm6">
 <label class="control-label">Salaire :</label>
 <input  required type="number" maxlength="3" min="1" max="100" name="salaire" class="form-control"/>

 </div>
 </center>
 <center>
 <div id="myForm7">
 <button  type="submit" class="btn btn-primary" >Creer</button>
</div>
</center>
</form>

<form action="readclients" enctype="multipart/form-data" method="post" id="myForm2">

	 <div class="mb-3" id="myForm8">
  		<label for="formFile" class="form-label">Selectionner un fichier partenaire</label>
  			<input required class="form-control" accept=".txt, .csv, .xml, .json, .docx, .xls, .xlsx" onchange="checkFileType(this)" type="file" id="file" name="file">
	</div>

	 <div id="myForm9">
 		<center><button  type="submit" class="btn btn-primary">Lire</button></center>
	</div>

</form>

</div>
${msg}
<br/>
<center><a href="ListeClients">Liste Clients</a></center>
</div>

	<script type="text/javascript">
    var form1 = document.getElementById('myForm1');
    var form2 = document.getElementById('myForm2');
    var form3 = document.getElementById('myForm3');
    var form4 = document.getElementById('myForm4');
    var form5 = document.getElementById('myForm5');
    var form6 = document.getElementById('myForm6');
    var form7 = document.getElementById('myForm7');
    var form8 = document.getElementById('myForm8');
    var form9 = document.getElementById('myForm9');
    form8.disabled = true;form8.style.display = "none";
    form9.disabled = true;form9.style.display = "none";
    const checkbox = document.getElementById('myCheckbox');
    checkbox.addEventListener('change', (event) => {
        if (event.target.checked) {
            form1.disabled = true;form1.style.display = "none";
            form2.disabled = true;form2.style.display = "none";
            form3.disabled = true;form3.style.display = "none";
            form4.disabled = true;form4.style.display = "none";
            form5.disabled = true;form5.style.display = "none";
            form6.disabled = true;form6.style.display = "none";
            form7.disabled = true;form7.style.display = "none";
            form8.disabled = false;form8.style.display = "block";
            form9.disabled = false;form9.style.display = "block";
        } else {
            form1.disabled = false;form1.style.display = "block";
            form2.disabled = false;form2.style.display = "block";
            form3.disabled = false;form3.style.display = "block";
            form4.disabled = false;form4.style.display = "block";
            form5.disabled = false;form5.style.display = "block";
            form6.disabled = false;form6.style.display = "block";
            form7.disabled = false;form7.style.display = "block";
            form8.disabled = true;form8.style.display = "none";
            form9.disabled = true;form9.style.display = "none";
        }
    });
    
    function checkFileType(file) {
  		const file1 = file.files[0];
  		const allowedTypes = /(\.|\/)(vnd.openxmlformats-officedocument.wordprocessingml.document|plain|csv|xml|json|vnd.ms-excel|vnd.openxmlformats-officedocument.spreadsheetml.sheet)$/;

  		if (!allowedTypes.test(file1.type)) {
    		alert("Le type de fichier n'est pas autorisé !");
    		file.value = "";
   			 return false;
  }
}
    
</script>


</body>
</html>
