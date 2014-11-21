// Run calls on page load
window.onload = function(){
	getCurrentSales();
}




// Find number of trades the user has made
function getCurrentSales(){
	$.ajax({
		url: "../../309/T11/getCurrentSales",
		type: "GET",
		datatype: 'json',
		success: function(result){
			
			var i = 0;
			while(result.Result[1].Sales[i] != null) {
			displayCurrentSales(result.Result[1].Sales[i].saleDescription, result.Result[1].Sales[i].Seller, result.Result[1].Sales[i].dateCreated, result.Result[1].Sales[i].saleID);
			i++;
			}
			console.log(result);
			
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};


function displayCurrentSales(description, seller, date, saleID) {
    var sale = document.createElement("p");
    var table = document.getElementById("currentSalesTable");
    var row = table.insertRow(-1);
    var descriptionRow = row.insertCell(0);
    var userRow = row.insertCell(1);
    var dateRow = row.insertCell(2);
    descriptionRow.innerHTML = description;
    descriptionRow.addEventListener('click', function(){
        window.location.href = '../tradeDetails/index.html?saleID=' + saleID + "&saleDescription=" + description + "&saleSeller=" + seller + "&saleDate=" + date;
    }); 
    userRow.innerHTML = seller;
    userRow.addEventListener('click', function(){
        window.location.href = '../profilePage/index.html?username=' + seller;
    }); 
    dateRow.innerHTML = date;
}


function logout() {
	
	deleteCookie("ourCookie");
    window.location.href = '../../index.html';
    
}

function deleteCookie() {
	  document.cookie = "ourCookie=Authorization=; path=/";
	}


function createSale()	 {
	window.location.href = '../createSale/createSale.html';
}

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

function getUsername() {
	var username = getCredentials().substr(0, getCredentials().indexOf(':'));
	return username.substr(username.indexOf(' ')+1);
}

function setUsername() {
	var userText = document.getElementById("logout")
	userText.innerText = getUsername();
	userText.style.textAlign="right";
}