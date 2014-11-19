function logout() {
	
	deleteCookie("ourCookie");
    window.location.href = '../../index.html';
    
}

function getUsername() {
	var username = getCredentials().substr(0, getCredentials().indexOf(':'));
	return username.substr(username.indexOf(' ')+1);
}

function deleteCookie() {
	  document.cookie = "ourCookie=Authorization=; path=/";
	}

// Submit Bio Function
$('#subBio').click(function(){
	var bio = document.forms["bioForm"]["uploadBio"];
	var response = "";
	var bioSub = $.ajax({
		url: "../../309/T11/updateProfile/" + getUsername() + "/" + bio,
		type: "POST",
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function (result) {
			if(result.Status == "Login Success"){
				checkLogin();
				window.location.href = 'frontEnd/editProfile/index.html';
			}
			else {
				alert("Incorrect Username/Password");
			}
		}
	});
	return false;
});