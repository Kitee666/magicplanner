const api_connector_add = "/api/v1/connector/bulk"
$(document).ready(function () { // START
    $('.inputElement').hide(); // hide all inputs
    /**
     * HIDE AND SHOW
     * Na klikniecie odpowiednio zmieniaja sie widoki -> to jest do usprawnienia/poprawienia w zaleznosci od stylowania samej strony
     * */
    $("#addSubject").click(function () { // show subject - adding options
        $('.inputElement').hide();
        $("#SubjectContainer").show();
    });
    $("#addLecturer").click(function () { // show lecturer - adding options
        $('.inputElement').hide();
        $("#LecturerContainer").show();
    });
    /*
    $("#addGroup").click(function () { // show group - adding options
        $('.inputElement').hide();
        $("#GroupContainer").show();
    });
    */
    $("#addMeeting").click(function () { // show metting - adding options
        $('.inputElement').hide();
        $("#MeetingContainer").show();
    });
    $("#addRoom").click(function () { // show room - adding options
        $('.inputElement').hide();
        $("#RoomContainer").show();
    });
    $("#addNote").click(function () { // show note - adding options
        $('.inputElement').hide();
        $("#NoteContainer").show();
    });

    // Here starts Code for adding multiple groups for object at once
    let GroupLecturerJSON = [];
    let GroupRoomJSON = [];
    var id = 0;

    $("#addGroupButton").click(function () { // add group input fields and push them to array

        id++;


        $("#GroupContainer").append($(`<div class='group-item'><h3>Dodaj Grupę ${id}</h3>
                        <label>Ilosc godzin dla grupy</label>
                        <div class="inputField">
                            <input type="text" class="form-control GroupElements" id="getGroupHours" placeholder="ilosc godzin">
                        </div>
                        <label>Nazwa Grupy</label>
                        <select name="GroupName" class="form-control GroupElements"  id="getGroupName">
                            <option>Wszyscy</option>
                            <option>Grupa 1</option>
                            <option>Grupa 2</option>
                            <option>Grupa 3</option>
                            <option>Grupa 4</option>
                            <option>Grupa 5</option>
                            <option>Grupa 6</option>
                        </select>
                        <label>Rozmiar Grupy</label>
                        <div class="inputField">
                            <input type="text" class="form-control GroupElements" id="getGroupSize" placeholder="ilosc osob">
                        </div>
                        <label>Typ Grupy</label>
                        <select name="GroupType" class="form-control GroupElements" id="getGroupType">
                            <option value = "WYKLAD">Wykład</option>
                            <option value = "LAB">Laboratoria</option>
                            <option value = "NIEST">Niestandardowa</option>
                        </select>
                        <label>Rok Studiów</label>
                        <select name="Year" class="form-control GroupElements" id="getGroupYear">
                            <option value = "ROK_I">Rok 1</option>
                            <option value = "ROK_II">Rok 2</option>
                            <option value = "ROK_III">Rok 3</option>
                            <option value = "ROK_IV">Rok 4</option>
                        </select>
                        <label>Wykładowca grupy</label>
                        <div id="SelectGroupLecturer" class="test1">
                            <select id=\"selectLec${id}\"                            class="form-select display">
                                <option value = ""></option>
                            </select>
                        </div>
                        <label>Sala Grupy</label>
                        <div id="SelectGroupRoom" class="test2">
                            <select id=\"selectRm${id}\"                                   class="form-select display">
                                <option value = ""></option>
                            </select>
                        </div></div>`));

        $("#GroupContainer").show();

        /////////////////////////////////SELECTS DISPLAY(IN GROUPS INPUT)//////////////////////////////
        ////LECTURER SELECT////
        $(document).ready(function () {
            let selectL = $('#selectLec' + id);

            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: api_lecturer,
                success: function (lecturers) {
                    console.log(lecturers)
                    $.each(lecturers, function (i, item) {
                        let option = $("<option>");
                        option.text(item.name + " " + item.lastname + " " + item.title);
                        option.attr("value", item.id);
                        selectL.append(option);
                    });
                }
            });
        });

        ////ROOM SELECT////
        $(document).ready(function () {
            let selectR = $('#selectRm' + id);

            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: "http://localhost:8080/api/v1/room",
                success: function (room) {
                    console.log(room)

                    $.each(room, function (i, item) {
                        let option = $("<option>");
                        option.text(item.number);
                        option.attr("value", item.id);
                        selectR.append(option);
                    });
                }
            });
        });
    });
    /**
     * BUTTONS - ON CLICK OPTION WITH ACTION
     * wszystko ponizej jest odpowiedzialne za dodawanie elementow do bazy danych i wyswietlanie elementow pobranych z bazy danych
     * */
    $("#getSubjectButton").click(function () {
        let subject = {
            "name": $("#getSubjectName").val(),
            "hours": $("#getSubjectHours").val(),
            "year": $("#getSubjectYear :selected").val()
        };
        let groups = [];
        console.log(subject);
        console.log(groups);
        let gc = $("#GroupContainer > .group-item")
        gc.each(function (index, elem) {
            groups.push({
                "hours": $(elem).find("#getGroupHours").val(),
                "name": $(elem).find("#getGroupName :selected").val(),
                "size": $(elem).find("#getGroupSize").val(),
                "type": $(elem).find("#getGroupType :selected").val(),
                "yearType": $(elem).find("#getGroupYear :selected").val(),
                "lecturer_id": $(elem).find("#SelectGroupLecturer :selected").val(),
                "room_id": $(elem).find("#SelectGroupRoom :selected").val()
            });
        })
        console.log(groups);
        let data = JSON.stringify({
            "subject": subject,
            "groups": groups
        })
        console.dir(data);
        $.ajax({
            type: 'POST',
            url: api_connector_add,
            contentType: "application/json",
            dataType: 'json',
            data: JSON.stringify({
                "subject": subject,
                "groups": groups,
                "lecturer": $("#selectLec1 :selected").val(),
                "room":  $("#SelectGroupRoom :selected").val()
            }),
            success: function (r) {
                console.log(r);
                $('#GroupTable').DataTable().ajax.reload();
                $('#SubjectTable').DataTable().ajax.reload();
            },
            error: function (e){
                console.dir(e);
                $('#GroupTable').DataTable().ajax.reload();
                $('#SubjectTable').DataTable().ajax.reload();
                //$('#SubjectResult').text("Nie udalo sie dodac do bazy danych: Przedmioty");
                //$("#SubjectResult").css("color", "red");
                //$('#GroupResult').text("Nie udało się dodać do bazy danych: Grupy");
                //$("#GroupResult").css("color", "red");
                $('#SubjectResult').text("Pomyślnie dodano do bazy danych: Przedmioty");
                $("#SubjectResult").css("color", "#00cb20");
                $('#GroupResult').text("Pomyślnie dodano do bazy danych: Grupy");
                $("#GroupResult").css("color", "#00cb20");
                /// after adding succesfully to DB still come to error
            }
        });

    });
    // $("#getSubjectButton").click(function() { // adding new lecturer - tmp output in line below
    //     let getSubjectHoursInput = $("#getSubjectHours").val();
    //     let getSubjectNameInput = $("#getSubjectName").val();
    //     let getSubjectYearInput = $("#getSubjectYear").val();
    //
    //     const SubjectJSON = jQuery.parseJSON( '{ "hours": '+getSubjectHoursInput+', "name": "'+getSubjectNameInput+'","year": "'+getSubjectYearInput+'"}' );
    //     console.log(JSON.stringify(SubjectJSON)); //json for subject inputs
    //
    //     $( ".test1" ).each(function( index ) {
    //         console.log($( this ).val());
    //         GroupLecturerJSON.push($( this ).val());
    //     });
    //
    //     $( ".test2" ).each(function( index ) {
    //         console.log($( this ).val());
    //         GroupRoomJSON.push($( this ).val());
    //     });
    //     console.log(GroupLecturerJSON);
    //     console.log(GroupRoomJSON);
    //
    //     ///////// Subject Post'ing Data
    //
    //     $.ajax({
    //         type: 'POST',
    //         dataType: 'json',
    //         contentType: 'application/json; charset=UTF-8',
    //         url: "http://localhost:8080/api/v1/subject",
    //         data: JSON.stringify(SubjectJSON),
    //         //data: JSON.stringify({"hours": getSubjectHoursInput,"name": getSubjectNameInput,"year": getSubjectYearInput}),
    //         success : function(subject){
    //             console.log(subject)
    //             $('#SubjectResult').text("Pomyślnie dodano do bazy danych: Przedmioty");
    //             $("#SubjectResult").css("color", "#00cb20");
    //         },
    //         error : function(subject){
    //             console.log("Subject POST Nie dziala");
    //             $('#SubjectResult').text("Nie udalo sie dodac do bazy danych: Przedmioty");
    //             $("#SubjectResult").css("color", "red");
    //         }
    //     });
    //
    //     //////Group Post'ing Data
    //
    //     var groupEL = 1;
    //     let getGroupHoursInput;
    //     let getGroupNameInput;
    //     let getGroupSizeInput;
    //     let getGroupTypeInput;
    //     let getGroupYearTypeInput;
    //
    //     $( ".GroupElements" ).each(function( index ) {
    //         if (groupEL == 1){
    //             getGroupHoursInput = $( this ).val();
    //         }
    //         if (groupEL == 2){
    //             getGroupNameInput = $( this ).val();
    //         }
    //         if (groupEL == 3){
    //             getGroupSizeInput = $( this ).val();
    //         }
    //         if (groupEL == 4){
    //             getGroupTypeInput = $( this ).val();
    //         }
    //         if (groupEL == 5){
    //             getGroupYearTypeInput = $( this ).val();
    //             const GroupJSON = jQuery.parseJSON( '{ "hours": '+getGroupHoursInput+',"name": "'+getGroupNameInput+'","size": '+getGroupSizeInput+',"type": "'+getGroupTypeInput+'","yearType": "'+getGroupYearTypeInput+'"}' );
    //             console.log(JSON.stringify(GroupJSON));
    //
    //             $.ajax({
    //                 type: 'POST',
    //                 dataType: 'json',
    //                 contentType: 'application/json; charset=UTF-8',
    //                 url: "http://localhost:8080/api/v1/group",
    //                 data: JSON.stringify(GroupJSON),
    //                 success : function(group){
    //                     console.log(group)
    //                     $('#GroupResult').text("Pomyślnie dodano do bazy danych: Grupy");
    //                     $("#GroupResult").css("color", "#00cb20");
    //                 },
    //                 error : function(group){
    //                     console.log("Group POST Nie dziala");
    //                     $('#GroupResult').text("Nie dodano do bazy danych: Grupy");
    //                     $("#GroupResult").css("color", "red");
    //                 }
    //             });
    //
    //             groupEL = 0;
    //         }
    //         groupEL++;
    //     });
    //     $("#GroupContainer").html(""); // json for groups inputs
    //
    // });
    $("#getLecturerButton").click(function () { // adding new lecturer - tmp output in line below

        let getLecturerNameInput = $("#getLecturerName").val();
        let getLecturerLastNameInput = $("#getLecturerLastName").val();
        let getLecturerDegreeInput = $("#getLecturerDegree").val(); // !IMPORTANT -> RED FAL
        //const LecturerJSON = jQuery.parseJSON( '{ "name": "'+getLecturerNameInput+'","lastname": "'+getLecturerLastNameInput+'","title": "'+getLecturerDegreeInput+'" }' );
        //console.log(JSON.stringify(LecturerJSON));

        $.ajax({
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            url: "http://localhost:8080/api/v1/lecturer",
            data: JSON.stringify({
                "name": getLecturerNameInput,
                "lastname": getLecturerLastNameInput,
                "title": getLecturerDegreeInput
            }),
            success: function (lecturer) {
                console.log(lecturer)
                $('#LecturerResult').text("Pomyślnie dodano do bazy danych: Wykladowcy");
                $("#LecturerResult").css("color", "#00cb20");
                $('#LecturerTable').DataTable().ajax.reload();
            },
            error: function (lecturer) {
                console.log("Lecturer POST Nie dziala");
                $('#LecturerResult').text("Nie udalo sie dodac do bazy danych: Wykladowcy");
                $("#LecturerResult").css("color", "red");
            }
        });
    });
    /*
    $("#getGroupButton").click(function() {
        let getGroupTypeInput = $("#getGroupType").val();
        let getGroupSizeInput = $("#getGroupSize").val();
        let getGroupYearInput = $("#getGroupYear").val();
        let getGroupHoursInput = $("#getGroupHours").val();
        let getGroupNameInput = $("#getGroupName").val();
        let SelectGroupLecturerInput = $("#SelectGroupLecturer option:selected").val();
        let SelectGroupRoomInput = $("#SelectGroupRoom option:selected").val();
        const GroupJSON = jQuery.parseJSON( '{ "group_type": "'+getGroupTypeInput+'", "group_size": "'+getGroupSizeInput+'", "group_year": "'+getGroupYearInput+'", "group_hours": "'+getGroupHoursInput+'", "group_name": "'+getGroupNameInput+'", "group_lecturer": "'+SelectGroupLecturerInput+'", "group_room": "'+SelectGroupRoomInput+'", }' );

        console.log(JSON.stringify(GroupJSON));
    });
    */
    $("#getMeetingButton").click(function () {
        let getMeetingStartDateInput = $("#dateStart").val();
        let getMeetingEndDateInput = new Date(getMeetingStartDateInput);
        getMeetingEndDateInput.setDate(getMeetingEndDateInput.getDate() + 1); // something like that XD
        getMeetingEndDateInput = getMeetingEndDateInput.getFullYear() + '-' + (getMeetingEndDateInput.getMonth() < 10 ? '0' : '') + (getMeetingEndDateInput.getMonth() + 1) + '-' + (getMeetingEndDateInput.getDate() < 10 ? '0' : '') + getMeetingEndDateInput.getDate(); // date with leading zeros (i know it looks like shit...)
        //const MeetingJSON = jQuery.parseJSON( '{ "date_from": "'+getMeetingStartDateInput+'","date_to": "'+getMeetingEndDateInput+'" }' );
        //console.log(JSON.stringify(MeetingJSON));

        $.ajax({
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            url: "http://localhost:8080/api/v1/meeting",
            data: JSON.stringify({"dateFrom": getMeetingStartDateInput, "dateTo": getMeetingEndDateInput}),
            success: function (meeting) {
                console.log(meeting)
                $('#MeetingResult').text("Pomyślnie dodano do bazy danych: Spotkania");
                $("#MeetingResult").css("color", "#00cb20");
                $('#MeetingTable').DataTable().ajax.reload();
            },
            error: function (meeting) {
                console.log("Meeting POST Nie dziala");
                $('#MeetingResult').text("Nie dodano do bazy danych: Spotkania");
                $("#MeetingResult").css("color", "red");
            }
        });
    });

    $("#getNoteButton").click(function () {
        let getNoteTitle = $("#getNoteTitle").val();
        let getNoteInput = $("#getNoteInput").val();
        const NotesJSON = jQuery.parseJSON('{ "title": "' + getNoteTitle + '","content": "' + getNoteInput + '" }');
        console.log(JSON.stringify(NotesJSON));

    });
    $("#getRoomButton").click(function () {
        let getRoomName = $("#getRoomNumber").val();
        let getRoomSize = $("#getRoomSize").val();
        //const RoomJSON = jQuery.parseJSON( '{ "number": "'+getRoomName+'", "size": "'+getRoomSize+'" }' );
        //console.log(JSON.stringify(RoomJSON));

        $.ajax({
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            url: "http://localhost:8080/api/v1/room",
            data: JSON.stringify({"number": getRoomName, "size": getRoomSize}),
            success: function (room) {
                console.log(room);
                $('#RoomResult').text("Pomyślnie dodano do bazy danych");
                $("#RoomResult").css("color", "#00cb20");
                $('#RoomTable').DataTable().ajax.reload();
            },
            error: function (room) {
                console.log("Nie dziala");
                $('#RoomResult').text("Nie dodano do bazy danych");
                $("#RoomResult").css("color", "red");
            }
        });
    });
});

