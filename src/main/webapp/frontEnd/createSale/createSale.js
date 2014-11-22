

$("#submitButton").click(function () {
    $.ajax({
        url: "../../309/T11/createSale/" + $("#description").val(),
        type: "POST",
        headers: {
        	"Authorization" : getCredentials(),
        },
        success: function (result) {
            console.log(result);
            window.location.href = '../currentSalesPage/currentSalesPage.html';
        },
        error: function (dc, status, err) {
            console.log(err);
            console.log(status);
        }
    });
});
