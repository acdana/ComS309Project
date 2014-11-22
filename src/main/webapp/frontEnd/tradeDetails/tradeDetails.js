window.onload = function(){
	setSaleInfo();
	getSellerReputation();
}


function setSaleInfo() {
	var saleID = document.getElementById("saleID");
	saleID.innerText = "Sale ID: " + getURLParameter("saleID");
	var saleDescription = document.getElementById("saleDescription");
	saleDescription.innerText = "Sale Description: " + unescape(getURLParameter("saleDescription"));
	var sellerName = document.getElementById("traderName");
	sellerName.innerText = "Trader: " + unescape(getURLParameter("saleSeller"));	
}


function getSellerReputation(){
	$.ajax({
		url: "../../309/T11/getReputation/" + unescape(getURLParameter("saleSeller")),
		type: "GET",
		datatype: 'json',
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function(result){
			console.log(result);
			var sellerRep = document.getElementById("traderReputation");
			sellerRep.innerText = "Reputation: " + result.Result[1].Reputation;
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};


$("#beginTradeButton").click(function () {
	 window.location.href = '../instantTrade/tradePage.jsp?saleID=' + getURLParameter("saleID");
});
