<html>
<head>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyANwzgDecZIZr5Z8X5nNFEGZ1U8XqySgFs">
	
</script>
<script type="text/javascript">
	//max zoom out level

	function init() {
		var maxZoomOut = 14;
		var map = new google.maps.Map(document.getElementById('map'), {
			zoom : maxZoomOut,
			center : new google.maps.LatLng(42.026652, -93.646420),
			mapTypeId : google.maps.MapTypeId.ROADMAP
		});
		//map boundrys
		var boundry = new google.maps.LatLngBounds(new google.maps.LatLng(
				42.007171, -93.659365), new google.maps.LatLng(42.037073,
				-93.629762));

		//drag listener
		google.maps.event.addListener(map, 'dragend', function() {

			//force center of map within boundrys
			//fixed center
			var cent = posFix(map.getCenter());

			//setting center to corrected position
			map.setCenter(cent);
		});

		//zoom listener
		google.maps.event.addListener(map, 'zoom_changed', function() {
			//forces map to not zoom out farther than max
			if (map.getZoom() < maxZoomOut) {
				map.setZoom(maxZoomOut);
			}
		});

		//prevent dblclick zoom
		map.setOptions({
			disableDoubleClickZoom : true
		});

		//make dblclick new marker
		var marker;
		google.maps.event.addListener(map, 'dblclick', function(clickLatLng) {
			var fixPos = posFix(clickLatLng.latLng);
			if (marker) {
				//force marker into boundrys
				marker.setPosition(fixPos);
			} else {
				marker = new google.maps.Marker({
					position : fixPos,
					map : map
				});
			}
		});

		function posFix(pos) {
			var x = pos.lng();
			var y = pos.lat();

			//the boundrys
			var minX = boundry.getSouthWest().lng();
			var minY = boundry.getSouthWest().lat();
			var maxX = boundry.getNorthEast().lng();
			var maxY = boundry.getNorthEast().lat();

			//correcting position
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

	}

	//in google tutoria. makes things work. figure out what this does
	google.maps.event.addDomListener(window, 'load', init);
</script>


</head>
<body>
	<div id="map" style="width: 400px; height: 350px;"></div>
</body>
</html>