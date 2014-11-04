
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%--brings in the map--%>
<jsp:include page="map.jsp"/>
<%--brings in the chat--%>
<jsp:include page="chat.jsp"/>
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
	
 --%>

</body>
</html>