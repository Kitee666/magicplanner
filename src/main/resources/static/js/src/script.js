/**
 *                      TEN JQUERY TO JEST CHUJ I CIPA -> NIC Z TEGO NIE BEDZIE -> ELO
 * */
$(document).ready(function(){ // START
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
    $("#addGroup").click(function () { // show group - adding options
        $('.inputElement').hide();
        $("#GroupContainer").show();
    });
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
    /**
     * BUTTONS - ON CLICK OPTION WITH ACTION
     * wszystko ponizej jest odpowiedzialne za dodawanie elementow do bazy danych i wyswietlanie elementow pobranych z bazy danych
     * */

    $("#getSubjectButton").click(function() { // adding new lecturer - tmp output in line below
        let getSubjectNameInput = $("#getSubjectName").val();
        let SelectLecturerInput = $("#SelectLecturer option:selected").val();// We need to get info about Lecturers from DataBase :) -> Select list to be used here
        let SelectGroupInput = $("#SelectGroup option:selected").val();// We need to get values about groups form DataBase :) -> Select Group to be used here
        ///$("#SubjectResult").text(getSubjectNameInput + " " + SelectLecturerInput + " " + SelectGroupInput);

        const SubjectJSON = jQuery.parseJSON( '{ "name": "'+getSubjectNameInput+'","lecturer_id": "'+SelectLecturerInput+'","group_id": "'+SelectGroupInput+'" }' );
        console.log(JSON.stringify(SubjectJSON));
    });
    $("#getLecturerButton").click(function() { // adding new lecturer - tmp output in line below

        let getLecturerNameInput = $("#getLecturerName").val();
        let getLecturerLastNameInput = $("#getLecturerLastName").val();
        let getLecturerSubjectInput = $("#getLeturerSubject").val(); // !IMPORTANT -> RED FAL
        const LecturerJSON = jQuery.parseJSON( '{ "name": "'+getLecturerNameInput+'","lastname": "'+getLecturerNameInput+'","subject": "'+getLecturerSubjectInput+'" }' );
        console.log(JSON.stringify(LecturerJSON));
    });
    $("#getGroupButton").click(function() {
        let getGroupTypeInput = $("#getGroupType").val();
        let getGroupYearInput = $("#getGroupYear").val();
        let getGroupSizeInput = $("#getGroupSize").val();
        ///$("#GroupResult").text(getGroupTypeInput + ", " + getGroupYearInput + ", " + getGroupSizeInput);
        const GroupJSON = jQuery.parseJSON( '{ "type": "'+getGroupTypeInput+'","rok": "'+getGroupYearInput+'","size": "'+getGroupSizeInput+'" }' );
        console.log(JSON.stringify(GroupJSON));
    });
    $("#getMeetingButton").click(function() {
        let getMeetingStartDateInput = $("#dateStart").val();
        let getMeetingEndDateInput = $("#dateEnd").val();
        // $("#RoomResult").text(getRoomInput);
        const MeetingJSON = jQuery.parseJSON( '{ "date_from": "'+getMeetingStartDateInput+'","date_to": "'+getMeetingEndDateInput+'" }' );
        console.log(JSON.stringify(MeetingJSON));
    });
    $("#getNoteButton").click(function() {
        let getNoteTitle = $("#getNoteTitle").val();
        let getNoteInput = $("#getNoteInput").val();
        const NotesJSON = jQuery.parseJSON( '{ "title": "'+getNoteTitle+'","content": "'+getNoteInput+'" }' );
        console.log(JSON.stringify(NotesJSON));
    });
    $("#getRoomButton").click(function() {
        let getRoomName = $("#getRoomName").val();
        const RoomJSON = jQuery.parseJSON( '{ "number": "'+getRoomName+'" }' );
        console.log(JSON.stringify(RoomJSON));
    });
});



/////////////////////////////////DATATABLES DISPLAY//////////////////////////////
////LECTURER DATATABLE////
$(document).ready(function() {
    $('#lecturertable').DataTable({
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
                data: "subject"
            }
        ]
    });
});

$(document).ready(function() {
    let select = $('#selectLec');

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: "http://localhost:8080/api/v1/lecturer",
        success : function(lecturers){
            console.log(lecturers)

            $.each(lecturers, function(i, item) {
                let option = $("<option>");
                option.text(item.name + " " + item.lastname);
                option.attr("value", item.id);
                select.append(option);
            });
        }
    });
});

$(document).ready(function() {
    let select = $('#selectGr');

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: "http://localhost:8080/api/v1/groups",
        success : function(groups){
            console.log(groups)

            $.each(groups, function(i, item) {
                let option = $("<option>");
                option.text(item.rok + " " + item.type);
                option.attr("value", item.id);
                select.append(option);
            });
        }
    });
});

$(document).ready(function() {
    let select = $('#selectGr');

    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: "http://localhost:8080/api/v1/groups",
        success : function(groups){
            console.log(groups)

            $.each(groups, function(i, item) {
                let option = $("<option>");
                option.text(item.rok + " " + item.type);
                option.attr("value", item.id);
                select.append(option);
            });
        }
    });
});
