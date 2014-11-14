function logout() {
	
	deleteCookie("ourCookie");
    window.location.href = '../../index.html';
    
}

function deleteCookie() {
	  document.cookie = "ourCookie=Authorization=; path=/";
	}

// Submit Bio Function
$('#subBio').click(function(){
	
});