//javascript for map

//initialization for use outside this file
var marker = null;
var mark = null;
var map = null;

function init() {
	// marks between users
	var othMark = {};
	othMark.socket = null;

	// this marker

	// other persons marker

	// for displaying location of both markers
	var yoursPos = document.getElementById("yoursPos");
	var otherPos = document.getElementById("otherPos");

	var maxZoomOut = 14;

	map = new google.maps.Map(document.getElementById('map'), {
		zoom : maxZoomOut,
		center : new google.maps.LatLng(42.026652, -93.646420),
		mapTypeId : google.maps.MapTypeId.ROADMAP
	});

	// make new marker or change marker position
	function setMarker(mar, fixPos) {
		if (mar) {
			mar.setPosition(fixPos);
		} else {
			mar = new google.maps.Marker({
				position : fixPos,
				map : map

			});
		}
		return mar;
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

						var msg = marker.getPosition().lat() + ","
								+ marker.getPosition().lng();

						// taks msg and maeks into html displayable
						var p = document.createElement('p');
						p.style.wordWrap = "break-word";
						p.innerHTML = msg;

						// removes old location information
						if (yoursPos.childNodes.length > 0) {
							yoursPos.removeChild(yoursPos.firstChild);
						}
						// adds new locations data
						yoursPos.appendChild(p);
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
				otherPos.appendChild(p);
				var newPos = new google.maps.LatLng(y, x);
				mark = setMarker(mark, newPos);
				mark.setOpacity(.5);
			}
		};
	};

	// endpoint initialization
	// incase we have https
	if (window.location.protocol == "http:") {
		othMark.connect('ws://' + window.location.host + "/ComS309Project/map");
	} else {
		othMark
				.connect('wss://' + window.location.host
						+ "/ComS309Project/map");
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