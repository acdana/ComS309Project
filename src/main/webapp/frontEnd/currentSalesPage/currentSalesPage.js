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

function filter(input) {
	
	$.ajax({
		url: "../../309/T11/getCurrentSales",
		type: "GET",
		datatype: 'json',
		success: function(result){

			var rows = result.Result[1].Sales;
			var weight = new Array(rows.length);
			var enums = new Array(rows.length);
			var strings = input.toLowerCase().split(" ");
			
			for (var i = 0; i < rows.length; i++) {
		
				weight[i] = 0;
				enums[i] = i;
				var description = rows[i].saleDescription.toLowerCase();
				for (j = 0; j < strings.length; j++) {

					if (description.indexOf(strings[j]) !== -1) {
				
						weight[i] = weight[i] + 1;
				
					}
			
				}
		
			}

			sort(weight, enums);

			var table = document.getElementById("currentSalesTable");
			for (var i = table.rows.length - 1; i > 0; i--) {
				
				table.deleteRow(i);

			}

			for (var i = 0; i < enums.length; i++) {

				displayCurrentSales(rows[enums[i]].saleDescription, rows[enums[i]].Seller, rows[enums[i]].dateCreated);

			}
			
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}

	});
	
}

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
