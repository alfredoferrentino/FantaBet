<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="index.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>FantaBet</title>
</head>
<body>

	<div class="modal" id="loginmodal">

		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<img src="Logo.png" style="width: 200px; height: 150px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closemodal">
						<span title="CloseLogin">&times;</span>
					</button>
				</div>
				<form action="Login" method="post">
					<div class="modal-body">
						<input class="form-control" type="text" name="username"
							placeholder="Enter Username" required><br> <input
							class="form-control" type="password" name="psw"
							placeholder="Enter Password" required>
						<div class="checkbox">
							<label><input type="checkbox" checked="checked">Remember me</label>
						</div>
					</div>
					<div class="modal-footer">
						<div>
							<button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
						</div>
						<div>
							<p style="margin-top: 20px;">Not already signed?</p>
							<button type="button" id="registerbtn" class="btn btn-link">Register</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal" id="registermodal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<img src="Logo.png" style="width: 200px; height: 150px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeregistermodal">
						<span title="CloseLogin">&times;</span>
					</button>
				</div>
				<form action="Registrazione" method="post">
					<div class="modal-body">
						<input class="form-control" type="text" name="username"
							placeholder="Enter Username" required><br> <input
							class="form-control" type="password" name="password"
							placeholder="Enter Password" required><br> <input
							class="form-control" type="text" name="email"
							placeholder="Enter Email" required><br> <input
							class="form-control" type="text" name="nick"
							placeholder="Enter Nickname" required><br>
					</div>
					<div class="modal-footer">
						<div>
							<button class="btn btn-primary btn-lg btn-block" type="submit">Registrati</button>
						</div>
					</div>


				</form>
			</div>
		</div>
	</div>
<div class="home">
	<nav class="navbar navbar-inverse">
  		<div class="container-fluid">
    		<div class="navbar-header">
    			<a class="navbar-brand" href="#"><img src="Logo.png" style="width:150px; height:60px"></a>
    		</div>
    		<ul class="nav navbar-nav">
      			<li class="active"><a href="#">Home</a></li>
    		</ul>
    		<ul class="nav navbar-nav navbar-right">
      			<li><a id="loginbtn" href="#"><span class="glyphicon glyphicon-log-in"></span> Entra</a></li>
    		</ul>
  		</div>
	</nav>
	<div class="logo-center">
		<img src="Logo.png">
		<p style="color:white;">“Un social network nato molto prima dei social network”</p>
		<button style="margin-top:20px;" type="button" class="btn btn-primary btn-lg" id="inizia">Inizia a giocare!</button>
	</div>
</div>
<div class="about">
	<div class="col-md-12" style="text-align:center;">
		<h2 style="color:#202020" class="section-main-title">ABOUT FANTABET</h2>
		<img src="Logo.png" style="width:40px; margin:20px;">
	</div>
	<div class="col-md-10 col-md-offset-1" style="text-align:center;">
		<p> Il fantacalcio è un gioco fantasy sport sul calcio, consistente nell'organizzare e gestire squadre virtuali formate
			da calciatori reali, scelti fra quelli che giocano il torneo cui il gioco si riferisce.
			Fu inventato da Riccardo Albini che, ispirandosi a un passatempo USA basato sul baseball 
			lo pubblicò per la prima volta in Italia nel 1990 tramite le Edizioni Studio Vit.
			FantaBet riprende lo spirito e le regole del fantacalcio per creare competizioni amichevoli o globali. Sfida ora i tuoi amici!</p>
	</div>
</div>
<button id="myBtn" title="Go to top"><span class="glyphicon glyphicon-hand-up"></span></button>


	<script type="text/javascript" src="script.js"></script>
</body>
</html>