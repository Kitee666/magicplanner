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


    var calendar = new Calendar(calendarEl, {
        locale: 'pl',
        firstDay: 1,
        forceEventDuration: true,
        defaultTimedEventDuration: '02:00:00',
        events: api_calendar_url,
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
        drop: function (info) {
            // is the "remove after drop" checkbox checked?
            if (checkbox.checked) {
                // if so, remove the element from the "Draggable Events" list
                info.draggedEl.parentNode.removeChild(info.draggedEl);
            }
        },

        // eventReceive: function(info) {
        //
        //     //get the bits of data we want to send into a simple object
        //
        //     var eventData = {
        //         // title: info.event.title,
        //         "dateFrom": info.event.start,
        //         "dateTo": info.event.end,
        //         "connector_id": 1,
        //         "room_id": 1
        //     };
        //     // console.log(eventData);
        //     //send the data via an AJAX POST request, and log any response which comes from the server
        //     fetch('http://localhost:8080/api/v1/event', {
        //         method: 'POST',
        //         headers: {
        //             'Accept': 'application/json',
        //             'Content-Type': 'application/json'
        //         },
        //         body: encodeFormData(eventData)
        //     })
        //         .then(response => console.log(response))
        //         .catch(error => console.log(error));
        // },

        eventDragStop: function(info) {
            $("#eventModal").html("");
            $("#eventModal").append(`<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Czy chcesz zmienić długość bloku?</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                               Nowa data to od: ${info.event.start.toISOString()}
                               do: ${info.event.end.toISOString()}
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Anuluj</button>
                                <button type="button" class="btn btn-primary">Zapisz zmiany</button>
                              </div>
                            </div>
                          </div>
                                </div>`);

            $('#exampleModal').modal('show');
        },
        eventResize: function(info) {
            $("#eventModal").html("");
            $("#eventModal").append(`<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Czy chcesz zmienić długość bloku?</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                               Nowa data to: ${info.event.end.toISOString()}
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Anuluj</button>
                                <button type="button" class="btn btn-primary">Zapisz zmiany</button>
                              </div>
                            </div>
                          </div>
                                </div>`);

            $('#exampleModal').modal('show');

        },

        eventClick: function(info) {
            // alert('Event: ' + info.event.title +
            //     'From' + info.event.start +
            //     'To' + info.event.end);

            $("#eventModal").html("");
            $("#eventModal").append(`<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">${info.event.title}</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                              </div>
                              <div class="modal-body">
                               
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                              </div>
                            </div>
                          </div>
                                </div>`);

            $('#exampleModal').modal('show');
        },





    });

    calendar.render();
});

// const encodeFormData = (data) => {
//     var form_data = new FormData();
//
//     for (var key in data) {
//         form_data.append(key, data[key]);
//     }
//     return form_data;
// }

