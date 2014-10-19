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
        url: "309/T11/createNewUser/" + $("#name").val() + "/" + $("#pass").val() + "/" + $("#email").val(),
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

}




$("#testButton").click(function () {
    $.ajax({
        url: "309/T11/getAllUsernames",
        type: "GET",
        headers: {
        	"Authorization" : "TestUser" + ":" + "myPassword", //TestUser and myPassword need to be stored during the jQuery session somehow.
        },
        success: function (result) {
            console.log(result);
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});