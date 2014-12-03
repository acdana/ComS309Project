var hmap = null;
var averageMarker = null;

function init() {

	
	// creation of the map object
	hmap = new google.maps.Map(document.getElementById('map'), {
		zoom : 14,
		center : new google.maps.LatLng(42.026652, -93.646420),
	});

	
}
google.maps.event.addDomListener(window, 'load', init);



$("#averageLocationButton").click(function () {
    $.ajax({
        url: "../../309/T11/getAverageSaleLocation",
        type: "GET",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
            console.log(result);
            
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(result.Result[1].Location[0].latitude, result.Result[1].Location[0].longitude),
                map: hmap,
                title:"Average Sale Location"
            });
            
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});