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
		sock.connect('ws://' + window.location.host + "/ComS309Project/map");
	} else {
		sock.connect('wss://' + window.location.host + "/ComS309Project/map");
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
var htmlChange = function(yStr, oStr) {
	var yp = document.createElement('p');
	var op = document.createElement('p');
	yp.innerHTML = yStr;
	op.innerHTML = oStr;

	yourStar.removeChild(yourStar.firstChild);
	otherStar.removeChild(otherStar.firstChild);

	yourStar.appendChild(yp);
	otherStar.appendChild(op);

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
		Yours.addEventListener("click", function() {
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
			htmlChange(yStr, oStr);
		});
		// left dash becomes a star for other button
		Other.addEventListener("click", function() {
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
			htmlChange(yStr, oStr);
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
			htmlChange(yStr, oStr);
		}

	};
};

proto(locationBut);
