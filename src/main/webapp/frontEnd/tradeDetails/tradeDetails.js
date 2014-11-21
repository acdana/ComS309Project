window.onload = function(){
	setSaleInfo();
}



function getURLParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}

function logout() {
	
	deleteCookie("ourCookie");
    window.location.href = '../../index.html';
    
}

function deleteCookie() {
	  document.cookie = "ourCookie=Authorization=; path=/";
	}
	
	
	
	
function getCredentials() {
	   var authorization = "Authorization=Basic ";
	   var idx = document.cookie.indexOf(authorization)

	   if (idx != -1) {
	       var end = document.cookie.indexOf(";", idx + 1);
	       if (end == -1) end = document.cookie.length;
	       return unescape(document.cookie.substring(idx + authorization.length, end));
	   } else {
	       return "";
	  }
}

function getUsername() {
	var username = getCredentials().substr(0, getCredentials().indexOf(':'));
	return username.substr(username.indexOf(' ')+1);
}

function setUsername() {
	var userText = document.getElementById("logout")
	userText.innerText = getUsername();
	userText.style.textAlign="right";
}

function setSaleInfo() {
	var saleID = document.getElementById("saleID");
	saleID.innerText = "Sale ID: " + getURLParameter("saleID");
	var saleDescription = document.getElementById("saleDescription");
	saleDescription.innerText = "Sale Description: " + unescape(getURLParameter("saleDescription"));
	var sellerName = document.getElementById("traderName");
	sellerName.innerText = "Trader: " + unescape(getURLParameter("saleSeller"));	
}

