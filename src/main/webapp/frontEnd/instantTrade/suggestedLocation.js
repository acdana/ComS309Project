
$("#suggestedLocationButton").click(function () {
	
	try {
	var firstCoord = document.getElementById("yourPos").innerHTML;
	var secondCoord = document.getElementById("othersPos").innerHTML;
	var firstLat = parseFloat(firstCoord.split(",")[0]);
	var firstLon = parseFloat(firstCoord.split(",")[1]);
	var secondLat = parseFloat(secondCoord.split(",")[0]);
	var secondLon = parseFloat(secondCoord.split(",")[1]);
	var averageLat = (firstLat+secondLat)/2;
	var averageLon = (firstLon+secondLon)/2;
	}
	catch(error) {
		alert("Both users must have a location selected...");
	}
	

   getSuggestedLocation(averageLat, averageLon);
});


function getSuggestedLocation(lat, lon) {
var request = {
    location: new google.maps.LatLng(lat, lon),
    rankBy: google.maps.places.RankBy.DISTANCE,
    types: ['accounting',
            'airport',
            'amusement_park',
            'aquarium',
            'art_gallery',
            'atm',
            'bakery',
            'bank',
            'bar',
            'beauty_salon',
            'bicycle_store',
            'book_store',
            'bowling_alley',
            'bus_station',
            'cafe',
            'campground',
            'car_dealer',
            'car_rental',
            'car_repair',
            'car_wash',
            'casino',
            'cemetery',
            'church',
            'city_hall',
            'clothing_store',
            'convenience_store',
            'courthouse',
            'dentist',
            'department_store',
            'doctor',
            'electrician',
            'electronics_store',
            'embassy',
            'establishment',
            'finance',
            'fire_station',
            'florist',
            'food',
            'funeral_home',
            'furniture_store',
            'gas_station',
            'general_contractor',
            'grocery_or_supermarket',
            'gym',
            'hair_care',
            'hardware_store',
            'health',
            'hindu_temple',
            'home_goods_store',
            'hospital',
            'insurance_agency',
            'jewelry_store',
            'laundry',
            'lawyer',
            'library',
            'liquor_store',
            'local_government_office',
            'locksmith',
            'lodging',
            'meal_delivery',
            'meal_takeaway',
            'mosque',
            'movie_rental',
            'movie_theater',
            'moving_company',
            'museum',
            'night_club',
            'painter',
            'park',
            'parking',
            'pet_store',
            'pharmacy',
            'physiotherapist',
            'place_of_worship',
            'plumber',
            'police',
            'post_office',
            'real_estate_agency',
            'restaurant',
            'roofing_contractor',
            'rv_park',
            'school',
            'shoe_store',
            'shopping_mall',
            'spa',
            'stadium',
            'storage',
            'store',
            'subway_station',
            'synagogue',
            'taxi_stand',
            'train_station',
            'travel_agency',
            'university',
            'veterinary_care',
            'zoo']
};

suggest = document.getElementById('suggestDiv');

var service = new google.maps.places.PlacesService(suggest);
service.nearbySearch(request, callback);

}



function callback(results, status) {

    if (status == google.maps.places.PlacesServiceStatus.OK) {
        if(results.length > 0) {
        	  var rawCoord = results[0].geometry.location.toString();
        	  rawCoord = rawCoord.substring(0, rawCoord.length - 1);
        	  rawCoord = rawCoord.substring(1, rawCoord.length);
        	  rawCoord = rawCoord.split(",");
        	  var image = '../assets/png/beachflag.png';
        	  var myLatLng = new google.maps.LatLng(rawCoord[0], rawCoord[1]);
        
        	  
        	  if(suggestedMarker) {
        		  suggestedMarker.setPosition(myLatLng);
        	  }
        	  else {
        		  	suggestedMarker = new google.maps.Marker({
        	  		position: myLatLng,
        	  		map: map,
        	  		icon: image
        	  	});
        	  }
        }
    }
}
