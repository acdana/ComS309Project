
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Instant Trade</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="../include/credentials.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> 
    <script> 
    $(function(){
      $("#navbar").load("../include/navbar.html"); 
    });
	</script>
	 <div id="navbar"></div>
<style>
#left {
	float: left;
	padding: 10px;
}

#right {
	float: left;
	width: 150px;
}

#rightright {
	float: left;
}

#yours {
	padding: 10px;
}

#yoursPos {
	padding: 10px;
	width: 100px;
	border: 1px solid #000000;
	width: 100px;
}

#other {
	padding: 10px;
}

#otherPos {
	padding: 10px;
	width: 100px;
	border: 1px solid #000000;
}

input#items {
	width: 400px
}

#console-container {
	width: 392px;
}

#otherTradeBox {
	border: 1px solid #000000;
	height: 170px;
	overflow-y: scroll;
	padding: 5px;
	width: 400px;
}

#tradeBox {
	border: 1px solid #000000;
	height: 170px;
	overflow-y: scroll;
	padding: 5px;
	width: 400px;
}

#console p {
	padding: 0;
	margin: 0;
}
</style>

</head>
<body>

	<div id="left">
		<%--brings in the map
--%>
		<jsp:include page="map.jsp" />

		<%--brings in the chat
--%>
		<jsp:include page="chat.jsp" />
	</div>




	<%--
things needed

-final agree redirect
-cancel trade redirect
 --%>
	<div id="right">
		<div id="yours">
			<button id="yoursBut">Your location</button>
		</div>
		<p id="yourStar">--</p>
		<div id="yoursPos"></div>
		<div id="other">
			<br>
			<button id="otherBut">Other location</button>
		</div>
		<p id="otherStar">--</p>
		<div id="otherPos"></div>
		<br> <br>

		<button id="fAgree">Final Agree</button>
		<p id="fStar">--</p>
		<br>

		<button id="cancel">Cancel trade</button>

	</div>

	<%--chats but able to remove items --%>
	<div id="rightright">
		<div>
			<p>
				<input type="text" placeholder="type and press enter to add an item"
					id="items" />
			</p>
			<select id="tradeBox" size="2">
			</select>
		</div>
		<br>
		<button id="remove">Remove Trade</button>
		<br> <br>
		<div>
			Trading for: <br> <select id="otherTradeBox" size="2">
			</select> <br> <br>
			<button id="agree">Agree to trade</button>
			<p id="aStar">--</p>

		</div>
	</div>
	<%--idk what this does yet.
--%>
	<script src="locationButtons.js"></script>
	<script src="trade.js"></script>
	<script src="agreeButtons.js"></script>
	<script src="cancelButton.js"></script>
    <script src="../include/credentials.js"></script>

</body>
</html>