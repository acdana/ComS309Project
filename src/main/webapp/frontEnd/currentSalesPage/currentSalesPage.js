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
			while(result.Result[i] != null) {
			displayCurrentSales(result.Result[i].saleDescription, result.Result[i].Seller, result.Result[i].dateCreated);
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


function displayCurrentSales(description, seller, date) {
    var sale = document.createElement("p");
    var table = document.getElementById("currentSalesTable");
    var row = table.insertRow(-1);
    var descriptionRow = row.insertCell(0);
    var userRow = row.insertCell(1);
    var dateRow = row.insertCell(2);
    descriptionRow.innerHTML = description;
    userRow.innerHTML = seller;
    dateRow.innerHTML = date;
}


function logout() {
	
	deleteCookie("ourCookie");
	alert(document.cookie);
    window.location.href = '../../index.html';
    
}

function deleteCookie() {
	  document.cookie = "ourCookie=Authorization=; path=/";
	}