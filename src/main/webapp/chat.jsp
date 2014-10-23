<!-- javascript for the instant chat -->
<html>
<head>
<!-- simple temp css to make the chat more readable -->
<style type="text/css">
input#chat {
	width: 410px
}

#console-container {
	width: 400px;
}

#console {
	border: 1px solid #000000;
	height: 170px;
	overflow-y: scroll;
	padding: 5px;
	width: 100%;
}

#console p {
	padding: 0;
	margin: 0;
}
</style>
<script type="text/javascript">
	var Chat = {};

	Chat.socket = null;

	//compatability type checks	
	Chat.connect = (function(host) {
		if ("WebSocket" in window) {
			Chat.socket = new WebSocket(host);
		} else if ("MozWebSocket" in window) {
			Chat.socket = new MozWebSocket(host);
		} else {
			Console.log("Error");
			return;
		}

		//when a connection is made
		Chat.socket.onopen = function() {
			document.getElementById("chat").onkeydown = function(event) {
				if (event.keyCode == 13) {
					Chat.sendMessage();
				}
			};
		};

		//when a connection closes
		Chat.socket.onclose = function() {
			document.getElementById("chat").onkeydown = null;
			Console.log("Dissconnected");
		};

		//when a message is submitted
		Chat.socket.onmessage = function(message) {
			Console.log(message.data);
		};
	});

	//endpoint initialization
	Chat.initialize = function() {
		//incase we have https
		if (window.location.protocol == "http:") {
			Chat.connect('ws://' + window.location.host
					+ "/ComS309Project/chat");
		} else {
			Chat.connect('wss://' + window.location.host
					+ "/ComS309Project/chat");
		}
	};
	//when a messege is sent
	// checks for blank messeges
	Chat.sendMessage = function() {
		var message = document.getElementById("chat").value;
		if (message != '') {
			Chat.socket.send(message);
			document.getElementById("chat").value = '';
		}
	};

	var Console = {};

	//puts words into the console and limits the logs
	Console.log = (function(message) {
		var console = document.getElementById("console");

		//adds new message to console with word wrap
		var p = document.createElement('p');
		p.style.wordWrap = "break-word";
		p.innerHTML = message;
		console.appendChild(p);

		//keeps chat log small
		while (console.childNodes.length > 25) {
			console.removeChild(console.firstChild);
		}
		//puts scroll bar at bottom with each message
		console.scrollTop = console.scrollHeight;
	});

	Chat.initialize();
</script>
</head>
<body>

	<div>
		<p>
			<input type="text" placeholder="type and press enter to chat"
				id="chat" />
		</p>
		<div id="console-container">
			<div id="console"></div>
		</div>
	</div>
</body>
</html>