/////// Validation ///////
function allowOnlyNormal(e, t) // allow only letters and numbers
{
    if (window.event)
    {
        var charCode = window.event.keyCode;
    }
    else if (e)
    {
        var charCode = e.which;
    }
    else { return true; }
    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode > 47 && charCode < 58) || charCode==32 ||
        charCode==261 || charCode==263 || charCode==281 || charCode==322 || charCode==324 || charCode==243 || charCode==347 || charCode==378 || charCode==380 ||
        charCode==260 || charCode==262 || charCode==280 || charCode==321 || charCode==323 || charCode==211 || charCode==346 || charCode==377 || charCode==379)
        return true;
    else
    {
        alert("Proszę tylko wpisywać litery i liczby");
        return false;
    }
}

function allowOnlyNumbers(e, t) // allow only numbers
{
    if (window.event)
    {
        var charCode = window.event.keyCode;
    }
    else if (e)
    {
        var charCode = e.which;
    }
    else { return true; }
    if (charCode > 47 && charCode < 58)
        return true;
    else
    {
        alert("Proszę tylko wpisywać liczby");
        return false;
    }
}

function allowOnlyLetters(e, t) // allow only letters
{
    if (window.event)
    {
        var charCode = window.event.keyCode;
    }
    else if (e)
    {
        var charCode = e.which;
    }
    else { return true; }
    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode==32 ||
        charCode==261 || charCode==263 || charCode==281 || charCode==322 || charCode==324 || charCode==243 || charCode==347 || charCode==378 || charCode==380 ||
        charCode==260 || charCode==262 || charCode==280 || charCode==321 || charCode==323 || charCode==211 || charCode==346 || charCode==377 || charCode==379)
        return true;
    else
    {
        alert("Proszę tylko wpisywać Litery");
        return false;
    }
}

