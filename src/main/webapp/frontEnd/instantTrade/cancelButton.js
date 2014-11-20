// javascript for redirecting users when cancel button is pressed

// the socket
var Cancel = {};
Cancel.socket = null;

// button for listener
var cancel = document.getElementById("cancel");

// initial connection
Cancel.connect = (function(host) {
	// function from locationButtons
	moziSock(Cancel, host);

	
	Cancel.socket.onopen = function() {
		//checks for cancel button click
		cancel.addEventListener("click", function() {

			// adds tag c for cancel
			message = "c";
			//informs other user trade has been canceled and makes them redirect to their profile page
			Cancel.socket.send(message);
			//redirects this user to his profile page
			window.location.href = '../../frontEnd/profilePage/index.html?username='+ getUsername();
			
		});

	};

	// when a message is recieved
	Cancel.socket.onmessage = function(message) {
		// makes a string and splits at ,
		var msgArr = message.data.split(",");

		// checks this message is for here (should be a c if it is)
		if (msgArr[0] == "c") {
			//redirects this user to his profile page
			window.location.href = '../../frontEnd/profilePage/index.html?username='+ getUsername();
		}
	};

});

// function from locationButtons
proto(Cancel);
