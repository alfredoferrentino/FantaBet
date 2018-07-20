var input = document.getElementById('searchUser');

function mostra(el, nome, partecipanti, giornate) {
	console.log($(el).parent(), nome, partecipanti, giornate);
	$('.modal-title').text("Competizione " + nome );
	$('.nome').text(nome );
	$('.partecipanti').text(partecipanti );
	$('.giornate').text(giornate );
}

function modifica(e, username, nickname, email, password) {
	$('#nick').val(nickname);
	$('#pass').val(password);
	$('#mail').val(email);
	$('#username').val(username);
}

$('#modifica').click(function () {
	if ($('#pass').val() != $('#pass-conf').val()) {
		$('.modal-header').append('<p style="color:red;">Le password devono coincidere</p>');
		setTimeout(function(){
			window.location.reload(); }, 3000);
		return;
	}
	else {
		
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "/FantaBet/ModificaUtenteServlet?action=modifica-utente&nickname="+$('#nick').val()+"&password="+$('#pass').val()+"&email="+$('#mail').val()+"&username="+$('#username').val(), true);
	xhttp.send();
	$('.modal-header').append('<p style="color:red;">I dati sono stati cambiati</p>');
	setTimeout(function(){
		window.location.replace("/FantaBet/gestione_utenti.jsp"); }, 3000);
	}
});

input.addEventListener("keyup", function(event) {
	  // Cancel the default action, if needed
	  event.preventDefault();
	  // Number 13 is the "Enter" key on the keyboard
	  if (event.keyCode === 13) {
	    // Trigger the button element with a click
	   console.log("ho cliccato invio su :  " + $('.form-control').val());
	   window.location.href = "/FantaBet/RicercaServlet?action=search-user&utente="+$('.form-control').val();
	 }
});