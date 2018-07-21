var id_calciatore = [];
var modulo = $('#selectmodulo').val().split("-",3);
var count_p = 0;
var count_d = 0;
var count_c = 0;
var count_a = 0;
var totale = 0;



$( "#selectcomp" ).change(function() {
	if ($('#selectcomp').val() != "") {
		$('.button-group').show();
		$('.contenitore-classifica').load('competizioni.jsp .contenitore-classifica');
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET", "/FantaBet/ClassificaCompetizioneServlet?action=classifica-comp&nome_competizione="+$('#selectcomp').val(), true);
		xhttp.send();
		setTimeout(function(){
			$('.contenitore-classifica').load('competizioni.jsp .contenitore-classifica'); }, 1000);
		$('.form-display').hide();
		$('.visualizza_formazione').hide();
		$('.lista').show();
	}
	else  { 
		$('.button-group').hide();
		$('.form-display').hide();
		$('.lista').hide();
		$('.visualizza_formazione').hide();
	}
});

$('#send').click(function () {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if(xhttp.readyState == 4) {
			if(xhttp.status == 200) {
				$('.modal-header').append('<p style="color:red;">Competizione richiesta. Attendi che un amministratore la approvi</p>');
			}
			else {
				$('.modal-header').append('<p style="color:red;">Nome Competizione già esistente. Inseriscine un altro</p>');
				
			}
		}
	}
	xhttp.open("GET", "/FantaBet/RichiestaServlet?action=inserisci-comp&nome_competizione="+$('#form1').val()+"&num_giornate="+$('#form2').val()+"&num_partecipanti="+$('#form3').val(), true);
	xhttp.send();
	setTimeout(function(){
		window.location.reload(); }, 3000);
});

$('#inserisci-form').click(function () {
	$('.lista').hide();
	$('.visualizza_formazione').hide();
	$('.form-display').show();
	
});
$('#p').click(function () {
	$('.list-group').load('competizioni.jsp .list-group');
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "/FantaBet/ScegliFormazioneServlet?action=load&ruolo=p", true);
	xhttp.send();
	setTimeout(function() {
		$('.list-group').load('competizioni.jsp .list-group').fadeIn(3000); }, 1000);
});

$('#d').click(function () {
	$('.list-group').load('competizioni.jsp .list-group');
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "/FantaBet/ScegliFormazioneServlet?action=load&ruolo=d", true);
	xhttp.send();
	setTimeout(function() {
		$('.list-group').load('competizioni.jsp .list-group'); }, 1000);
});

$('#c').click(function () {
	$('.list-group').load('competizioni.jsp .list-group');
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "/FantaBet/ScegliFormazioneServlet?action=load&ruolo=c", true);
	xhttp.send();
	setTimeout(function() {
		$('.list-group').load('competizioni.jsp .list-group'); }, 1000);
});

$('#a').click(function () {
	$('.list-group').load('competizioni.jsp .list-group');
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "/FantaBet/ScegliFormazioneServlet?action=load&ruolo=a", true);
	xhttp.send();
	setTimeout(function() {
		$('.list-group').load('competizioni.jsp .list-group'); }, 1000);
});

