/**
 * 
 */

$("#deleteButton").click(function () {
    $.ajax({
        url: "../../309/T11/deleteUser/" + $("#userDelete").val(),
        type: "GET",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
        	alert(result.Status)
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
        type: "GET",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
        	alert(result.Status)
            console.log(result);
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});


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


function logout() {
	
	deleteCookie("ourCookie");
 window.location.href = '../../index.html';
 
}

function deleteCookie() {
	  document.cookie = "ourCookie=Authorization=; path=/";
	}