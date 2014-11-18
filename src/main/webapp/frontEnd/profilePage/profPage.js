// Run calls on page load
window.onload = function(){
	getProfPic();
	getSignDate();
	getTotTrades();
	getSaleItems();
	getBio();
}


function getUsername() {
	var username = getCredentials().substr(0, getCredentials().indexOf(':'));
	return username.substr(username.indexOf(' ')+1);
}

// Find user's profile picture
function getProfPic(){
	getUsername();
	var path = $.ajax({
		url: "../../309/T11/getProfPic/" + getUsername(),
		type: "GET",
		datatype: 'json',
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function(result){
			displayProfilePic(result.Result[1].ProfilePic, 100, 100);
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
	
	
};


function displayProfilePic(path, width, height) {
    var pic = document.getElementById("profPic");
    pic.src = path;
    pic.width = width;
    pic.height = height;

}

// Find user's bio
function getBio(){
	getUsername();
	var path = $.ajax({
		url: "../../309/T11/getBio/" + getUsername(),
		type: "GET",
		datatype: 'json',
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function(result){
			displayBio(result.Result[1].Bio);
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
}

// userBio helper function
function displayBio(path){
	var bio = document.getElementById("userBio");
	bio.src = path;
}

// Find user's sign-up date
//TODO This is not a REST URL at the moment
function getSignDate(){
	$.ajax({
		url: "../../309/T11/getSignUpDate",
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
			displayTotalTrades(result.Result[1].Trades);
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};


function displayTotalTrades(trades) {
    document.getElementById("totalTradesMade").innerHTML = "Total Trades: " + trades;

}

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
	   var authorization = "Authorization=Basic ";
	   var idx = document.cookie.indexOf(authorization)

	   if (idx != -1) {
	       var end = document.cookie.indexOf(";", idx + 1);
	       if (end == -1) end = document.cookie.length;
	       return unescape(document.cookie.substring(idx + authorization.length, end));
	   } else {
	       return "";
	  }
}


function logout() {
	
	deleteCookie("ourCookie");
    window.location.href = '../../index.html';
    
}

function deleteCookie() {
	  document.cookie = "ourCookie=Authorization=; path=/";
	}




function setUsername() {
	var userText = document.getElementById("logout")
	userText.innerText = getUsername();
	userText.style.textAlign="right";
}