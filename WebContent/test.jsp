<%@page import="bean.CalciatoreBean"%>
<%@page import="java.util.*,bean.ClassificaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
    	
		Collection<?> calciatori = null;
		String ruolo = (String) session.getAttribute("isLogged");
		System.out.println("Il ruolo di cicciocasillo è :" + ruolo); 
    %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<link rel="stylesheet" href="style/home_utente.css">
  	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	
	<title>FantaBet</title>
</head>
<body>
	<div class="home">
		<nav class="navbar navbar-inverse">
  			<div class="container-fluid">
    			<div class="navbar-header">
    				<a class="navbar-brand" href="#"><img src="icon/Logo.png" style="width:150px; height:60px"></a>
    			</div>
    			<% if (ruolo.equals("u"))  { System.out.println("Il ruolo è :" + ruolo);%>
    			<ul class="nav navbar-nav">
      				<li class="active"><a href="home_utente.jsp">Home</a></li>
      				<li><a href="profilo.jsp">Profilo</a></li>
      				<li><a href="Competizione?action=competizioni">Competizioni</a></li>
      			</ul>
      			<% } else { %>
      			<ul class="nav navbar-nav">
      				<li class="active"><a href="home_utente.jsp">Home</a></li>
      				<li><a href="profilo.jsp">Profilo</a></li>
      				<li><a href="Competizione?action=retrieve-comp">Gestione Competizioni</a></li>
      				<li><a href="gestione_utenti.jsp">Gestione Utenti</a></li>
      			</ul>
      			<% } %>
      				
      				
      				
				
    			<ul class="nav navbar-nav navbar-right">
      				<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    			</ul>
  			</div>
		</nav>
		
		<div class="col-md-4">
			<h2>Classifica Fantamondiale</h2>
			<div class="lista hidden">
  				<div class="lista-titoli">
  					<h6>Nome</h6>
  					<h6>Goal</h6>
  					<h6>Assist</h6>
  					<h6>Ammon.</h6>
  					<h6>Espuls.</h6>
  					<h6>Voto</h6>
  					<h6>Operazione</h6>
  				</div>
  				<hr>
  				<div class="contenitore-classifica">
  				<%  calciatori = (Collection<?>) session.getAttribute("lista-calciatori");
  				if (calciatori != null && calciatori.size() != 0) {
  					Iterator<?> iter = calciatori.iterator();
  						while (iter.hasNext()) {
  							CalciatoreBean bean = (CalciatoreBean) iter.next();
  			%>
			<div class="item-classifica">
				<p><%=bean.getNome()%>  <%=bean.getCognome() %></p>
				<input type="number">
				<input type="number">
				<input type="checkbox">
				<input type="checkbox">
				<input type="number">
				<button>Inserisci</button>
			</div>
			<hr>

    		<%} }  %>
				</div>
			</div>
			
		</div>
		
	</div>

<script src="script/home_script.js"></script>
</body>
</html>