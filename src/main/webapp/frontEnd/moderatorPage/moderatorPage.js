//when penalizeButton is pressed this method is called to penalize the given user
$("#penalizeButton").click(function () {
    $.ajax({
        url: "../../309/T11/penalizeUser/" + $("#userPenalize").val(),
        type: "POST",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
        	alert(result.Result[0].Status)
            console.log(result);
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});

//when userPenaltyButton is pressed this function is called to return the current penalty count of the given user
$("#userPenaltyButton").click(function () {
    $.ajax({
        url: "../../309/T11/getPenaltyCount/" + $("#userPenaltyCount").val(),
        type: "GET",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
        	alert(result.Result[1].PenaltyCount)
            console.log(result);
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});

//when userTypeButton is pressed this method gets the user type of the given user
$("#userTypeButton").click(function () {
    $.ajax({
        url: "../../309/T11/getUserType/" + $("#userType").val(),
        type: "GET",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
        	alert(result.Result[1].UserType)
            console.log(result);
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});