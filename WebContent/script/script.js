var login = document.getElementById('loginbtn');
var logmodal = document.getElementById('loginmodal');
var closelog =document.getElementById('closemodal');
var register=document.getElementById('registerbtn');
var regmodal=document.getElementById('registermodal')
var closereg=document.getElementById('closeregistermodal');
var gioca=document.getElementById('inizia');

login.onclick = function() {
    logmodal.style.display = "block";
}
closelog.onclick = function() {
	logmodal.style.display="none";
}
register.onclick = function() {
	logmodal.style.display="none";
	regmodal.style.display ="block";
	
}
closereg.onclick = function () {
	regmodal.style.display="none";
}
gioca.onclick = function () {
	logmodal.style.display="block";
}

window.onscroll = function () {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("myBtn").style.display = "block";
    } else {
        document.getElementById("myBtn").style.display = "none";
    }
}

document.getElementById("myBtn").onclick = function () {
	 document.body.scrollTop = 0;
	 document.documentElement.scrollTop = 0;
}

$(document).ready(function() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (xhttp.status == 405) {
			console.log("stai accis");
		}
	}
});

