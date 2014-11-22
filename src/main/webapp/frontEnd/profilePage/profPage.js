// Run calls on page load
window.onload = function(){
	getProfPic();
	getTotTrades();
	getOpenSales();
	getBio();
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

function displaySales(sales) {
	document.getElementById("currentUserSales").innerHTML = "Current User Sales: " + sales;
}