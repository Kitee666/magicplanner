$(document).ready(function() {

    // $.ajax({
    //     type: 'GET',
    //     dataType: 'json',
    //     url: 'http://localhost:8080/api/v1/connector',
    //     success : function(connector) {
    //         console.log(connector)
    //     },
    // });

        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: 'http://localhost:8080/api/v1/connector',
            success: function (result) {
                console.log(result)
                //after successfully call bind data to Table
                if (result && result.length > 0)
                {
                    jQuery.each(result, function (i, val) {
                    console.log(val);
                    console.log(val.id);
                    console.log(val.lecturer.name);


                    });
                }
            },
            fail: function (jqXHR, textStatus) {
                alert("Request failed: " + textStatus);
            }
        });



    $("#external-events").append("<p>\n" +
        "                    <strong>Draggable Events</strong>\n" +
        "                </p>\n" +
        "                <div class='fc-event'>My Event 1</div>\n" +
        "                <div class='fc-event'>My Event 2</div>\n" +
        "                <div class='fc-event'>My Event 3</div>\n" +
        "                <div class='fc-event'>My Event 4</div>\n" +
        "                <div class='fc-event'>My Event 5</div>\n" +
        "                <p>\n" +
        "                    <input type='checkbox' id='drop-remove' />\n" +
        "                    <label for='drop-remove'>remove after drop</label>\n" +
        "                </p>");
});