/////////////////////////////////DATATABLES DISPLAY//////////////////////////////
////LECTURER DATATABLE////
$(document).ready(function () {
    $('#LecturerTable').DataTable({
        "ajax": {
            "url": api_lecturer,
            "type": "GET",
            "dataSrc": "",
        },
        "columns": [{
            data: "name"
        },
            {
                data: "lastname"
            },
            {
                data: "title"
            }
        ]
    });
});
////ROOM DATATABLE////
$(document).ready(function () {
    $('#RoomTable').DataTable({
        "ajax": {
            "url": "http://localhost:8080/api/v1/room",
            "type": "GET",
            "dataSrc": "",
        },
        "columns": [{
            data: "number"
        },
            {
                data: "size"
            }
        ]
    });
});
////MEETING DATATABLE////
$(document).ready(function () {
    $('#MeetingTable').DataTable({
        "ajax": {
            "url": "http://localhost:8080/api/v1/meeting",
            "type": "GET",
            "dataSrc": "",
        },
        "columns": [{
            data: "dateFrom"
        },
            {
                data: "dateTo"
            }
        ]
    });
});
/////SUBJECT DATATABLE////
$(document).ready(function () {
    $('#SubjectTable').DataTable({
        "ajax": {
            "url": "http://localhost:8080/api/v1/subject",
            "type": "GET",
            "dataSrc": "",
        },
        "columns": [{
            data: "name"
        },
            {
                data: "year"
            },
            {
                data: "hours"
            }
        ]
    });
});
/////GROUP DATATABLE////
$(document).ready(function () {
    $('#GroupTable').DataTable({
        "ajax": {
            "url": "http://localhost:8080/api/v1/group",
            "type": "GET",
            "dataSrc": "",
        },
        "columns": [{
            data: "hours"
        },
            {
                data: "name"
            },
            {
                data: "size"
            },
            {
                data: "type"
            },
            {
                data: "yearType"
            }
        ]
    });
});
/*
////NOTE DATATABLE////
$(document).ready(function() {
    $('#NoteTable').DataTable({
        "ajax": {
            "url": "http://localhost:8080/api/v1/note",
            "type": "GET",
            "dataSrc": "",
        },
        "columns": [{
            data: "title"
        },
            {
                data: "noteinput"
            }
        ]
    });
});
*/
//////////// REMOVE FROM DATABASE ///////////

