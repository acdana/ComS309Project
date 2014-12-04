//javascript for chat abilities

//function used to get the credentials of the current user based on cookie
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

//function to get the username of the current user
function getUsername() {
	var username = getCredentials().substr(0, getCredentials().indexOf(':'));
	return username.substr(username.indexOf(' ')+1);
}

//helper method to parse URL parameters for us
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




var Chat = {};

Chat.socket = null;

// compatability type checks
Chat.connect = (function(host) {
	if ("WebSocket" in window) {
		Chat.socket = new WebSocket(host);
	} else if ("MozWebSocket" in window) {
		Chat.socket = new MozWebSocket(host);
	} else {
		Console.log("Error");
		return;
	}

	// when a connection is made
	Chat.socket.onopen = function() {
		document.getElementById("chat").onkeydown = function(event) {
			if (event.keyCode == 13) {
				Chat.sendMessage();
			}
		};
	};

	// when a connection closes
	Chat.socket.onclose = function() {
		document.getElementById("chat").onkeydown = null;
		Console.log("Dissconnected");
	};

	// when a message is submitted
	Chat.socket.onmessage = function(message) {
		Console.log(message.data);
	};
});

// endpoint initialization
Chat.initialize = function() {
	// incase we have https
	if (window.location.protocol == "http:") {
		Chat.connect('ws://' + window.location.host + "/ComS309Project/chat/" + getURLParameter("saleID") + "/" + getUsername());
	} else {
		Chat.connect('wss://' + window.location.host + "/ComS309Project/chat/" + getURLParameter("saleID") + "/" + getUsername());
	}
};
// when a messege is sent
// checks for blank messeges
Chat.sendMessage = function() {
	var message = document.getElementById("chat").value;
	if (message != '') {
		Chat.socket.send(message);
		document.getElementById("chat").value = '';
	}
};

var Console = {};

// puts words into the console and limits the logs
Console.log = (function(message) {
	var console = document.getElementById("console");

	// adds new message to console with word wrap
	var p = document.createElement('p');
	p.style.wordWrap = "break-word";
	p.innerHTML = message;
	console.appendChild(p);

	// keeps chat log small
	while (console.childNodes.length > 25) {
		console.removeChild(console.firstChild);
	}
	// puts scroll bar at bottom with each message
	console.scrollTop = console.scrollHeight;
});

Chat.initialize();