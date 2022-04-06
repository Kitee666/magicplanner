$(document).ready(function(){ // START
    $('.userElement').hide(); // hide all inputs

    $("#changeLogin").click(function () { // show login change option
        $('.userElement').hide();
        $("#LoginContainer").show();
    });
    $("#changePassword").click(function () { //show password change option
        $('.userElement').hide();
        $("#passwordContainer").show();
    });
    /**
     * BUTTONS - ON CLICK OPTION WITH ACTION
     * wszystko ponizej jest odpowiedzialne za zmienianie ustawien uzytkownika (np. login, haslo)
     * */

    $("#getLoginButton").click(function() { // change login
        let getOldLoginInput = $("#getOldLogin").val();
        let getNewLoginInput = $("#getNewLogin").val();
        const LoginJSON = jQuery.parseJSON( '{ "OldLogin": "'+getOldLoginInput+'","NewLogin": "'+getNewLoginInput+'" }' );
        console.log(JSON.stringify(LoginJSON));
    });

    $("#getPasswordButton").click(function() { // change login
        let getOldPasswordInput = $("#getOldPassword").val();
        let getNewPasswordInput = $("#getNewPassword").val();
        const LoginJSON = jQuery.parseJSON( '{ "OldPassword": "'+getOldPasswordInput+'","NewPassword": "'+getNewPasswordInput+'" }' );
        console.log(JSON.stringify(LoginJSON));
    });
});