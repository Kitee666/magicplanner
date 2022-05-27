var CalendarAdmin;

document.addEventListener('DOMContentLoaded', function () {

    var Calendar = FullCalendar.Calendar;
    var Draggable = FullCalendar.Draggable;

    var containerEl = document.getElementById('external-events');
    var calendarEl = document.getElementById('calendar');
    var checkbox = document.getElementById('drop-remove');

    // initialize the external events
    // -----------------------------------------------------------------

    new Draggable(containerEl, {
        itemSelector: '.fc-event',
        eventData: function (eventEl) {
            return {
                title: eventEl.innerText,
                zIndex: 999,
                revert: true,      // will cause the event to go back to its
                revertDuration: 0  //  original position after the drag
            };

        },
    });

    // initialize the calendar
    // -----------------------------------------------------------------

    // function getIds(){
    //     alert($(this).data('id'));
    //     alert($(this).data('room'));
    // }

    CalendarAdmin = new Calendar(calendarEl, {
        locale: 'pl',
        firstDay: 1,
        forceEventDuration: true,
        defaultTimedEventDuration: '02:00:00',
        events:  {
            url: 'http://localhost:8080/api/v1/event/filtered',
            method: 'GET',
            extraParams: {
                year_type: categNr,
            }
            },
        headerToolbar: {
            left: 'prev,next,today',
            center: 'title',
            right: 'dayGridMonth,timeGridTwoDays',

        },
        initialView: 'timeGridTwoDays',
        views: {
            timeGridTwoDays: {
                type: 'timeGrid',
                duration: {days: 7},
                buttonText: 'Weekendy',
                hiddenDays: [1, 2, 3, 4, 5],
                weekday: 'long',
                slotDuration: '01:00:00',
                slotMinTime: '08:00:00',
                slotMaxTime: '20:00:00'
            },
            dayGridMonth: {
                buttonText: 'Widok miesiąca',

            },
        },
        buttonText: {
            today: 'Dzisiaj'
        },


        editable: true,
        droppable: true, // this allows things to be dropped onto the calendar
        // drop: function (info) {
        //     // is the "remove after drop" checkbox checked?
        //     if (checkbox.checked) {
        //         // if so, remove the element from the "Draggable Events" list
        //         info.draggedEl.parentNode.removeChild(info.draggedEl);
        //     }
        // },


        eventReceive: function(info) {

            //get the bits of data we want to send into a simple object
            // console.log(eventData);
            //send the data via an AJAX POST request, and log any response which comes from the server
            console.log(info.event);
            console.log(connectID);
            console.log(roomID);

            // console.log(info.event.start);
            // console.log(info.event.end);
            // console.log(info.id);
            // console.log(info.room.id);
            $.ajax({
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json; charset=UTF-8',
                url: "http://localhost:8080/api/v1/event",
                data: JSON.stringify({
                    "dateFrom": info.event.start,
                    "dateTo": info.event.end,
                    "connector": connectID,
                    "room": roomID

                }),
                success: function (addEvent) {

                    console.log("Dziaua")
                    location.reload();
                },
                error: function (addEvent) {
                    console.log("Nie dziala");
                }
            });
        },

        eventDrop: function(info) {


            console.log("daty");
            console.log(info.event.start);
            console.log(info.event.end);
            console.log("drop");
            console.log(info);
            let roomdrag, connectordrag;
            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: "http://localhost:8080/api/v1/event/" + info.event.id,
                success: function (event) {
                    console.log(event);
                    connectordrag = event.connector.id;
                    roomdrag = event.room.id;
                    console.log(connectordrag);
                    console.log(roomdrag);

                    $.ajax({
                        type: 'PUT',
                        dataType: 'json',
                        contentType: 'application/json; charset=UTF-8',
                        url: "http://localhost:8080/api/v1/event/" + info.event.id,
                        data: JSON.stringify({
                            "dateFrom": info.event.start,
                            "dateTo": info.event.end,
                            "connector": connectordrag,
                            "room": roomdrag,
                        }),
                        success: function (update) {
                            console.log("Udalo sie zrobić drag");
                            console.log(connectordrag);
                            console.log(roomdrag);
                            location.reload();
                        },
                        error: function (update) {
                            console.log("Nie udalo sie zrobić drag");
                            console.log(connectordrag);
                            console.log(roomdrag);
                        }
                    });

                }
            });
        },


        eventResize: function(info) {


                console.log("resize");
                let room, connector;
                $.ajax({
                    type: 'GET',
                    dataType: 'json',
                    url: "http://localhost:8080/api/v1/event/" + info.event.id,
                    success: function (event) {
                        connector = event.connector.id;
                        room = event.room.id;

                        $.ajax({
                            type: 'PUT',
                            dataType: 'json',
                            contentType: 'application/json; charset=UTF-8',
                            url: "http://localhost:8080/api/v1/event/" + info.event.id,
                            data: JSON.stringify({
                                "dateFrom": info.event.start,
                                "dateTo": info.event.end,
                                "connector": connector,
                                "room": room,
                            }),
                            success: function (update) {
                                console.log("Udalo sie");
                                location.reload();
                            },
                            error: function (update) {
                                console.log("Nie udalo sie dodac do bazy");
                            }
                        });

                    }
                });

        },

        eventClick: function(info) {
            // alert('Event: ' + info.event.title +
            //     'From' + info.event.start +
            //     'To' + info.event.end);

            $("#eventModal").html("");
            $("#eventModal").append(`
<script>
            function deleteEvent(){
                $.ajax({
                    type: 'DELETE',
                    dataType: 'json',
                    url: "http://localhost:8080/api/v1/event/${info.event.id}" ,
                    success: function (event) {
                          console.log("Usunieto EVENT");
                          $('#exampleModal').modal('hide');
                          location.reload();

                    }
                });

        }
</script>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">${info.event.title}</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-footer">
                                <a class="btn btn-danger" data-toggle="modal" onclick="deleteEvent()">Usuń wydarzenie</a>
                              </div>
                            </div>
                          </div>

                                </div>

`);

            $('#exampleModal').modal('show');


        },





    });

    CalendarAdmin.render();
});

// const encodeFormData = (data) => {
//     var form_data = new FormData();
//
//     for (var key in data) {
//         form_data.append(key, data[key]);
//     }
//     return form_data;
// }

