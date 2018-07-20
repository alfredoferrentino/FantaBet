$('#modifica').click(function () {
	if ($('#pass').val() != $('#pass-conf').val()) {
		$('.modal-header').append('<p style="color:red;">Le password devono coincidere</p>');
		setTimeout(function(){
			window.location.reload(); }, 3000);
		return;
	}
	else {
		
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "/FantaBet/UserControl?action=modifica&nickname="+$('#nick').val()+"&password="+$('#pass').val()+"&email="+$('#mail').val()+"&username="+$('#user').val(), true);
	xhttp.send();
	$('.modal-header').append('<p style="color:red;">I dati sono stati cambiati</p>');
	setTimeout(function(){
		window.location.replace("/FantaBet/profilo.jsp"); }, 3000);
	}
});