var login = document.getElementById('loginbtn');
var logmodal = document.getElementById('loginmodal');
var closelog =document.getElementById('closemodal');
var register=document.getElementById('registerbtn');
var regmodal=document.getElementById('registermodal')
var closereg=document.getElementById('closeregistermodal');
var gioca=document.getElementById('inizia');
var top =document.getElementById('myBtn');

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

top.onclick = function () {
	 document.body.scrollTop = 0;
	 document.documentElement.scrollTop = 0;
}