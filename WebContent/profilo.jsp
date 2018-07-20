<%@page import="java.util.Iterator"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	UserBean utente = (UserBean) session.getAttribute("bean");
    	String ruolo = (String) session.getAttribute("isLogged");
    %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<link rel="stylesheet" href="style/profilo.css">
  	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>
	<title>Profilo</title>
</head>
<body>
	<div class="profilo">
		<nav class="navbar navbar-inverse">
  			<div class="container-fluid">
    			<div class="navbar-header">
    				<a class="navbar-brand" href="#"><img src="icon/Logo.png" style="width:150px; height:60px"></a>
    			</div>
    			<% if (ruolo.equals("u"))  { %>
    			<ul class="nav navbar-nav">
      				<li><a href="home_utente.jsp">Home</a></li>
      				<li class="active"><a href="profilo.jsp">Profilo</a></li>
      				<li><a href="CompetizioniUtenteServlet?action=competizioni">Competizioni</a></li>
      			</ul>
      			<% } else { %>
      			<ul class="nav navbar-nav">
      				<li><a href="home_utente.jsp">Home</a></li>
      				<li class="active"><a href="profilo.jsp">Profilo</a></li>
      				<li><a href="CompetizioneServlet?action=retrieve-comp">Gestione Competizioni</a></li>
      				<li><a href="gestione_utenti.jsp">Gestione Utenti</a></li>
      			</ul>
      			<% } %>
      				
      				
      				
				
    			<ul class="nav navbar-nav navbar-right">
      				<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    			</ul>
  			</div>
		</nav>
		<div class="info-profilo">
			<div class="dati-personali">
				<h6>Nickname : <%=utente.getNick() %></h6>
				<h6>E-mail : <%=utente.getEmail() %></h6>
							
			
			</div>
			<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modalModifica">Modifica Profilo</button>
		
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
            		<form role="form" data-toggle="validator">
           			<div class="modal-body mx-3">
           				<div class="form-group">
    						<label for="nick" class="control-label">Nickname</label>
    						<input pattern="^[_A-z0-9]{1,20}$" type="text" class="form-control" id="nick" value="<%=utente.getNick()%>">
    						<div class="help-block with-errors">Only letters and numbers, no special characters</div>
  						</div>
  						<div class="form-group">
    						<label for="pass" class="control-label">Password</label>
        					<input type="password" pattern="^[_A-z0-9]{6,15}$" class="form-control" id="pass" value="<%=utente.getPassword()%>">
       						 <div class="help-block with-errors">Minimum of 6 characters, no special characters</div>
      					</div>
      					<div class="form-group">
      						<label for="pass-conf" class="control-label">Confirm Password</label>
        					<input type="password" class="form-control" id="pass-conf" data-match="#pass" data-match-error="Whoops, these don't match" placeholder="Confirm">
        					<div class="help-block with-errors"></div>
        				</div>
        				<div class="form-group">
   							<label for="mail">Email</label>
   							<input class="form-control" type="email" id="mail" value="<%=utente.getEmail()%>">
   							<div class="help-block with-errors"></div>
  						</div>
            		</div>
            		</form>
            		<div class="modal-footer">
            			<input type="hidden" id="user" value="<%=utente.getUsername()%>">
                		<button id="modifica" class="btn btn-primary btn-lg">Modifica<i class="fas fa-paper-plane"></i></button>
            		</div>
        		</div>
    		</div>
		</div>
	</div>
<script src="script/profilo.js"></script>
</body>
</html>