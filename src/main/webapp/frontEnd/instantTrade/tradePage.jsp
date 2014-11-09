
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
#left {
	float: left;
	padding: 10px;
}

#right {
	float: left;
	width: 200px;
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
-indicator of how many people are in trade
-button to agree on this or other persons picked location
	check for location changes on selected location
	check that location actually has been selected
		-hold onto selected location to give to database
-locations to add things that will be traded
-agree buttons
	check for changes in trade items
-last agree button that both location and trades are good
	check that location has been agreed to by both
	check that trades have been agreed to by both
-cancel trade
 --%>
	<div id="right">
		<div id="yours">
			<button id="yoursBut">Your location</button>
		</div>
		<p id="yourStar">--</p>
		<div id="yoursPos"></div>
		<div id="other">
			<button id="otherBut">Other location</button>
		</div>
		<p id="otherStar">--</p>
		<div id="otherPos"></div>
		<br> <br> <br> <br> <br>

		<button id="fAgree">Final Agree</button>
		<br> <br>

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
			</select> <br>
			<br>
			<button id="agree">Agree to trade</button>

		</div>
	</div>
	<%--idk what this does yet.
--%>
	<script src="locationButtons.js"></script>
	<script src="trade.js"></script>

</body>
</html>