// Run calls on page load
window.onload = function(){
	getProfPic();
	getProfName();
	getSignDate();
	getTotTrades();
	getSaleItems();
}


function getUsername() {
	var username = getCredentials().substr(0, getCredentials().indexOf(':'))
	return username.substr(username.indexOf(' ')+1);
}

// Find user's profile picture
function getProfPic(){
	getUsername();
	$.ajax({
		url: "../../309/T11/getProfPic/" + getUsername(),
		type: "GET",
		datatype: 'json',
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};


// Find user's name
//TODO This is not a REST URL at the moment
function getProfName(){
	$.ajax({
		url: "../../309/T11/getProfName",
		type: "GET",
		datatype: 'json',
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};

// Find user's sign-up date
//TODO This is not a REST URL at the moment
function getSignDate(){
	$.ajax({
		url: "../../309/T11/getSignDate",
		type: "GET",
		datatype: 'json',
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};

// Find number of trades the user has made
function getTotTrades(){
	$.ajax({
		url: "../../309/T11/getTotTrades/" + getUsername(),
		type: "GET",
		datatype: 'json',
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};

// Find number of items currently for sale
//TODO This is not a REST URL at the moment
function getSaleItems(){
	$.ajax({
		url: "../../309/T11/getSaleItems",
		type: "GET",
		datatype: 'json',
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};



function getCredentials() {
	   var authorization = "Authorization=";
	   var idx = document.cookie.indexOf(authorization)

	   if (idx != -1) {
	       var end = document.cookie.indexOf(";", idx + 1);
	       if (end == -1) end = document.cookie.length;
	       return unescape(document.cookie.substring(idx + authorization.length, end));
	   } else {
	       return "";
	  }
}