// Run calls on page load
window.onload = function(){
	filter();
}

// Find number of trades the user has made
function getCurrentSales(){
	$.ajax({
		url: "../../309/T11/getCurrentSales",
		type: "GET",
		datatype: 'json',
		success: function(result){
			
			var table = document.getElementById("currentSalesTable");
			for (var i = table.rows.length - 1; i > 0; i--) {
				
				table.deleteRow(i);

			}
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

// Grabs all current sales and then sorts them based on a weight corresponding to the presence of input keywords.
// input is a string of words like: "book calculus textbook math 165"
// Function will sort the sales based on the weight of the descriptions matching those words.
// Weight max is equal to the number of words.
// The top of the returned/displayed table will have the highest weight and it decreases as you go down the table.
function filter() {
	
	var strings = document.getElementById("search").value.toLowerCase().split(",");
	
	if (strings.length == 1 && strings[0] == "") {
		
		getCurrentSales();
		
	} else {
	
		$.ajax({
			url: "../../309/T11/getCurrentSales",
			type: "GET",
			datatype: 'json',
			success: function(result){

				// variables
				var rows = result.Result[1].Sales;
				var weight = new Array(rows.length);
				var enums = new Array(rows.length);
				var strings = document.getElementById("search").value.toLowerCase().split(",");
				
				// check if keywords are present in each sale description
				for (var i = 0; i < rows.length; i++) {
		
					weight[i] = 0;
					enums[i] = i;
					var tags = rows[i].Tags.toLowerCase().split(",");
					for (j = 0; j < strings.length; j++) {
						
						for (k = 0; k < tags.length; k++) {
							
							if (tags[k].trim() === strings[j].trim()) {
								
								weight[i] = weight[i] + 1;
						
							}
							
						}
				
					}
			
				}
	
				// Sort the enumeration/index array and weight array the same way.
				sort(weight, enums);
	
				// Clear table 
				var table = document.getElementById("currentSalesTable");
				for (var i = table.rows.length - 1; i > 0; i--) {
					
					table.deleteRow(i);
	
				}
	
				// Add sales to the table.
				for (var i = 0; i < enums.length; i++) {
	
					if (weight[i] > 0) {
						
						displayCurrentSales(rows[enums[i]].saleDescription, rows[enums[i]].Seller, rows[enums[i]].dateCreated);
						
					}
	
				}
				
			},
			error: function(dc, status, err){
				console.log(err);
				console.log(status);
			}
	
		});
		
	}
	
}

// Sort function (selection sort) that sorts both arrays simultaneously based on the weight values.
function sort(weight, enums) {

	for (var i = 0; i < weight.length; i++) {

        var max = i;

        for (var j = i + 1; j < weight.length; j++) {

            if (weight[max] < weight[j]) {

				max = j;

            }
        
        }
        
        if (max !== i) {

			swap(i, max, weight, enums)

        }
        
    }

}

// Swaps the i and max values of the weight and enums arrays.
function swap(i, max, weight, enums) {

	var tmp = weight[i];
	weight[i] = weight[max];
	weight[max] = tmp;

	var enumstmp = enums[i];
	enums[i] = enums[max];
	enums[max] = enumstmp;

}


function displayCurrentSales(description, seller, date, saleID) {
    var sale = document.createElement("p");
    var table = document.getElementById("currentSalesTable");
    var row = table.insertRow(-1);
    var descriptionRow = row.insertCell(0);
    var userRow = row.insertCell(1);
    var dateRow = row.insertCell(2);
    descriptionRow.innerHTML = description;
    descriptionRow.addEventListener('click', function(){
        window.location.href = '../tradeDetails/tradeDetails.html?saleID=' + saleID + "&saleDescription=" + description + "&saleSeller=" + seller + "&saleDate=" + date;
    }); 
    userRow.innerHTML = seller;
    userRow.addEventListener('click', function(){
        window.location.href = '../profilePage/index.html?username=' + seller;
    }); 
    dateRow.innerHTML = date;
}


function createSale()	 {
	window.location.href = '../createSale/createSale.html';
}