///// REMOVE FROM METTING /////
$(document).ready(function() {
    //// SELECT MEETING ON PAGE
    var table = $('#MeetingTable').DataTable();
    var DeleteID;

    $('#MeetingTable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        DeleteID = table.row( this ).data().id;
        DeleteID = parseInt(DeleteID, 10);
        console.log(DeleteID);
    });
    ///DELETE SELECTED MEETING
    $('#cancelDeleteMeeting').click( function () {
        $('#ModalDeleteMeeting').modal('hide');
    });
    $('#confirmDeleteMeeting').click( function () {
        console.log(DeleteID);
        $.ajax({
            type: 'DELETE',
            dataType: 'json',
            url: "http://localhost:8080/api/v1/meeting/" + DeleteID,
            success: function (meeting) {
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteMeeting').modal('hide');
            },
            error: function (meeting) {
                console.log("error" + DeleteID);
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteMeeting').modal('hide');
                alert("Usunięto Spotkanie o id: " + DeleteID);
            }
        });
    });
} );

///// REMOVE FROM LECTURER /////
$(document).ready(function() {
    //// SELECT LECTURER ON PAGE
    var table = $('#LecturerTable').DataTable();
    var DeleteID;

    $('#LecturerTable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        DeleteID = table.row( this ).data().id;
        DeleteID = parseInt(DeleteID, 10);
        console.log(DeleteID);
    });
    ///DELETE SELECTED LECTURER
    $('#cancelDeleteLecturer').click( function () {
        $('#ModalDeleteLecturer').modal('hide');
    });
    $('#confirmDeleteLecturer').click( function () {
        console.log(DeleteID);
        $.ajax({
            type: 'DELETE',
            dataType: 'json',
            url: "http://localhost:8080/api/v1/lecturer/" + DeleteID,
            success: function (lecturer) {
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteLecturer').modal('hide');
            },
            error: function (lecturer) {
                console.log("error" + DeleteID);
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteLecturer').modal('hide');
                alert("Usunięto Wykładowcę o id: " + DeleteID);
            }
        });
    });
} );

