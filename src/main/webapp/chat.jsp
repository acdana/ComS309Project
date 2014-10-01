<html>
<head>
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

	Chat.connect = (function(host) {
		if ("WebSocket" in window) {
			Chat.socket = new WebSocket(host);
		} else if ("MozWebSocket" in window) {
			Chat.socket = new MozWebSocket(host);
		} else {
			Console.log("Error");
			return;
		}

		Chat.socket.onopen = function() {
			Console.log("Connected");
			document.getElementById("chat").onkeydown = function(event) {
				if (event.keyCode == 13) {
					Chat.sendMessage();
				}
			};
		};

		Chat.socket.onclose = function() {
			document.getElementById("chat").onkeydown = null;
			Console.log("Dissconnected");
		};

		Chat.socket.onmessage = function(message) {
			Console.log(message.data);
		};
	});

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

	Chat.sendMessage = (function() {
		var message = document.getElementById("chat").value;
		if (message != '') {
			Chat.socket.send(message);
			document.getElementById("chat").value = '';
		}
	});

	var Console = {};

	Console.log = (function(message) {
		var console = document.getElementById("console");
		var p = document.createElement('p');
		p.style.wordWrap = "break-word";
		p.innerHTML = message;
		console.appendChild(p);
		while (console.childNodes.length > 25) {
			console.removeChild(console.firstChild);
		}
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