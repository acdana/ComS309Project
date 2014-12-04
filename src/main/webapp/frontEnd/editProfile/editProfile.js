
// when subBio is clicked this function is called that updates the current users bio
$('#subBio').click(function(){
	var bio = document.getElementById("uploadBio").value;
	var response = "";
	var bioSub = $.ajax({
		url: "../../309/T11/updateProfile/" + getUsername() + "/" + bio,
		type: "POST",
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function (result) {
			if(result.Result[0].Status == "Success"){
				console.log(result);
				alert("Success");
			}
			else {
				console.log(result);
				alert("Failure");
			}
		}
	});
	return false;
});

//when subPP is clicked this function is called which updates the profile picture of the currently logged in user
$('#subPP').click(function(){
	var pic = document.getElementById("uploadProfPic").value;
	pic = pic.replace(/\//g, '~');
	var response = "";
	var bioSub = $.ajax({
		url: "../../309/T11/updateProfilePic/" + getUsername() + "/" + encodeURIComponent(pic),
		type: "POST",
		headers: {
			"Authorization" : getCredentials(),
		},
		success: function (result) {
			if(result.Result[0].Status == "Success"){
				console.log(result);
				alert("Success");
			}
			else {
				console.log(result);
				alert("Failure");
			}
		}
	});
	return false;
});