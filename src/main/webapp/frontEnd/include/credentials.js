
//This function gets the credentials of a user based on a stored cookie
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

//This function logs the user out
function logout() {
	
	deleteCookie("ourCookie");
    window.location.href = '../../index.html';
    
}

//This is a helper method for logout() which clears the current cookie, used for user persistence
function deleteCookie() {
	  document.cookie = "ourCookie=Authorization=; path=/";
}

//Helper method to get the username of the currently logged in user
function getUsername() {
	var username = getCredentials().substr(0, getCredentials().indexOf(':'));
	return username.substr(username.indexOf(' ')+1);
}

//this function sets the current users username in the top right navbar
function setUsername() {
	var userText = document.getElementById("usernameDisplay")
	userText.innerText = getUsername();
}

//function that grabs URL paramaters and parses them for us
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

