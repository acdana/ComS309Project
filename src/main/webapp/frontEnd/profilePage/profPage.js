// Find user's profile picture
function getProfPic(){
	$.ajax({
		url: "309/T11/getProfPic",
		type: "GET",
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};
// Find user's name
function getProfName(){
	$.ajax({
		url: "309/T11/getProfName",
		type: "GET",
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};

// Find user's sign-up date
function getSignDate(){
	$.ajax({
		url: "309/T11/getSignDate",
		type: "GET",
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};

// Find number of trades the user has made
function getTotTrades(){
	$.ajax({
		url: "309/T11/getTotTrades",
		type: "GET",
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};

// Find number of items currently for sale
function getSaleItems(){
	$.ajax({
		url: "309/T11/getSaleItems",
		type: "GET",
		success: function(result){
			console.log(result);
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};