function aggiungi (element, nome, id, ruolo, prezzo) {
	if (id_calciatore.indexOf(id) != -1) {
		var i = id_calciatore.indexOf(id);
		id_calciatore.splice(i,1);
		totale -= prezzo;
		$('.costo').text("Costo : "+totale+" /150 Crediti");
		$("p:contains('"+nome+"')").remove();
		$(element).find("i").remove();
		if (ruolo == 'p')
			count_p -= 1;
		if (ruolo == 'd')
			count_d -= 1;
		if (ruolo == 'c')
			count_c -= 1;
		if (ruolo == 'a')
			count_a -= 1;
		return;
	}
	if (ruolo == 'p') {
		console.log("Stai selezionando i portieri e il count sta a : " + count_p);
		if (count_p == 1) {
			$('.alert').html('<p style="color:red">Hai inserito il numero massimo di portieri</p>');
			setTimeout(function() {
				$('.alert').empty(); }, 2000);
			return;
		}
		else count_p += 1;
	}
	if (ruolo == 'd') {
		console.log("Stai selezionando i difensori e il count sta a : " + count_d);
		if (count_d == Object.values(modulo)[0]) {
			$('.alert').html('<p style="color:red">Hai inserito il numero massimo di difensori</p>');
			setTimeout(function() {
				$('.alert').empty(); }, 2000);
			return;
		}
		else count_d += 1;
	}
	if (ruolo == 'c') {
		console.log("Stai selezionando i centrocampisti e il count sta a : " + count_c);
		if (count_c == Object.values(modulo)[1]) {
			$('.alert').html('<p style="color:red">Hai inserito il numero massimo di centrocampisti</p>');
			setTimeout(function() {
				$('.alert').empty(); }, 2000);
			return;
		}
	else count_c += 1;
	}
	if (ruolo == 'a') {
		console.log("Stai selezionando gli attaccanti e il count sta a : " + count_a);
		if (count_a == Object.values(modulo)[2]) {
			$('.alert').html('<p style="color:red">Hai inserito il numero massimo di attaccanti</p>');
			setTimeout(function() {
				$('.alert').empty(); }, 2000);
			return;
		}
	else count_a += 1;
	}
	id_calciatore.push(id);
	console.log(id_calciatore);
	totale += prezzo;
	$('.giocatori').append('<p>'+nome+'</p>');
	$('.costo').text("Costo : "+totale+" /150 Crediti");
	console.log($('.costo').text());
	$(element).append('      <i class="fas fa-check"></i>');
}
$( "#selectmodulo" ).change(function() {
	count_p=0;
	count_d=0;
	count_c=0;
	count_a=0;
	modulo = ($('#selectmodulo').val().split("-",3));
	id_calciatore=[];
	$('.giocatori').empty();
	totale = 0;
	$('.costo').text("Costo : "+totale+" /150 Crediti");
	$('.fa-check').remove();
	console.log("ciao");
});

$('#insert-form').click(function () {
	if (totale > 150) {
		$('.alert').html('<p style="color:red">Il costo supera la somma massima consentita</p>');
		setTimeout(function() {
			$('.alert').empty(); }, 2000);
		return;
	}
	if (id_calciatore.length < 11) {
		$('.alert').html('<p style="color:red">Numero di giocatori non sufficiente</p>');
		setTimeout(function() {
			$('.alert').empty(); }, 2000);
		return;
	}
		
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (xhttp.status == 405) {
			$('.alert').html('<p style="color:red; font-size:15px;">Hai già inserito la formazione per questa giornata</p>');
				setTimeout(function() {
					$('.alert').empty(); }, 2000);
			return;
		}
	}
	xhttp.open("GET", "/FantaBet/InserimentoFormazioneServlet?action=insert-form&calciatori="+id_calciatore, true);
	xhttp.send();
	$('.alert').html('<p style="color:red">La tua formazione è stata registrata con successo</p>');
	setTimeout(function() {
		$('.alert').empty(); }, 2000);
}); 

$('#visualizza-form').click(function () {
	$('.lista').hide();
	$('.form-display').hide();
	$('.visualizza_formazione').show();
	
});

$('#add').click(function () {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if(xhttp.readyState == 4) {
			if(xhttp.status == 200) {
				$('.modal-header').append('<p style="color:red;">Giocatore : ' +$("#form4").val() + ' aggiunto </p>');
			}
			else {
				$('.modal-header').append('<p style="color:red;">Giocatore : ' +$("#form4").val() + ' inesistente </p>');
			}
		}
	}
	xhttp.open("GET", "/FantaBet/AggiungiPartecipante?action=aggiungi-part&nome_giocatore="+$('#form4').val()+"&nome_competizione="+$('#selectcomp').val(), true);
	xhttp.send();
	setTimeout(function(){
		$('.modal-header').empty(); }, 2000);
});