// Run calls on page load
window.onload = function(){
	getMessages();
}




// This function is used to make an ajax call that gets a users messages
function getMessages(){
	$.ajax({
		url: "../../309/T11/getMessages/" + getUsername(),
		type: "GET",
		datatype: 'json',
        headers: {
        	"Authorization" : getCredentials(),
        },
		success: function(result){
			console.log(result);
			var i = 0;
			//for every message we call the displayMessages function to set the message on the page
			while(result.Result[1].Messages[i] != null) {
			displayMessages(result.Result[1].Messages[i].Message, result.Result[1].Messages[i].Sender, result.Result[1].Messages[i].dateCreated);
			i++;
			}
			
			
		},
		error: function(dc, status, err){
			console.log(err);
			console.log(status);
		}
	});
};

//this helper method creates a table with rows containing the message data provided via ajax call
function displayMessages(message, sender, date) {
    var sale = document.createElement("p");
    var table = document.getElementById("messagesTable");
    var row = table.insertRow(-1);
    var messageRow = row.insertCell(0);
    var senderRow = row.insertCell(1);
    var dateRow = row.insertCell(2);
    messageRow.innerHTML = message;
    messageRow.addEventListener('click', function(){
       var messageToSend = prompt("Enter your message to send to " + sender, "Message...");
       if(messageToSend != null && messageToSend != '') {
       sendMessage(sender, messageToSend);
       }
       else {
    	   alert("Blank messages are not allowed!");
       }
    }); 
    senderRow.innerHTML = sender;
    senderRow.addEventListener('click', function(){
    	window.location.href = '../profilePage/index.html?username=' + sender;
    }); 
    dateRow.innerHTML = date;
}

//this is a function used for a quick reply to a message. It makes an ajax call that sends a message to the given user
function sendMessage(sendTo, message) {
    $.ajax({
        url: "../../309/T11/createNewMessage/" + sendTo + "/" + message + "/" + getUsername(),
        type: "POST",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
            console.log(result);
            alert("Message has been sent...");
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
}