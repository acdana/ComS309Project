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

//this function gets the users profile pic (from an ajax call) and displays it in a previously generated html image tag
function displayProfilePic(path, width, height) {
    var pic = document.getElementById("profPic");
    pic.src = path;
    pic.width = width;
    pic.height = height;

}

// this function gets the given users bio
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

// userBio helper function used to display the bio in the element with the given id
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

//this is a helper method that displays the total number of trades a user has made
function displayTotalTrades(trades) {
    document.getElementById("totalTradesMade").innerHTML = "Total Trades: " + trades;
}

// Find number of sales open for a give user
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




//when sendMessageButton is clicked a message is sent to the user with the given message
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

//helper method for displaying the amount of sales a user has open
function displaySales(sales) {
	document.getElementById("currentUserSales").innerHTML = "Current User Sales: " + sales;
}