// Run calls on page load
window.onload = function(){
	getProfPic();
	getTotTrades();
	getOpenSales();
	getBio();
}


function getUsername() {
	var username = getCredentials().substr(0, getCredentials().indexOf(':'));
	return username.substr(username.indexOf(' ')+1);
}



function getURLParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}


// Find user's profile picture
function getProfPic(){
	getUsername();
	var path = $.ajax({
		url: "../../309/T11/getProfPic/" + getURLParameter("username"),
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
		url: "../../309/T11/getBio/" + getURLParameter("username"),
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
	bio.innerHTML = path;
}


// Find number of trades the user has made
function getTotTrades(){
	$.ajax({
		url: "../../309/T11/getTotTrades/" + getURLParameter("username"),
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
function getOpenSales(){
	$.ajax({
		url: "../../309/T11/getOpenSales/" + getURLParameter("username"),
		type: "GET",
		datatype: 'json',
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function(result){
			console.log(result);
			displaySales(result.Result[1].Sales);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};





$("#sendMessageButton").click(function () {
    $.ajax({
        url: "../../309/T11/createNewMessage/" + getURLParameter("username") + "/" + $("#message").val() + "/" + getUsername(),
        type: "POST",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
            console.log(result);
            alert("Message has been sent...");
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});



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

function displaySales(sales) {
	document.getElementById("currentUserSales").innerHTML = "Current User Sales: " + sales;
}