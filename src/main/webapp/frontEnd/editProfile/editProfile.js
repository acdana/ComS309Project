function logout() {
	
	deleteCookie("ourCookie");
    window.location.href = '../../index.html';
    
}

function deleteCookie() {
	  document.cookie = "ourCookie=Authorization=; path=/";
	}

// Submit Bio Function
$('#subBio').click(function(){
	var bio = document.forms["bioForm"]["uploadBio"];
	var response = "";
	var bioSub = $.ajax({
		url: "309/T11/userBio/" + bio,
		type: "GET",
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