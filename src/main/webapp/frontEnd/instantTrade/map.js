//javascript for map

//initialization for use outside this file
var marker = null;
var mark = null;
var map = null;
var suggestedMarker = null;

//helper method to parse URL parameters for us
function getURLParameter(sParam) {
	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');
	for (var i = 0; i < sURLVariables.length; i++) {
		var sParameterName = sURLVariables[i].split('=');
		if (sParameterName[0] == sParam) {
			return sParameterName[1];
		}
	}
}

function init() {
	// marks between users
	var othMark = {};
	othMark.socket = null;

	// Variables for the chooseLM function
	var ls = document.getElementById("locSelect");

	// for displaying location of both markers
	var yoursPos = document.getElementById("yoursPos");
	var otherPos = document.getElementById("otherPos");

	var maxZoomOut = 14;

	//creation of the map object
	map = new google.maps.Map(document.getElementById('map'), {
		zoom : maxZoomOut,
		center : new google.maps.LatLng(42.026652, -93.646420),
		mapTypeId : google.maps.MapTypeId.ROADMAP
	});

	// make new marker or change marker position
	function setMarker(mar, fixPos) {
		if (mar) {
			//resets the current marker
			mar.setPosition(fixPos);
		} else {
			//makes a new marker
			mar = new google.maps.Marker({
				position : fixPos,
				map : map

			});
		}
		return mar;
	}

	//if an object is selected in the drop down menu, the fucntino is activated.
	ls.addEventListener('change', chooseLM);

	// If a landmark is selected in the dropdown menu, place the marker
	// on the cordinates for the landmark.
	function chooseLM() {
		//gets the value of the drop down selection
		var val = ls.options[ls.selectedIndex].value;
		//initializing eventual position holder of drop down selection
		var pos = null;
		
		if (val == "MU") {
			// MU's coordinates on map
			pos = new google.maps.LatLng(42.02348260903262, -93.64573359489441);
		} else if (val == "musicHall") {
			// Music Hall's coordinates
			pos = new google.maps.LatLng(42.024614358276004, -93.64817440509796);

		} else if (val == "carver") {
			// Carver Hall's coordinates
			pos = new google.maps.LatLng(42.02522007494668, -93.64832729101181);

		} else if (val == "beardshear") {
			// Beardshear Hall's coordinates
			pos = new google.maps.LatLng(42.02614457873918, -93.64842116832733);

		}else if (val == "udcc") {
			// UDCC's coordinates
			pos = new google.maps.LatLng(42.0249251872884, -93.65148961544037);
		}else if (val == "stateGym") {
			// State Gym's coordinates
			pos = new google.maps.LatLng(42.025032781592465, -93.65390628576279);
		}else if (val == "library") {
			// Library's coordinates
			pos = new google.maps.LatLng(42.028104123687534, -93.64865250885487);
		}else if (val == "coover") {
			// Coover Hall's coordinates
			pos = new google.maps.LatLng(42.028340221365696, -93.65134209394455);
		}else if (val == "lied") {
			// Lied Red Center coordinates
			pos = new google.maps.LatLng(42.02676622028182, -93.63777816295624);
		}else if (val == "curtiss") {
			// Curtiss Hall's coordinates
			pos = new google.maps.LatLng(42.02615852589172, -93.64484712481499);
		}
		// if something has been selected the marker will be moved and info sent
		// to other user
		if (pos) {
			changeMarker(pos);
		}
	}

	function changeMarker(pos) {
		// force marker into boundrys
		var fixPos = posFix(pos);

		// change marker pos or make new marker
		marker = setMarker(marker, fixPos);

		// resets final agree if this location was selected
		if (yStr == "**") {
			fStr = "--";
			htmlChange(fStr, fStar);
		}

		// resets the selection under your button if change
		// happens
		yStr = "--";
		htmlChange(yStr, YourStar);
		var p = document.createElement('p');
		var msg = marker.getPosition().lat() + "," + marker.getPosition().lng();
		p.innerHTML = msg;

		// removes old location information
		if (yoursPos.childNodes.length > 0) {
			yoursPos.removeChild(yoursPos.firstChild);
		}

		// adds new locations data
		var childP = yoursPos.appendChild(p);
		childP.id = "yourPos";
		msg = "m," + msg;
		othMark.socket.send(msg);
	}

	// pos is checked for being in boundrys
	// new lnglat returned with fixed cords
	function posFix(pos) {
		var x = pos.lng();
		var y = pos.lat();

		// the boundrys
		var minX = boundry.getSouthWest().lng();
		var minY = boundry.getSouthWest().lat();
		var maxX = boundry.getNorthEast().lng();
		var maxY = boundry.getNorthEast().lat();

		// correcting position
		if (x < minX) {
			x = minX;
		}
		if (x > maxX) {
			x = maxX;
		}
		if (y < minY) {
			y = minY;
		}
		if (y > maxY) {
			y = maxY;
		}

		// makes a new latlng object with corrected cords
		var newPos = new google.maps.LatLng(y, x);
		return newPos;
	}

	// compatability type checks
	othMark.connect = function(host) {
		if ("WebSocket" in window) {
			othMark.socket = new WebSocket(host);
		} else if ("MozWebSocket" in window) {
			othMark.socket = new MozWebSocket(host);
		} else {
			return;
		}

		// when a connection is made
		othMark.socket.onopen = function() {
			// listens for double click to create a marker
			google.maps.event.addListener(map, 'dblclick',
					function(clickLatLng) {
						// force marker into boundrys
						var fixPos = posFix(clickLatLng.latLng);

						// change marker pos or make new marker
						marker = setMarker(marker, fixPos);

						// resets final agree if this location was selected
						if (yStr == "**") {
							fStr = "--";
							htmlChange(fStr, fStar);
						}
						// resets the selection under your button if change
						// happens
						yStr = "--";
						htmlChange(yStr, YourStar);

						// creates message to send
						var msg = marker.getPosition().lat() + ","
								+ marker.getPosition().lng();

						// takes msg and makes it into html displayable
						var p = document.createElement('p');
						p.style.wordWrap = "break-word";
						p.innerHTML = msg;

						// removes old location information
						if (yoursPos.childNodes.length > 0) {
							yoursPos.removeChild(yoursPos.firstChild);
						}
						// adds new locations data
						var childP = yoursPos.appendChild(p);
						childP.id = "yourPos";
						msg = "m," + msg;
						othMark.socket.send(msg);
					});
		};

		// when a message is submitted
		othMark.socket.onmessage = function(message) {
			var arrStr = message.data.split(",");
			if (arrStr[0] == "m") {
				var y = parseFloat(arrStr[1]);
				var x = parseFloat(arrStr[2]);

				// makes a string out of location info
				msg = arrStr[1] + "," + arrStr[2];
				// make msg html displayable
				var p = document.createElement('p');
				p.style.wordWrap = "break-word";
				p.innerHTML = msg;

				// removes old location data
				if (otherPos.childNodes.length > 0) {
					otherPos.removeChild(otherPos.firstChild);
				}

				if (oStr == "**") {
					fStr = "--";
					htmlChange(fStr, fStar);
				}
				// resets the selection under other button if change
				// happens
				oStr = "--";
				htmlChange(oStr, OtherStar);
				// resets final agree if this was selected

				// adds new location data
				var otherP = otherPos.appendChild(p);
				otherP.id = "othersPos";

				var newPos = new google.maps.LatLng(y, x);
				mark = setMarker(mark, newPos);
				mark.setOpacity(.5);
			}
		};
	};

	// endpoint initialization
	// incase we have https
	if (window.location.protocol == "http:") {
		othMark.connect('ws://' + window.location.host + "/ComS309Project/map/"
				+ getURLParameter("saleID"));
	} else {
		othMark.connect('wss://' + window.location.host
				+ "/ComS309Project/map/" + getURLParameter("saleID"));
	}

	// map boundrys
	var boundry = new google.maps.LatLngBounds(new google.maps.LatLng(
			42.007171, -93.659365), new google.maps.LatLng(42.037073,
			-93.629762));

	// drag listener
	google.maps.event.addListener(map, 'dragend', function() {

		// force center of map within boundrys
		// fixed center
		var cent = posFix(map.getCenter());

		// setting center to corrected position
		map.setCenter(cent);
	});

	// zoom listener
	google.maps.event.addListener(map, 'zoom_changed', function() {
		// forces map to not zoom out farther than max
		if (map.getZoom() < maxZoomOut) {
			map.setZoom(maxZoomOut);
		}
	});

	// prevent dblclick zoom
	map.setOptions({
		disableDoubleClickZoom : true
	});

}

google.maps.event.addDomListener(window, 'load', init);