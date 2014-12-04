//to hold the map object
var hmap = null;

function init() {

	
	// creation of the map object
	hmap = new google.maps.Map(document.getElementById('map'), {
		zoom : 14,
		center : new google.maps.LatLng(42.026652, -93.646420),
	});

	
}
google.maps.event.addDomListener(window, 'load', init);


//when averageLocationButton is pressed it calls this function
$("#averageLocationButton").click(function () {
	//display the averageLocation map
	document.getElementById('map').style.display = 'block';
	//resize the map so that the view isn't off
	google.maps.event.trigger(hmap, 'resize');
	//hide the heat map
	document.getElementById('heatMap').style.display = 'none';
	
	//ajax call gets the coordinates of the average sale location
    $.ajax({
        url: "../../309/T11/getAverageSaleLocation",
        type: "GET",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
            console.log(result);
            //reload map to center on the average sale location
        	hmap = new google.maps.Map(document.getElementById('map'), {
        		zoom : 14,
        		center : new google.maps.LatLng(result.Result[1].Location[0].latitude, result.Result[1].Location[0].longitude),
        	});
        	//marker is placed on the average sale location
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

