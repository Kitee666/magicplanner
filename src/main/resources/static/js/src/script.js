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

        $("#SubjectResult").text(getSubjectNameInput + " " + SelectLecturerInput + " " + SelectGroupInput);
    });
    $("#getLecturerButton").click(function() { // adding new lecturer - tmp output in line below
        let getLecturerNameInput = $("#getLecturerName").val();
        let getLecturerLastNameInput = $("#getLecturerLastName").val();
        let getLecturerSubjectInput = $("#getLeturerSubject").val(); // !IMPORTANT -> RED FAL
        $("#LecturerResult").text(getLecturerNameInput + " " + getLecturerLastNameInput + " " + getLecturerSubjectInput);
    });
    $("#getGroupButton").click(function() {
        let getGroupTypeInput = $("#getGroupType").val();
        let getGroupYearInput = $("#getGroupYear").val();
        let getGroupSizeInput = $("#getGroupSize").val();
        $("#GroupResult").text(getGroupTypeInput + ", " + getGroupYearInput + ", " + getGroupSizeInput);
    });
    $("#getMeetingButton").click(function() {
        let getMeetingStartDateInput = $("#dateStart").val();
        let getMeetingEndDateInput = $("#dateEnd").val();
        $("#MeetingResult").text(getMeetingStartDateInput + " to " + getMeetingEndDateInput);
    });
    $("#getRoomButton").click(function() {
        let getRoomInput = $("#getRoomName").val();
        $("#RoomResult").text(getRoomInput);
    });
    $("#getNoteButton").click(function() {
        let getNoteTitle = $("#getNoteTitle").val();
        let getNoteInput = $("#getNoteInput").val();
        $("#NoteResult").text(getNoteTitle + " " + getNoteInput);
    });

});