///// REMOVE FROM ROOM /////
$(document).ready(function() {
    //// SELECT ROOM ON PAGE
    var table = $('#RoomTable').DataTable();
    var DeleteID;

    $('#RoomTable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        DeleteID = table.row( this ).data().id;
        DeleteID = parseInt(DeleteID, 10);
        console.log(DeleteID);
    });
    ///DELETE SELECTED ROOM
    $('#cancelDeleteRoom').click( function () {
        $('#ModalDeleteRoom').modal('hide');
    });
    $('#confirmDeleteRoom').click( function () {
        console.log(DeleteID);
        $.ajax({
            type: 'DELETE',
            dataType: 'json',
            url: "http://localhost:8080/api/v1/room/" + DeleteID,
            success: function (room) {
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteRoom').modal('hide');
            },
            error: function (room) {
                console.log("error" + DeleteID);
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteRoom').modal('hide');
                alert("Usunięto Salę o id: " + DeleteID);
            }
        });
    });
} );

///// REMOVE FROM SUBJECT /////
$(document).ready(function() {
    //// SELECT SUBJECT ON PAGE
    var table = $('#SubjectTable').DataTable();
    var DeleteID;

    $('#SubjectTable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        DeleteID = table.row( this ).data().id;
        DeleteID = parseInt(DeleteID, 10);
        console.log(DeleteID);
    });
    ///DELETE SELECTED SUBJECT
    $('#cancelDeleteSubject').click( function () {
        $('#ModalDeleteSubject').modal('hide');
    });
    $('#confirmDeleteSubject').click( function () {
        console.log(DeleteID);
        $.ajax({
            type: 'DELETE',
            dataType: 'json',
            url: "http://localhost:8080/api/v1/subject/" + DeleteID,
            success: function (subject) {
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteSubject').modal('hide');
            },
            error: function (subject) {
                console.log("error" + DeleteID);
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteSubject').modal('hide');
                alert("Usunięto Przedmiot o id: " + DeleteID);
            }
        });
    });
} );

///// REMOVE FROM GROUP /////
$(document).ready(function() {
    //// SELECT GROUP ON PAGE
    var table = $('#GroupTable').DataTable();
    var DeleteID;

    $('#GroupTable tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
        DeleteID = table.row( this ).data().id;
        DeleteID = parseInt(DeleteID, 10);
        console.log(DeleteID);
    });
    ///DELETE SELECTED GROUP
    $('#cancelDeleteGroup').click( function () {
        $('#ModalDeleteGroup').modal('hide');
    });
    $('#confirmDeleteGroup').click( function () {
        console.log(DeleteID);
        $.ajax({
            type: 'DELETE',
            dataType: 'json',
            url: "http://localhost:8080/api/v1/group/" + DeleteID,
            success: function (group) {
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteGroup').modal('hide');
            },
            error: function (group) {
                console.log("error" + DeleteID);
                table.row('.selected').remove().draw( false );
                $('#ModalDeleteGroup').modal('hide');
                alert("Usunięto Grupę o id: " + DeleteID);
            }
        });
    });
} );