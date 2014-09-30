//Login/Sign Up Page Script - Daniel Cain

function checkForm() {
    // initializing variables to values entered in the form
    var n = document.forms["signUpForm"]["name"].value;
    var email = document.forms["signUpForm"]["email"].value;
    var address = document.forms["signUpForm"]["address"].value;

    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");

    // Checks if the name is made with alphanumeric chars and isn't empty
    if (n == null || n == "" || !n.match(/^[0-9a-z]+$/)) {
        alert("Name must be filled out with alphanumeric characters only");
        return false;
    }

    // Checks if the address field is filled
    if (address == null || address == "") {
        alert("Address required");
        return false;
    }

    // Checks if the email field is filled and has a domain for the email host
    // TO DO: Check if the email is from "iastate.edu"
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
        alert("Not a valid e-mail address");
        return false;
    }
}