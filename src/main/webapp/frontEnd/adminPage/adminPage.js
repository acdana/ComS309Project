/**
 * 
 */

$("#deleteButton").click(function () {
    $.ajax({
        url: "../../309/T11/deleteUser/" + $("#userDelete").val(),
        type: "DELETE",
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

$("#changeUserTypeButton").click(function () {
    $.ajax({
        url: "../../309/T11/changeUserType/" + $("#usernameToChange").val() + "/" + $("#changeUserType").val(),
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
