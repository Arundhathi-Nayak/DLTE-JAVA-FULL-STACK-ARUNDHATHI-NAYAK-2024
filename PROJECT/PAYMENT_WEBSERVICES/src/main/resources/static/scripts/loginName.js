$(document).ready(()=> {
    $.ajax({
        type: "GET",
        url: "/payees/name",
        dataType: 'text',
        contentType:"application/json;charset=utf-8",

        success: function (response) {
            alert(response);
            $('#Username').text("Welcome, " + response); // Display the name
        },
        error: function (xhr, status, error) {
            console.error(xhr.responseText);
            $('#Username').text("Error fetching name");
        }
    });


});