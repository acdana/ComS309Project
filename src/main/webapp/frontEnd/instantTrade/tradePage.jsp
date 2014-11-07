
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


	<%--idk what this does yet.
<script src="trade.js"></script>
--%>

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
			<button type="button">Your location</button>
		</div>
		<div id="yoursPos"></div>
		<div id="other">
			<button type="button">Other location</button>
		</div>
		<div id="otherPos"></div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<button type="button">Agree to trade</button>
		<br>
		<br> <br>
		<br>
		<button type="button">Final Agree</button>
		<br>
		<br>

		<button type="button">Cancel trade</button>

	</div>

	<%--basicly make them chats but able to remove items --%>
	<div id="rightright">
		<div>
			<p>
				<input type="text" placeholder="type and press enter to add an item"
					id="items" />
			</p>
			<div id="console-container">
				<div id="console"></div>
			</div>
		</div>
		<br>
		<div>
			Trading for:<br>

			<div id="console-container">
				<div id="console"></div>
			</div>
		</div>
	</div>

</body>
</html>