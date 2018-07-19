<%@page import="bean.UserBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Collection<?> utenti = null;
    	String ruolo = (String) session.getAttribute("isLogged");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<link rel="stylesheet" href="style/gestione_competizioni.css">
  	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>
<title>Gestione Utenti</title>
</head>
<body>
<div class="container">
		<nav class="navbar navbar-inverse">
  			<div class="container-fluid">
    			<div class="navbar-header">
    				<a class="navbar-brand" href="#"><img src="icon/Logo.png" style="width:150px; height:60px"></a>
    			</div>
    			<% if (ruolo == "a")  { System.out.println("Il ruolo è :" + ruolo);%>
    			<ul class="nav navbar-nav">
      				<li><a href="home_utente.jsp">Home</a></li>
      				<li><a href="profilo.jsp">Profilo</a></li>
      				<li><a href="competizioni.jsp">Competizioni</a></li>
      			</ul>
      			<% } else { %>
      			<ul class="nav navbar-nav">
      				<li><a href="home_utente.jsp">Home</a></li>
      				<li><a href="profilo.jsp">Profilo</a></li>
      				<li><a href="AdminControl?action=retrieve-comp">Gestione Competizioni</a></li>
      				<li class="active"><a href="gestione_utenti.jsp">Gestione Utenti</a></li>
      			</ul>
      			<% } %>
      				
      				
      				
				
    			<ul class="nav navbar-nav navbar-right">
      				<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    			</ul>
  			</div>
		</nav>

	<div class="lista"> 
		<div class="col-md-4 col-md-offset-4">
		<h5>Lista Utenti</h5>
  			<div class="lista-titoli">
  				<h6>Nome</h6>
  				<h6>Visualizza</h6>
  				<h6>Rimuovi</h6>
  			</div>
  			<hr>
  			<div class="contenitore-classifica">
  				
  			<%  utenti= (Collection<?>) session.getAttribute("lista_utenti");
  				if (utenti != null && utenti.size() != 0) {
  					Iterator<?> it = utenti.iterator();
  						while (it.hasNext()) {
  							UserBean utente = (UserBean) it.next();
  			%>
			<div class="item-classifica">
				<p><%=utente.getNick()%></p>
				<a class="text-warning" style="text-decoration:none;" data-toggle="modal" data-target="#showmodal" onclick = "mostra(this, '<%=utente.getNick()%>', '<%=utente.getUsername()%>', '<%=utente.getEmail()%>')">Visualizza</a>
				<a class="text-warning" style="text-decoration:none;" data-toggle="modal" data-target="#modalModifica" onclick = "modifica(this, '<%=utente.getUsername()%>', '<%=utente.getNick()%>', '<%=utente.getEmail()%>', '<%=utente.getPassword()%>')">Modifica</a>
			</div>
			<hr>

    		<%} }  %>
    		</div>
		</div>
	</div>
	<div class="modal fade" id="showmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
        	<div class="modal-content">
            	<div class="modal-header text-center">
                	<h4 class="modal-title w-100 font-weight-bold"></h4>
                	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                	</button>
				</div>
				<div class="modal-body mx-3">
                	<div class="md-form mb-5">
                    	<i class="fas fa-user-tie"></i>
                    	<h6>Nickname : </h6><p class="nome"></p>
               		</div>
               		<div class="md-form mb-5">
                    	<i class="fas fa-user-tie"></i>
                    	<h6>Username : </h6><p class="partecipanti"></p> <br>
               		</div>
               		<div class="md-form mb-5">
                    	<i class="fas fa-user-tie"></i>
                    	<h6>Email : </h6><p class="giornate"></p> <br>
               		</div>
               	</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalModifica" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    		<div class="modal-dialog" role="document">
        		<div class="modal-content">
            		<div class="modal-header text-center">
                		<h4 class="modal-title w-100 font-weight-bold">Modifica Profilo</h4>
                		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    	<span aria-hidden="true">&times;</span>
                		</button>
            		</div>
           			<div class="modal-body mx-3">
           			<form role="form" data-toggle="validator">
           			<div class="modal-body mx-3">
           				<div class="form-group">
    						<label for="nick" class="control-label">Nickname</label>
    						<input pattern="^[_A-z0-9]{1,20}$" type="text" class="form-control" id="nick">
    						<div class="help-block with-errors">Only letters and numbers, no special characters</div>
  						</div>
  						<div class="form-group">
    						<label for="pass" class="control-label">Password</label>
        					<input type="password" pattern="^[_A-z0-9]{6,15}$" class="form-control" id="pass">
       						 <div class="help-block with-errors">Minimum of 6 characters, no special characters</div>
      					</div>
      					<div class="form-group">
      						<label for="pass-conf" class="control-label">Confirm Password</label>
        					<input type="password" class="form-control" id="pass-conf" data-match="#pass" data-match-error="Whoops, these don't match" placeholder="Confirm">
        					<div class="help-block with-errors"></div>
        				</div>
        				<div class="form-group">
   							<label for="mail">Email</label>
   							<input class="form-control" type="email" id="mail">
   							<div class="help-block with-errors"></div>
  						</div>
  						<div class="form-group">
    						<label for="username" class="control-label">Username</label>
    						<input pattern="^[_A-z0-9]{1,20}$" type="text" class="form-control" id="username">
    						<div class="help-block with-errors">Only letters and numbers, no special characters</div>
  						</div>
            		</div>
            		</form>
            		</div>
            		<div class="modal-footer">
                		<button id="modifica" class="btn btn-primary btn-lg">Modifica<i class="fas fa-paper-plane"></i></button>
            		</div>
        		</div>
    		</div>
		</div>
	
	
</div>
<script src="script/gestione_utenti.js"></script>
</body>
</html>