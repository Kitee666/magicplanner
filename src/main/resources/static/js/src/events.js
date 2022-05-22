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

                        $("#external-events").append(`<div class='fc-event'>
                            <div class="inside_left">
                                <p class="sup">${val.lecturer.name} ${val.lecturer.lastname}</p>
                                <p class="main">${val.subject.name}</p>
                            </div>
                             <div class="inside_center">
                                <p class="typ">${val.group.type}</p>
                                <p class="grupa">${val.group.name}</p>
                            </div>
                           
                                                                            </div>`);


                    });
                }
            },
            fail: function (jqXHR, textStatus) {
                alert("Request failed: " + textStatus);
            }
        });




});
