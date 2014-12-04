
//when submitButton is clicked this function creates a sale with the given description and tags
$("#submitButton").click(function () {
	if($("#tags").val() == null || $("#tags").val() == "") {
	    $.ajax({
	        url: "../../309/T11/createSale/" + $("#description").val() + "/none",
	        type: "POST",
	        headers: {
	        	"Authorization" : getCredentials(),
	        },
	        success: function (result) {
	            console.log(result);
	            window.location.href = '../currentSalesPage/currentSalesPage.html';
	        },
	        error: function (dc, status, err) {
	            console.log(err);
	            console.log(status);
	        }
	    });
	}
	else {
    $.ajax({
        url: "../../309/T11/createSale/" + $("#description").val() + "/" + $("#tags").val(),
        type: "POST",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
            console.log(result);
            window.location.href = '../currentSalesPage/currentSalesPage.html';
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
	}
	
});
