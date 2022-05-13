
document.addEventListener('DOMContentLoaded', function() {

    var Calendar = FullCalendar.Calendar;
    var Draggable = FullCalendar.Draggable;

    var containerEl = document.getElementById('external-events');
    var calendarEl = document.getElementById('calendar');
    var checkbox = document.getElementById('drop-remove');

    // initialize the external events
    // -----------------------------------------------------------------

    new Draggable(containerEl, {
        itemSelector: '.fc-event',
        eventData: function(eventEl) {
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
        timeZone: 'pl',
        locale: 'pl',
        firstDay: 1,
        forceEventDuration: true,
        defaultTimedEventDuration: '02:00:00',




        headerToolbar: {
            left: 'prev,next,today',
            center: 'title',
            right: 'dayGridMonth,timeGridTwoDays',

        },
        initialView: 'timeGridTwoDays',
        views: {
            timeGridTwoDays: {
                type: 'timeGrid',
                duration: { days: 7 },
                buttonText: 'Weekendy',
                hiddenDays: [ 1, 2, 3, 4, 5],
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
        //
        // events: [
        //     {
        //         // id: 'a',
        //         // title: 'my event',
        //         // start: '2022-05-14T09:00:00+02:00',
        //         // end: '2022-05-14T11:00:00+02:00'
        //     }
        // ],



        // events: function (start, end, timezone, callback) {
        //     $.ajax({
        //         type: "GET",    //WebMethods will not allow GET
        //         url: "http://localhost:8080/api/v1/event",
        //         //completely take out 'data:' line if you don't want to pass to webmethod - Important to also change webmethod to not accept any parameters
        //         contentType: "application/json; charset=utf-8",
        //         dataType: "json",
        //         success: function (doc) {
        //             // console.log(doc);
        //             var events = [];   //javascript event object created here
        //
        //             var obj = doc;
        //             $(obj).each(function () {
        //                 console.log("doopa");
        //                 events.push({
        //                     title: $(this).attr('name'),  //your calevent object has identical parameters 'title', 'start', ect, so this will work
        //                     start: $(this).attr('dateFrom'), // will be parsed into DateTime object
        //                     end: $(this).attr('dateTo')
        //                 });
        //             });
        //             // console.log(events);
        //             // console.log(events);
        //             if(callback)
        //                 callback(events);
        //         }
        //     });
        // },

        // events: function(fetchInfo, successCallback, failureCallback) {
        //     $.ajax({
        //         url: 'http://localhost:8080/api/v1/event',
        //         type: 'GET',
        //         dataType: 'json',
        //         success: function(result) {
        //             console.log(result);
        //             successCallback(result.events);
        //
        //         }
        //     });
        // },

        events: 'http://localhost:8080/api/v1/event',



        // eventTimeFormat: {
        //     month: '2-digit',
        //     year: 'numeric',
        //     day: '2-digit'
        // },



        editable: true,
        droppable: true, // this allows things to be dropped onto the calendar
        drop: function(info) {
            // is the "remove after drop" checkbox checked?
            if (checkbox.checked) {
                // if so, remove the element from the "Draggable Events" list
                info.draggedEl.parentNode.removeChild(info.draggedEl);
            }
        },

        eventReceive: function(info) {

            //get the bits of data we want to send into a simple object

            var eventData = {
                // title: info.event.title,
                "dateFrom": info.event.start,
                "dateTo": info.event.end,
                "connector_id": 1,
                "room_id": 1
            };
            // console.log(eventData);
            //send the data via an AJAX POST request, and log any response which comes from the server
            fetch('http://localhost:8080/api/v1/event', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: encodeFormData(eventData)
            })
                .then(response => console.log(response))
                .catch(error => console.log(error));
        },

        eventClick: function(info) {
            alert('Event: ' + info.event.title +
                'From' + info.event.start +
                'To' + info.event.end);

        }


    });

    calendar.render();
});

const encodeFormData = (data) => {
    var form_data = new FormData();

    for (var key in data) {
        form_data.append(key, data[key]);
    }
    return form_data;
}

