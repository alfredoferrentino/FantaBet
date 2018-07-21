<%@page import="bean.CalciatoreBean"%>
<%@page import="java.util.*,bean.ClassificaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
    	Collection<?> classifica = (Collection<?>) session.getAttribute("classifica");
		Collection<?> formazione = null;
		String ruolo = (String) session.getAttribute("isLogged");
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
    			<% if (ruolo.equals("u"))  { %>
    			<ul class="nav navbar-nav">
      				<li class="active"><a href="home_utente.jsp">Home</a></li>
      				<li><a href="profilo.jsp">Profilo</a></li>
      				<li><a href="CompetizioniUtenteServlet?action=competizioni">Competizioni</a></li>
      			</ul>
      			<% } else { %>
      			<ul class="nav navbar-nav">
      				<li class="active"><a href="home_utente.jsp">Home</a></li>
      				<li><a href="profilo.jsp">Profilo</a></li>
      				<li><a href="CompetizioneServlet?action=retrieve-comp">Gestione Competizioni</a></li>
      				<li><a href="UtenteServlet?action=utenti">Gestione Utenti</a></li>
      			</ul>
      			<% } %>
      				
      				
      				
				
    			<ul class="nav navbar-nav navbar-right">
      				<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    			</ul>
  			</div>
		</nav>
		<div class="col-md-4">
			<h2>La mia formazione</h2>
			<div class="lista hidden">
			<% formazione = (Collection<?>) session.getAttribute("formazione");
			if (formazione != null && formazione.size() != 0) {
				Iterator<?> iter = formazione.iterator();
				while(iter.hasNext()) {
					CalciatoreBean bean = (CalciatoreBean) iter.next(); %>
					<p><b><%=bean.getNome() %></b>  <b><%=bean.getCognome() %></b></p>
					
			<% 	}} else  %> <p><b>Non hai ancora inserito la formazione per questa giornata</b> </p>
			</div>
		</div>
		<div class="col-md-4">
			<h2>Classifica Fantamondiale</h2>
			<div class="lista hidden">
  				<div class="lista-titoli">
  					<h6>#</h6>
  					<h6>Nickname</h6>
  					<h6>Score</h6>
  				</div>
  				<hr>
  				<div class="contenitore-classifica">
  				<%
  					if (classifica != null && classifica.size() != 0) {
  						Iterator<?> it = classifica.iterator();
  						while (it.hasNext()) {
  							ClassificaBean bean = (ClassificaBean) it.next();
  				%>
					<div class="item-classifica">
						<p><%=bean.getPosizione()%></p>
						<p><%=bean.getNickname() %></p>
						<p><%=bean.getPunteggio() %></p>
					</div>
					<hr>
					
					
					
				
    				<%} } %>
				</div>
			</div>
			
		</div>
		<div class="col-md-4">
			<h2>Competizioni attive</h2>
			<table class="table table-hover hidden">
  				<thead>
    				<tr>
      					<th scope="col">#</th>
      					<th scope="col">First</th>
      					<th scope="col">Last</th>
      					<th scope="col">Handle</th>
    				</tr>
  				</thead>
  				<tbody>
    				<tr>
      					<th scope="row">1</th>
      					<td>Mark</td>
      					<td>Otto</td>
      					<td>@mdo</td>
    				</tr>
    				<tr>
      					<th scope="row">2</th>
      					<td>Jacob</td>
      					<td>Thornton</td>
      					<td>@fat</td>
    				</tr>
    				<tr>
      					<th scope="row">3</th>
      					<td colspan="2">Larry the Bird</td>
      					<td>@twitter</td>
    				</tr>
  				</tbody>
			</table>
		</div>
	</div>

<script src="script/home_script.js"></script>
</body>
</html>