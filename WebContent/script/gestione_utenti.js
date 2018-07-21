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