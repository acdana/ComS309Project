// javascript for chat abilities

//the socket
var Trade = {};
Trade.socket = null;

// holds the messages
var tradeBox = {};
var otherTradeBox = {};

// button for listener
var remove = document.getElementById("remove");
// what new options get put into
var tradeBox = document.getElementById("tradeBox");
var otherTradeBox = document.getElementById("otherTradeBox");

// initial connection
Trade.connect = (function(host) {
	// function from locationButtons
	moziSock(Trade, host);

	// when a connected
	Trade.socket.onopen = function() {
		document.getElementById("items").onkeydown = function(event) {
			// when enter is hit
			if (event.keyCode == 13) {
				// takes string from items box
				var message = document.getElementById("items").value;
				// check for blanks
				if (message != '') {

					// removes agreement if an item is added
					aStr = "--";
					htmlChange(aStr, aStar);
					// resets final agree
					fStr = "--";
					htmlChange(fStr, fStar);

					// makes a new option from the string and adds it to the
					// selection box
					var opt = document.createElement('option');
					opt.text = message;
					tradeBox.appendChild(opt);

					// adds tags, t for trade and a for add and sends the item
					message = "t,a," + message;
					Trade.socket.send(message);

					// sets items box back to blank
					document.getElementById("items").value = '';

				}
			}
		};

		// when the remove button is clicked
		remove.addEventListener("click", function() {
			if (tradeBox.selectedIndex >= 0) {
				// adds tags t for trade and r for remove and the index of the
				// removed item
				var rmv = "t,r," + tradeBox.selectedIndex;

				// sends the message
				Trade.socket.send(rmv);

				// removes agreement if an item is removed
				aStr = "--";
				htmlChange(aStr, aStar);
				// resets final agree
				fStr = "--";
				htmlChange(fStr, fStar);

				// removes this item from the select box
				tradeBox.remove(tradeBox.selectedIndex);
			}

		});

	};

	// when a message is recieved
	Trade.socket.onmessage = function(message) {
		// makes a string and splits at ,
		var msgArr = message.data.split(",");

		// checks this message is for here (should be a t if it is)
		if (msgArr[0] == "t") {

			// removes agreement if other person changes his trade
			aStr = "--";
			htmlChange(aStr, aStar);
			// resets final agree
			fStr = "--";
			htmlChange(fStr, fStar);

			// checks if this is for adding an option or removing an option
			if (msgArr[1] == "a") {

				// adds option to the other selection box
				var opt = document.createElement('option');
				opt.text = msgArr[2];
				otherTradeBox.appendChild(opt);

			} else if (msgArr[1] == "r") {
				// removes option from other selection box
				otherTradeBox.remove(msgArr[2]);

			}
		}
	};

	// when a connection closes
	Trade.socket.onclose = function() {
		document.getElementById("items").onkeydown = null;
	};

});

// function from locationButtons
proto(Trade);
