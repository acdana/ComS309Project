var hmap = null;

function init() {

	
	// creation of the map object
	hmap = new google.maps.Map(document.getElementById('map'), {
		zoom : 14,
		center : new google.maps.LatLng(42.026652, -93.646420),
	});

	
}
google.maps.event.addDomListener(window, 'load', init);