
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Instant Trade</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="../include/credentials.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> 
    <script src="suggestedLocation.js"></script>
    
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?libraries=places"></script>
    
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
	padding-right: 20px;
}

#rightright {
	float: left;
	padding-left: 30px;
}

#yours {
	padding: 10px;
}

#other {
	padding: 10px;
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

#landmarkDD{
	padding-bottom: 10px;
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
		<div id="landmarkDD">
			<select id="locSelect">
				<option value="base">Choose a Location</option>
				<option value="MU">Memorial Union</option>
				<option value="musicHall">Music Hall</option>
				<option value="carver">Carver Hall</option>
				<option value="beardshear">Beardshear Hall</option>
				<option value="udcc">Union Drive Marketplace</option>
				<option value="stateGym">State Gymnasium</option>
				<option value="library">Parks Library</option>
				<option value="coover">Coover Hall</option>
				<option value="lied">Lied Rec Center</option>
				<option value="curtiss">Curtiss Hall</option>
			</select>
        </div>
		</div>
		<div id="yoursPos" style="display: none;"></div>
		<div id="other">
			<button id="otherBut">Other location</button>
		</div>
		<p id="otherStar">--</p>
		<div id="otherPos" style="display: none;"></div>
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
				<h5 style="font-weight: bold; font-size:13px;">Don't forget to decide a meeting time with your trading partner!</h5>
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
	
	
	<button id="suggestedLocationButton">Suggest Location</button>
	<div id="suggestDiv"></div>
	
	<%--idk what this does yet.
--%>
	<script src="locationButtons.js"></script>
	<script src="suggestedLocation.js"></script>
	<script src="trade.js"></script>
	<script src="agreeButtons.js"></script>
	<script src="cancelButton.js"></script>
    <script src="../include/credentials.js"></script>
    

</body>
</html>