// the websocket
var agreeBut = {};
var secondSeller = null;
var secondTransaction = true;

//this gets the primary seller of the sale and sets secondTransaction based on whether or not the current user is the primary seller
//this is used later in the sale to determine who will upload data
$.ajax({
    url: "../../309/T11/getPrimarySeller/" + getURLParameter("saleID"),
    type: "GET",
	headers: {
		"Authorization" : getCredentials(),
	},
    success: function (result) {
        console.log(result);
        if(result.Result[1].Seller != getUsername()) {
        	secondSeller = getUsername();
        	secondTransaction = false;
        }
        
    },
    error: function (dc, status, err) {
        console.log(err);
        console.log(status);
    }
});


agreeBut.socket = null;

// for the button listeners
var agree = document.getElementById("agree");
var fAgree = document.getElementById("fAgree");

// where the strings get put
var aStar = document.getElementById("aStar");
var fStar = document.getElementById("fStar");

// initialization for the strings to inform users of agreement
var aStr = "--", fStr = "--";

// function that takes a string and html element , making the string useable for
// html and displays it in the given element
var htmlChange = function(str, htmlP) {
	var htStr = document.createElement('p');
	htStr.innerHTML = str;

	htmlP.removeChild(htmlP.firstChild);

	htmlP.appendChild(htStr);

}

// initial connection
agreeBut.connect = function(host) {
	moziSock(agreeBut, host);

	// when connected
	agreeBut.socket.onopen = function() {
		// listeners for button clicks on agree and final agree
		// Star system is the same as location buttons- left means this user has
		// pressed it, right means the other users has

		// shows trade has been agreed to
		agree.addEventListener("click", function() {
			// selects this button
			if (aStr == "--") {
				aStr = "*-";
			} else if (aStr == "-*") {
				aStr = "**";
			}
			agreeBut.socket.send("a,a");
			htmlChange(aStr, aStar);
		});

		// shows entire trade has been agreed too
		// only works if a location and the trade have been agreed to by both
		// users
		fAgree.addEventListener("click", function() {
			// selects this button
			if (aStr == "**" && (yStr == "**" || oStr == "**")) {
				if (fStr == "--") {
					fStr = "*-";
					
					//redirect to another page
				} else if (fStr == "-*") {
					fStr = "**";

					
					
					
					var selectLength = 0;
					var tradeBox = document.getElementById("tradeBox").options;
					//for all items in the trade box, add the items to the database
					while(selectLength < document.getElementById("tradeBox").options.length) {
						$.ajax({
					        url: "../../309/T11/createItem/" + getURLParameter("saleID") + "/" + getUsername() + "/" + tradeBox[selectLength].value,
					        type: "POST",
					        async: false,
					        headers: {
					        	"Authorization" : getCredentials(),
					        },
					        success: function (result) {
					        	console.log(result);
					        },
					        error: function (dc, status, err) {
					            console.log(err);
					            console.log(status);
					        }
					    });	
						selectLength++;
					}
				
				//if the current user is not the primary seller
				if(secondTransaction == false) {
						var firstCoordUpload = document.getElementById("yourPos").innerHTML;
						var secondCoordUpload = document.getElementById("othersPos").innerHTML;
						var firstLatUpload = parseFloat(firstCoordUpload.split(",")[0]);
						var firstLonUpload = parseFloat(firstCoordUpload.split(",")[1]);
						//add the sale details to the database
						$.ajax({
					        url: "../../309/T11/setSaleData/" + getURLParameter("saleID") + "/" + firstLatUpload + "/" + firstLonUpload + "/" + firstCoordUpload + "/" + secondCoordUpload + "/" + secondSeller,
					        type: "POST",
					        headers: {
					        	"Authorization" : getCredentials(),
					        },
					        success: function (result) {
					        	console.log(result);
					        	window.location.href = '../../frontEnd/profilePage/index.html?username='+ getUsername();
					        },
					        error: function (dc, status, err) {
					            console.log(err);
					            console.log(status);
					        }
					    });						

				}
				else {
					//if the current user is the primary seller we will instead have the other user upload the data
					agreeBut.socket.send("a,f");
					window.location.href = '../../frontEnd/profilePage/index.html?username='+ getUsername();
				}
				
				
				
				}
				agreeBut.socket.send("a,f");
				htmlChange(fStr, fStar);
				
			}
		});

	};

	// when a message is submitted
	agreeBut.socket.onmessage = function(message) {
		// check [0]: a for agree buttons,
		// m for map,
		// l of location buttons,
		// t for trade
		var mess = message.data.split(",");
		if (mess[0] == "a") {
			// second a shows the agree button was pressed, changes aStr
			// accordingly and displays
			if (mess[1] == "a") {
				if (aStr == "--") {
					aStr = "-*";
				} else if (aStr == "*-") {
					aStr = "**";
				}
				htmlChange(aStr, aStar);

				// shows the final agree button has been pressed, changes fStr
				// accordingly and displays
			} else if (mess[1] == "f") {
				if (fStr == "--") {
					fStr = "-*";
					//redirect
				} else if (fStr == "*-") {
					fStr = "**";
					
					

					var selectLength = 0;
					var tradeBox = document.getElementById("tradeBox").options;
					//for each item in the trade, upload the item to the database
					while(selectLength < document.getElementById("tradeBox").options.length) {
						$.ajax({
					        url: "../../309/T11/createItem/" + getURLParameter("saleID") + "/" + getUsername() + "/" + tradeBox[selectLength].value,
					        type: "POST",
					        async: false,
					        headers: {
					        	"Authorization" : getCredentials(),
					        },
					        success: function (result) {
					        	console.log(result);
					        },
					        error: function (dc, status, err) {
					            console.log(err);
					            console.log(status);
					        }
					    });	
						selectLength++;
					}
					
					//if the current user is not the primary seller
					if(secondTransaction == false) {

							var firstCoordUpload = document.getElementById("yourPos").innerHTML;
							var secondCoordUpload = document.getElementById("othersPos").innerHTML;
							var firstLatUpload = parseFloat(firstCoordUpload.split(",")[0]);
							var firstLonUpload = parseFloat(firstCoordUpload.split(",")[1]);
							//upload the details from the sale to the database				    
							$.ajax({
						        url: "../../309/T11/setSaleData/" + getURLParameter("saleID") + "/" + firstLatUpload + "/" + firstLonUpload + "/" + firstCoordUpload + "/" + secondCoordUpload + "/" + secondSeller,
						        type: "POST",
						        async: false,
						        headers: {
						        	"Authorization" : getCredentials(),
						        },
						        success: function (result) {
						        	console.log(result);
						        	window.location.href = '../../frontEnd/profilePage/index.html?username='+ getUsername(); 
						        },
						        error: function (dc, status, err) {
						            console.log(err);
						            console.log(status);
						        }
						    });						

					}
					
					
					
					window.location.href = '../../frontEnd/profilePage/index.html?username='+ getUsername();
				}
				htmlChange(fStr, fStar);
			}

		}

	};
};

proto(agreeBut);
