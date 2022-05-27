var calendar;
document.addEventListener('DOMContentLoaded', function() {



    var Calendar = FullCalendar.Calendar;
   // var Draggable = FullCalendar.Draggable;

   // var containerEl = document.getElementById('external-events');
    var calendarEl = document.getElementById('calendar_user');
    var checkbox = document.getElementById('drop-remove');


    // initialize the external events
    // -----------------------------------------------------------------

  /*  new Draggable(containerEl, {
        itemSelector: '.fc-event',
        eventData: function(eventEl) {
            return {
                title: eventEl.innerText,



            };

        },
    }); */

    // initialize the calendar
    // -----------------------------------------------------------------


     calendar = new Calendar(calendarEl, {
        events:  {
            url: 'http://localhost:8080/api/v1/event/filtered',
            method: 'GET',
            extraParams: {
                year_type: yearVal,
                group_number: groupVal,
                group_type: groupWVal
            },
            failure: function() {
                alert('there was an error while fetching events!');
            },
            year: '1',   // a non-ajax option
            group: '1' // a non-ajax option
        },
        locale: 'pl',

        headerToolbar: {
            left: 'prev,next,today',
            center: 'title',
            right: 'dayGridMonth,timeGridTwoDays',

        },
        initialView: 'timeGridTwoDays',
        views: {
            timeGridTwoDays: {
                type: 'timeGrid',
                duration: { days: 31 },
                buttonText: 'Weekendy',
                hiddenDays: [ 1, 2, 3, 4, 5],
                weekday: 'long',
                slotDuration: '01:00:00',
                slotMinTime: '08:00:00',
                slotMaxTime: '20:00:00'
            },
            dayGridMonth: {
                buttonText: 'Widok miesiąca'
            },
        },
        buttonText: {
            today: 'Dzisiaj'
        },




        editable: false,
        droppable: false, // this allows things to be dropped onto the calendar
      /*  drop: function(info) {
            // is the "remove after drop" checkbox checked?
            if (checkbox.checked) {
                // if so, remove the element from the "Draggable Events" list
                info.draggedEl.parentNode.removeChild(info.draggedEl);
            }
        } */
    });

    calendar.render();



});