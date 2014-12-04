//baseMap: a basic google map,
//heatmap: layer put on the original map that allows for the heatmap visual
var baseMap, heatmap;

//collection of sale coordinates
var saleData = [];

function initialize() {
  //make a map with center on campus with a satellite view
  var mapOptions = {
	zoom : 14,
	center : new google.maps.LatLng(42.026652, -93.646420),
    mapTypeId: google.maps.MapTypeId.SATELLITE
  };
  
  //assign the map to a div
  baseMap = new google.maps.Map(document.getElementById('heatMap'),
      mapOptions);
  
  //a google maps array containing the sale coordinates
  var pointArray = new google.maps.MVCArray(saleData);

  //creates heatmap layer
  heatmap = new google.maps.visualization.HeatmapLayer({
    data: pointArray
  });

  //set heatmap to map to baseMap
  heatmap.setMap(baseMap);
}


//load the map on page load
google.maps.event.addDomListener(window, 'load', initialize);

//when heatMapButton is clicked it calls this function
$("#heatMapButton").click(function () {
	//hides the average sale location map
	document.getElementById('map').style.display = 'none';
	//displays the heatmap
	document.getElementById('heatMap').style.display = 'block';
	//reloads map to reset view
	google.maps.event.trigger(baseMap, 'resize');
	
	//ajax call to get the sales locations
    $.ajax({
        url: "../../309/T11/getAllSaleLocations",
        type: "GET",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
            console.log(result);
           
            //iterate through each location and store as LatLng object in saleData
            var i = 0;
            while(result.Result[1].Locations[i] != null) {
            	saleData.push(new google.maps.LatLng(result.Result[1].Locations[i].Lat, result.Result[1].Locations[i].Lon));
            	i++;
            }
            
            
            //reload the map with the updated heatmap
            mapOptions = {
            		zoom : 14,
            		center : new google.maps.LatLng(42.026652, -93.646420),
            	    mapTypeId: google.maps.MapTypeId.SATELLITE
            	  };

            	  baseMap = new google.maps.Map(document.getElementById('heatMap'),
            	      mapOptions);

            	  pointArray = new google.maps.MVCArray(saleData);

            	  heatmap = new google.maps.visualization.HeatmapLayer({
            	    data: pointArray
            	  });

            	  heatmap.setMap(baseMap);
            
            
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});