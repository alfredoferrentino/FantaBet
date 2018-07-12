<%@page import="bean.CalciatoreBean"%>
<%@page import="java.util.Iterator, bean.ClassificaBean"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	Collection<?> competizioni = (Collection<?>) session.getAttribute("competizioni");
    	Collection<?> classifica_competizione = null;
    	Collection<?> calciatori = null;
    	Collection<?> formazione = null;
    %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<link rel="stylesheet" href="style/competizioni.css">
  	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
	<title>Competizioni</title>
</head>
<body>
	<div class="profilo">
		<nav class="navbar navbar-inverse">
  			<div class="container-fluid">
    			<div class="navbar-header">
    				<a class="navbar-brand" href="#"><img src="icon/Logo.png" style="width:150px; height:60px"></a>
    			</div>
    			<ul class="nav navbar-nav">
      				<li><a href="Data">Home</a></li>
      				<li><a href="profilo.jsp">Profilo</a></li>
      				<li class="active"><a href="competizioni.jsp">Competizioni</a></li>
				</ul>
    			<ul class="nav navbar-nav navbar-right">
      				<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    			</ul>
  			</div>
		</nav>
		<div class = "barra">
			<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modalSubscriptionForm">Richiedi Competizione</button>
			<div class="button-group">
				<button id="inserisci-form" class="btn btn-primary btn-lg">Inserisci Formazione</button>
				<button id="visualizza-form" class="btn btn-primary btn-lg">Visualizza Formazione</button>
				<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#aggiungiPartForm">Aggiungi Partecipante</button>
			</div>
			<select id="selectcomp" class="selectpicker">
				<option value=""></option>
		<%
  			if (competizioni != null && competizioni.size() != 0) {
  			Iterator<?> it = competizioni.iterator();
  				while (it.hasNext()) {
  		%>
 				<option><%=it.next()%></option>
 		<%} } %>
			</select>

		
		</div>
		<div class="lista">
			<div class="col-md-4 col-md-offset-4">
				<h2>Classifica Competizione</h2>
  				<div class="lista-titoli">
  					<h6>#</h6>
  					<h6>Nickname</h6>
  					<h6>Score</h6>
  				</div>
  				<hr>
  				<div class="contenitore-classifica">
  				
  				<%	classifica_competizione = (Collection<?>) session.getAttribute("classifica-competizione");
  					if (classifica_competizione != null && classifica_competizione.size() != 0) {
  						Iterator<?> it = classifica_competizione.iterator();
  						while (it.hasNext()) {
  							ClassificaBean bean = (ClassificaBean) it.next();
  				%>
					<div class="item-classifica">
						<p><%=bean.getPosizione()%></p>
						<p><%=bean.getNickname() %></p>
						<p><%=bean.getPunteggio() %></p>
					</div>
					<hr>
					
					
					
				
    				<%} }  %>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modalSubscriptionForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    		<div class="modal-dialog" role="document">
        		<div class="modal-content">
            		<div class="modal-header text-center">
                		<h4 class="modal-title w-100 font-weight-bold">Richiedi Competizione</h4>
                		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    	<span aria-hidden="true">&times;</span>
                		</button>
            		</div>
           			<div class="modal-body mx-3">
                		<div class="md-form mb-5">
                    		<i class="fas fa-users"></i>
                    		<label data-error="wrong" data-success="right" for="form3">Con quante persone vuoi giocare?</label>
                    		<input type="number" id="form3" class="form-control validate">
               			</div>
               			<div class="md-form mb-4">
                    		<i class="fas fa-list-ol"></i>
                    		<label data-error="wrong" data-success="right" for="form2">Quante giornate vuoi giocare?</label>
                    		<input type="number" id="form2" class="form-control validate">
                		</div>
                		<div class="md-form mb-4">
                    		<i class="fa fa-user prefix grey-text"></i>
                    		<label data-error="wrong" data-success="right" for="form1">Nome della competizione</label>
                    		<input type="text" id="form1" class="form-control validate">
                		</div>
            		</div>
            		<div class="modal-footer">
                		<button id="send" class="btn btn-primary btn-lg">Richiedi <i class="fas fa-paper-plane"></i></button>
            		</div>
        		</div>
    		</div>
		</div>
		<div class="modal fade" id="aggiungiPartForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    		<div class="modal-dialog" role="document">
        		<div class="modal-content">
            		<div class="modal-header text-center">
                		<h4 class="modal-title w-100 font-weight-bold">Aggiungi Partecipante</h4>
                		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    	<span aria-hidden="true">&times;</span>
                		</button>
            		</div>
           			<div class="modal-body mx-3">
                		<div class="md-form mb-5">
                    		<i class="fas fa-users"></i>
                    		<label data-error="wrong" data-success="right" for="form4">Chi vuoi aggiungere?</label>
                    		<input type="text" id="form4" class="form-control validate">
               			</div>
            		</div>
            		<div class="modal-footer">
                		<button id="add" class="btn btn-primary btn-lg">Aggiungi <i class="fas fa-paper-plane"></i></button>
            		</div>
        		</div>
    		</div>
		</div>
	<div class="form-display">
		<div class="container-formazione">
			<div class="modulo">
				<h5>Scegli il modulo : </h5>
				<select id="selectmodulo" class="selectpicker">
					<option>4-3-3</option>
					<option>3-4-3</option>
					<option>5-3-2</option>
					<option>4-4-2</option>
					<option>3-5-2</option>
				</select>
				<button id="insert-form" class="btn btn-primary btn-lg">Inserisci</button>
			</div>
			<div class="formazione">
				<div class="alert"></div>
				<div class="giocatori"></div>
				<div class="costo">Costo : 0/150 Crediti</div>
			</div>
			<div class="calciatori">
				<div style="margin:10px" class="container-calciatori">
					<button id="p" class="btn btn-primary btn-sm">P</button>
					<button id="d" class="btn btn-primary btn-sm">D</button>
					<button id="c" class="btn btn-primary btn-sm">C</button>
					<button id="a" class="btn btn-primary btn-sm">A</button>
				</div>
                	<div class="list-group">
                	<%	calciatori = (Collection<?>) session.getAttribute("calciatori");
                    	System.out.println(calciatori);
                		if (calciatori != null && calciatori.size() != 0) {
      					Iterator<?> it = calciatori.iterator();
      					while (it.hasNext()) {
      						CalciatoreBean bean = (CalciatoreBean) it.next(); %>
                  		<a onclick="aggiungi(this, '<%=bean.getNome()%> <%=bean.getCognome()%>',<%=bean.getId()%>, '<%=bean.getRuolo()%>', <%=bean.getPrezzo()%>)" class="list-group-item"><%=bean.getNome() + "  " + bean.getCognome() + " <b> " + bean.getPrezzo() +".00</b>" %></a>
                  	<%} } %>
					</div>
			</div>
		</div>
	</div>
	
	<div class="visualizza_formazione">
		<% formazione = (Collection<?>) session.getAttribute("formazione");
			if (formazione != null && formazione.size() != 0) {
				Iterator<?> iter = formazione.iterator();
				while(iter.hasNext()) {
					CalciatoreBean bean = (CalciatoreBean) iter.next(); %>
					<p><b><%=bean.getNome() %></b>  <b><%=bean.getCognome() %></b></p>
					
			<% 	}} else  %> <p><b>Non hai ancora inserito la formazione per questa giornata</b> 
		
	
	</div>
</div>
<script src="script/competizioni.js"></script>
</body>
</html>