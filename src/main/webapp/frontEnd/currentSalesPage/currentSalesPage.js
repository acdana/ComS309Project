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
			displayCurrentSales(result.Result[i].Seller, result.Result[i].dateCreated);
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


function displayCurrentSales(seller, date) {
    var sale = document.createElement("p");
    var details = document.createTextNode(seller + " - - - - - - " + date);
    sale.appendChild(details);
    var element = document.getElementById("currentSales");
    element.appendChild(sale);
}


