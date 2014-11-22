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


//common functions for this and other files
//selection of either mozilla or normal websocket
moziSock = function(sock, host) {
	if ("WebSocket" in window) {
		sock.socket = new WebSocket(host);
	} else if ("MozWebSocket" in window) {
		sock.socket = new MozWebSocket(host);
	} else {
		return;
	}
};

// use either ws or wss connection
var proto = function(sock) {
	if (window.location.protocol == "http:") {
		sock.connect('ws://' + window.location.host + "/ComS309Project/map/" + getURLParameter("saleID"));
	} else {
		sock.connect('wss://' + window.location.host + "/ComS309Project/map/" + getURLParameter("saleID"));
	}
};

// the websocket
var locationBut = {};
locationBut.socket = null;

// for the button listeners
var Yours = document.getElementById("yoursBut");
var Other = document.getElementById("otherBut");

// where the strings get put
var YourStar = document.getElementById("yourStar");
var OtherStar = document.getElementById("otherStar");

// initialization for the strings to inform users what buttons have been
// selected by both users
var yStr = "--", oStr = "--";

// function that takes the strings yStr and oStr and makes them viewable on the
// html page
var htmlChangeYO = function(yStr, oStr) {
	var yp = document.createElement('p');
	var op = document.createElement('p');
	yp.innerHTML = yStr;
	op.innerHTML = oStr;

	YourStar.removeChild(YourStar.firstChild);
	OtherStar.removeChild(OtherStar.firstChild);

	YourStar.appendChild(yp);
	OtherStar.appendChild(op);

}

// initial connection
locationBut.connect = function(host) {
	moziSock(locationBut, host);

	// when connected
	locationBut.socket.onopen = function() {
		// when you press your or other button, left dash becomes a star.
		// If the other person picks their your or other button, your right dash
		// becomes a star.

		// left dash becomes a star for your button
		// will only work if this user has put down a marker
		Yours.addEventListener("click", function() {
			if (marker != null) {
				// selects this button
				if (yStr == "--") {
					yStr = "*-";

				} else if (yStr == "-*") {
					yStr = "**";
				}
				// un-selects this button
				if (oStr == "*-") {
					oStr = "--";
				} else if (oStr == "**") {
					oStr = "-*";
				}

				// sending message and updating the html page
				locationBut.socket.send("l,o");
				htmlChangeYO(yStr, oStr);
			}
		});
		// left dash becomes a star for other button
		// will only work if the other user has put down a marker
		Other.addEventListener("click", function() {
			if (mark != null) {

				// selects this buttons
				if (oStr == "--") {
					oStr = "*-";
				} else if (oStr == "-*") {
					oStr = "**";
				}
				// un-selects other button
				if (yStr == "*-") {
					yStr = "--";
				} else if (yStr == "**") {
					yStr = "-*";
				}

				// sending message and updating html page
				locationBut.socket.send("l,y");
				htmlChangeYO(yStr, oStr);
			}
		});

	};

	// when a message is submitted
	locationBut.socket.onmessage = function(message) {
		// check [0]: l for location buttons, m for the map
		var mess = message.data.split(",");
		if (mess[0] == "l") {

			// y or o informs which button other user pushed and what string
			// should be updated
			// left dash meaning this user has selected this button and right
			// dash meaning other user has selected this button
			if (mess[1] == "y") {

				// selects this button
				if (yStr == "--") {
					yStr = "-*";

				} else if (yStr == "*-") {
					yStr = "**";
				}

				// un-selects other button
				if (oStr == "-*") {
					oStr = "--";
				} else if (oStr == "**") {
					oStr = "*-";
				}
			} else if (mess[1] == "o") {

				// selects this buttons
				if (oStr == "--") {
					oStr = "-*";
				} else if (oStr == "*-") {
					oStr = "**";
				}
				// un-selects this button
				if (yStr == "-*") {
					yStr = "--";
				} else if (yStr == "**") {
					yStr = "*-";
				}
			}
			htmlChangeYO(yStr, oStr);
		}

	};
};

proto(locationBut);
