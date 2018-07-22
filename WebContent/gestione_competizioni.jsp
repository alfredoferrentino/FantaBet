<%@page import="bean.CalciatoreBean"%>
<%@page import="bean.CompetizioneBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Collection<?> competizioni = null;
    	String ruolo = (String) session.getAttribute("isLogged");
    	Collection<?> calciatori = null;
    	Collection<?> approva = null;
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
<title>Gestione Competizioni</title>
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
      				<li class="active"><a href="gestione_competizioni.jsp">Gestione Competizioni</a></li>
      				<li><a href="UtenteServlet?action=utenti">Gestione Utenti</a></li>
      			</ul>
      			<% } %>
      				
      				
      				
				
    			<ul class="nav navbar-nav navbar-right">
      				<li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
    			</ul>
  			</div>
		</nav>

	<div class="lista">
		<div class="searchBar">
    		<input id="searchComp" class="form-control" type="text" placeholder="Search" aria-label="Search">
    		<a class="text-warning" style="text-decoration:none;" onclick="mostraCalciatori()" href="CalciatoriServlet?action=carica-calciatori">Inserisci voti</a>
    		<a class="text-warning" style="text-decoration:none;" onclick="mostraApprovate()" href="ApprovazioniServlet?action=approva-comp">Approva Competizioni</a>
		</div>
		<div class="col-md-4 col-md-offset-4">
		<h5>Lista Competizioni</h5>
  			<div class="lista-titoli">
  				<h6>Nome</h6>
  				<h6>Visualizza</h6>
  				<h6>Rimuovi</h6>
  			</div>
  			<hr>
  			<div class="contenitore-classifica">
  				
  			<%  competizioni= (Collection<?>) session.getAttribute("lista_competizioni");
  				if (competizioni != null && competizioni.size() != 0) {
  					Iterator<?> it = competizioni.iterator();
  						while (it.hasNext()) {
  							CompetizioneBean competizione = (CompetizioneBean) it.next();
  			%>
			<div class="item-classifica">
				<p><%=competizione.getNome()%></p>
				<a class="text-warning" style="text-decoration:none;" data-toggle="modal" data-target="#showmodal" onclick = "show(this, '<%=competizione.getNome()%>', <%=competizione.getNumPartecipanti()%>, <%=competizione.getNumGiornate()%>)" >Visualizza</a>
				<a class="text-warning" style="text-decoration:none;" onclick = "remove(this, <%=competizione.getIdComp()%>)">Rimuovi</a>
			</div>
			<hr>

    		<%} } else { %>
    		<div class="alert alert-danger">
    		 	Non ci sono competizioni con quel nome.
  			</div>
  			<% } %>
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
                	<dl>
                		<dt><i class="fas fa-user-tie"></i>  Nome Competizione :</dt>
                    	<dd class="nome"></dd>
               		</dl>
               		<dl>
                		<dt><i class="fas fa-user-tie"></i>  Numero Partecipanti :</dt>
                    	<dd class="partecipanti"></dd>
               		</dl>
               		<dl>
                		<dt><i class="fas fa-user-tie"></i>  Numero Giornate :</dt>
                    	<dd class="giornate"></dd>
               		</dl>
               	</div>
			</div>
		</div>
	</div>
	<div class="voti">
		<div class="col-md-4 col-md-offset-4">
			<h5>Inserimento voti</h5>
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
				<h5><%=bean.getNome()%>  <%=bean.getCognome() %></h5>
				<input type="hidden" id="idCalc" value="<%=bean.getId() %>">
				<input id="goal" type="number" class="form-control validate" placeholder="Goal" value="0">
				<input id="assist" type="number"class="form-control validate" placeholder="Assist" value="0">
				<input id="ammon" type="checkbox" class="form-control validate">
				<input id="espuls" type="checkbox" class="form-control validate">
				<input onchange="checkVoto(this)" id="voto" min="0" max="10" type="number" class="form-control validate" placeholder="Voto">
				<button id="button-insert" onclick="insertVoto(this)" type="button" class="btn btn-warning">Inserisci</button>
			</div>
			<hr>

    		<%} }  %>
    		</div>
		</div>
	</div>
	<div class="approva">
		<div class="searchBar">
    		<input id="searchComp" class="form-control" type="text" placeholder="Search" aria-label="Search">
    		<a class="text-warning" style="text-decoration:none;" onclick="mostraCalciatori()" href="CalciatoriServlet?action=carica-calciatori">Inserisci voti</a>
    		<a class="text-warning" style="text-decoration:none;" onclick="mostraApprovate()" href="ApprovazioniServlet?action=approva-comp">Approva Competizioni</a>
		</div>
		<div class="col-md-4 col-md-offset-4">
		<h5>Lista Competizioni</h5>
  			<div class="lista-titoli">
  				<h6>Nome</h6>
  				<h6>Approva</h6>
  			</div>
  			<hr>
  			<div class="contenitore-classifica">
  				
  			<%  approva= (Collection<?>) session.getAttribute("approva-competizioni");
  				if (approva != null && approva.size() != 0) {
  					Iterator<?> it = approva.iterator();
  						while (it.hasNext()) {
  							CompetizioneBean approvaBean = (CompetizioneBean) it.next();
  			%>
			<div class="item-classifica">
				<p><%=approvaBean.getNome()%></p>
				<button onclick="approvaVoti(<%=approvaBean.getIdComp() %>)" type="button" class="btn btn-warning">Approva</button>
			</div>
			<hr>

    		<%} }  %>
    		</div>
    		
		</div>
	</div>
	
</div>
<script src="script/gestione_competizioni.js"></script>
</body>
</html>