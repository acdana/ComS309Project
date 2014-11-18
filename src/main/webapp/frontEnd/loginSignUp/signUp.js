//Login/Sign Up Page Script - Daniel Cain

function checkForm() {
    // initializing variables to values entered in the form
    var n = document.forms["signUpForm"]["name"].value;
    var email = document.forms["signUpForm"]["email"].value;
    var pass1 = document.forms["signUpForm"]["password1"].value;
    var pass2 = document.forms["signUpForm"]["password2"].value;

    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");



    // Checks if the name is made with alphanumeric chars and isn't empty
    if (n == null || n == "" || !n.match(/^[0-9a-z]+$/)) {
        alert("Name must be filled out with alphanumeric characters only");
        return false;
    }

    // Checks if the email field is filled and has a domain for the email host
    // TO DO: Check if the email is from "iastate.edu"
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
        alert("Not a valid e-mail address");
        return false;
    }

    // Check if Password and Confirm Password have the same value
    if (pass1 != pass2) {
        alert("Password and its confirmation aren't the same.");
        return false;
    }
    $.ajax({
        url: "../../createNewUser/" + n + "/" + pass1 + "/" + email,
        type: "POST",
        success: function (result) {
            console.log(result);
        }
    })
}


// Submit GET request, creates new user
// Runs when the submit button (for Sign Up) is clicked
$("#submitButton").click(function () {
    $.ajax({
        url: "../309/T11/createNewUser/" + $("#username").val() + "/" + $("#pass").val() + "/" + $("#email").val(),
        type: "GET",
        success: function (result) {
            console.log(result);
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});

// Check for corrct username/password matchup
function checkLogin() {
    // Initialize variables for the forms from the login section
    var un = document.forms["loginForm"]["logUsername"].value;
    var passw = document.forms["loginForm"]["logPassword"].value;
    
    var header = "Basic " + un + ":" + passw;

    document.cookie = "ourCookie=expires=;Authorization=; path=/";
    
    document.cookie = "ourCookie=Authorization=" + header + "; path=/";

}




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



$('#loginButton').click(function() {
    var username = document.forms["loginForm"]["logUsername"].value;
    var password = document.forms["loginForm"]["logPassword"].value;
    var response = "";
    var login = $.ajax({
        url: "../309/T11/userLogin/" + username + "/" + password,
        type: "GET",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
        	if(result.Status == "Login Success") {
        		checkLogin();
        	    window.location.href = 'frontEnd/profilePage/index.html';
        	}
        	else {
        		alert("Incorrect Username/Password")
        	}
            console.log(result);

        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
	
    return false;
});




//$("#testButton").click(function () {
  //  $.ajax({
    //    url: "309/T11/getAllUsernames",
      //  type: "GET",
        //headers: {
        	//"Authorization" : getCredentials(),
//        },
  //      success: function (result) {
    //        console.log(result);
      //  },
        //error: function (dc, status, err) {
//            console.log(err);
  //          console.log(status);
    //    }
//    });
//});
