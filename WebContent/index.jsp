<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<link rel="stylesheet" href="style/index.css">
  	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.js"></script>
	<title>FantaBet</title>
</head>
<body>
	<div class="modal" id="loginmodal">

		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<img src="icon/Logo.png" style="width: 200px; height: 150px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closemodal">
						<span title="CloseLogin">&times;</span>
					</button>
				</div>
				<form action="Login" method="post" data-toggle="validator">
					<div class="modal-body">
						<div class="form-group">
    						<label for="inputUser" class="control-label">Username</label>
    						<input pattern="^[_A-z0-9]{1,20}$"  name="username" type="text" class="form-control" id="inputUser" placeholder="Enter Username" required>
    						<div class="help-block with-errors"></div>
  						</div>
  						<div class="form-group">
    						<label for="inputPsw" class="control-label">Password</label>
        					<input name="psw" type="password" pattern="^[_A-z0-9]{6,15}$" class="form-control" id="inputPsw" placeholder="Enter Password" required>
       						 <div class="help-block with-errors"></div>
      					</div>
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
					<img src="icon/Logo.png" style="width: 200px; height: 150px;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" id="closeregistermodal">
						<span title="CloseLogin">&times;</span>
					</button>
				</div>
				<form action="Registrazione" method="post" data-toggle="validator">
					<div class="modal-body">
						<div class="form-group">
    						<label for="inputName" class="control-label">Username</label>
    						<input pattern="^[_A-z0-9]{1,20}$"  name="username" type="text" class="form-control" id="inputName" placeholder="Enter Username" required>
    						<div class="help-block with-errors">Only letters and numbers, no special characters</div>
  						</div>
  						<div class="form-group">
    						<label for="inputPassword" class="control-label">Password</label>
        					<input name="password" type="password" pattern="^[_A-z0-9]{6,15}$" class="form-control" id="inputPassword" placeholder="Enter Password" required>
       						 <div class="help-block with-errors">Minimum of 6 characters, no special characters</div>
      					</div>
      					<div class="form-group">
      						<label for="inputPasswordConfirm" class="control-label">Confirm Password</label>
        					<input type="password" class="form-control" id="inputPasswordConfirm" data-match="#inputPassword" data-match-error="Whoops, these don't match" placeholder="Confirm" required>
        					<div class="help-block with-errors"></div>
        				</div>
						<div class="form-group">
   							<label for="inputEmail">Email</label>
   							<input class="form-control" type="email" id="inputEmail" name="email" placeholder="Enter Email" required>
   							<div class="help-block with-errors"></div>
  						</div>
  						 <div class="form-group">
    						<label for="inputNick" class="control-label">Nickname</label>
    						<input pattern="^[_A-z0-9]{1,20}$" name="nick" type="text" class="form-control" id="inputNick" placeholder="Enter Nickname" required>
    						<div class="help-block with-errors"></div>
  						</div>
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
    			<a class="navbar-brand" href="#"><img src="icon/Logo.png" style="width:150px; height:60px"></a>
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
		<img src="icon/Logo.png">
		<p style="color:white;">“Un social network nato molto prima dei social network”</p>
		<button style="margin-top:20px;" type="button" class="btn btn-primary btn-lg" id="inizia">Inizia a giocare!</button>
	</div>
</div>
<div class="about">
	<div class="col-md-12" style="text-align:center;">
		<h2 style="color:#202020" class="section-main-title">About FantaBet</h2>
		<img src="icon/Logo.png" style="width:40px; margin:20px;">
	</div>
	<div class="col-md-10 col-md-offset-1" style="text-align:center;">
		<p> Il fantacalcio è un gioco fantasy sport sul calcio, consistente nell'organizzare e gestire squadre virtuali formate
			da calciatori reali, scelti fra quelli che giocano il torneo cui il gioco si riferisce.
			Fu inventato da Riccardo Albini che, ispirandosi a un passatempo USA basato sul baseball 
			lo pubblicò per la prima volta in Italia nel 1990 tramite le Edizioni Studio Vit.
			FantaBet riprende lo spirito e le regole del fantacalcio per creare competizioni amichevoli o globali. Sfida ora i tuoi amici!</p>
	</div>
	
	<div class="col-md-12" style="text-align:center;">
		<h2 style="color:#202020" class="section-main-title">Puoi giocare il tuo Fantamondiale in 2 modi</h2>
		<img src="icon/Logo.png" style="width:40px; margin:20px;">
	</div>
	<div class="col-md-12">
		<div class="leghe">
			<div class="elemento-lega">
				<div>
					<img style="width:150px; heigth:100%" src="icon/medal.png">
				</div>
				<div>
					<div class="titolo-lega">
						<img style="width:40px; height:100%" src="icon/football.png">
						<h6>Leghe Pubbliche</h6>
					</div>
					<div class="descrizione-lega">
					<span class="glyphicon glyphicon-user"></span> <h5 style="font-weight:bold; margin-left:10px"> Inserisci la tua formazione <br> e partecipa al fantamondiale.</h5>
					</div>
				</div>
			</div>
			<div class="elemento-lega">
				<div>
					<img style="width:150px; height:100%" src="icon/cup.png">
				</div>
				<div>
					<div class="titolo-lega">
						<img style="width:40px; height:100%" src="icon/football.png">
						<h6>Leghe Private</h6>
					</div>
					<div class="descrizione-lega">
					<span class="glyphicon glyphicon-user"></span> <h5 style="font-weight:bold; margin-left:10px"> Lancia una sfida ai tuoi amici, <br> Inserisci la formazione e scopri chi sarà il vincitore.</h5>
					</div>
				</div>
			</div>
		</div>
		<div class="bottone-lega">
			<button style="margin-top:20px;" type="button" class="btn btn-primary btn-lg">Scopri di più</button>
		</div>
	</div>
</div>
<button id="myBtn" title="Go to top"><span class="glyphicon glyphicon-hand-up"></span></button>


	<script type="text/javascript" src="script/script.js"></script>
</body>
</html>