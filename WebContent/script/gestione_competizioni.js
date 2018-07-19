var input = document.getElementById("search");

function remove(element, id) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (xhttp.status == 200) {
				setTimeout(function() {
					$(element).parent().remove(); }, 2000);
		}
	}
	xhttp.open("GET", "/FantaBet/AdminControl?action=remove-comp&idComp="+id, true);
	xhttp.send();
	
}

input.addEventListener("keyup", function(event) {
	  // Cancel the default action, if needed
	  event.preventDefault();
	  // Number 13 is the "Enter" key on the keyboard
	  if (event.keyCode === 13) {
	    // Trigger the button element with a click
	   console.log("ho cliccato invio su :  " + $('.form-control').val());
	   window.location.href = "/FantaBet/AdminControl?action=search-comp&nome="+$('.form-control').val();
	 }
});

function show(el, nome, partecipanti, giornate) {
	console.log($(el).parent(), nome, partecipanti, giornate);
	$('.modal-title').text("Competzione " + nome );
	$('.nome').text(nome );
	$('.partecipanti').text(partecipanti );
	$('.giornate').text(giornate );
}

function mostraCalciatori () {
	console.log("setto approved");
	localStorage.setItem('show', 'approved'); //store state in localStorage
}
$(document).ready(function(){
    var show = localStorage.getItem('show');
    if(show === 'approved'){
    	console.log('mostro voti');
        $('.lista').hide();
        $('.approva').hide();
        $('.voti').show();
    }
    else if(show === 'disapproved'){
    	console.log('mostro approva');
        $('.lista').hide();
        $('.voti').hide();
        $('.approva').show();
    }
    localStorage.removeItem('show');
});

function insertVoto (elemento) {
	var el = $(elemento).parent();
	window.location.href = "/FantaBet/AdminControl?action=insert-voto&idCalc="+el.children('#idCalc').val()+"&votazione="+el.children('#voto').val()
	+"&gol="+el.children('#goal').val()+"&assist="+el.children('#assist').val()+"&ammonizione="+el.children('#ammon').is(":checked")+"&espulsione="+el.children('#espuls').is(":checked");
}

function mostraApprovate () {
	console.log("setto disapproved");
	localStorage.setItem('show', 'disapproved');
}

function approvaVoti (el) {
	console.log(el);
	window.location.href = "/FantaBet/AdminControl?action=approvate&idComp="+el;
}

