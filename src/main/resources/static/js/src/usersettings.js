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
     * Dodatko dodana została chujowa validacja...
     * */
    var currentLogin = "admin"; /// get value of current login
    var currentPassword = "password"; /// get value of current password

    $("#getLoginButton").click(function() { // change login
        let getOldLoginInput = $("#getOldLogin").val();
        let getNewLoginInput = $("#getNewLogin").val();
        let getConfirmNewLoginInput = $("#getConfirmNewLogin").val();
        //const LoginJSON = jQuery.parseJSON( '{ "OldLogin": "'+getOldLoginInput+'","NewLogin": "'+getNewLoginInput+'" }' );
        //console.log(JSON.stringify(LoginJSON));

        //////Login validation.....
        if(getOldLoginInput.length != 0 && getNewLoginInput.length != 0 && getConfirmNewLoginInput.length != 0) {
            if (getNewLoginInput == getConfirmNewLoginInput) {
                if (getNewLoginInput.length > 4) {
                    if (getOldLoginInput == currentLogin) {
                        //currentLogin = getNewLoginInput /// Change Login to new one!
                        $('#LoginResult').text('Pomyślnie zmieniono "Login"');
                        $("#LoginResult").css("color", "#00cb20");
                        $("#getOldLogin").css("border", "2px solid green");
                        $("#getNewLogin").css("border", "2px solid green");
                        $("#getConfirmNewLogin").css("border", "2px solid green");
                    } else {
                        $('#LoginResult').text('Podano błędnie "stary Login"');
                        $("#LoginResult").css("color", "red");
                        $("#getOldLogin").css("border", "2px solid red");
                        $("#getNewLogin").css("border-color", "gray");
                        $("#getConfirmNewLogin").css("border-color", "gray");
                    }
                } else {
                    $('#LoginResult').text('Login musi mieć przynajmniej 5 znaków');
                    $("#LoginResult").css("color", "red");
                    $("#getOldLogin").css("border-color", "gray");
                    $("#getNewLogin").css("border", "2px solid red");
                    $("#getConfirmNewLogin").css("border", "2px solid red");
                }
            } else {
                $('#LoginResult').text('Potwierdz nowy Login');
                $("#LoginResult").css("color", "red");
                $("#getOldLogin").css("border-color", "gray");
                $("#getNewLogin").css("border", "2px solid red");
                $("#getConfirmNewLogin").css("border", "2px solid red");
            }
        } else{
            $('#LoginResult').text('Wszystkie pola muszą być wypełnione');
            $("#LoginResult").css("color", "red");
            $("#getOldLogin").css("border", "2px solid red");
            $("#getNewLogin").css("border", "2px solid red");
            $("#getConfirmNewLogin").css("border", "2px solid red");
        }

    });

    $("#getPasswordButton").click(function() { // change login
        let getOldPasswordInput = $("#getOldPassword").val();
        let getNewPasswordInput = $("#getNewPassword").val();
        let getConfirmNewPasswordInput = $("#getConfirmNewPassword").val();
        //const LoginJSON = jQuery.parseJSON( '{ "OldPassword": "'+getOldPasswordInput+'","NewPassword": "'+getNewPasswordInput+'" }' );
        //console.log(JSON.stringify(LoginJSON));

        //////Password validation.....
        if(getOldPasswordInput.length != 0 && getNewPasswordInput.length != 0 && getConfirmNewPasswordInput.length != 0) {
            if (getNewPasswordInput == getConfirmNewPasswordInput) {
                if (getNewPasswordInput.length > 7) {
                    if (getOldPasswordInput == currentPassword) {
                        //currentPassword = getNewPasswordInput /// Change Password to new one!
                        $('#PasswordResult').text('Pomyślnie zmieniono "Hasło"');
                        $("#PasswordResult").css("color", "#00cb20");
                        $("#getOldPassword").css("border", "2px solid green");
                        $("#getNewPassword").css("border", "2px solid green");
                        $("#getConfirmNewPassword").css("border", "2px solid green");
                    } else {
                        $('#PasswordResult').text('Podano błędnie "Stare Hasło"');
                        $("#PasswordResult").css("color", "red");
                        $("#getOldPassword").css("border", "2px solid red");
                        $("#getNewPassword").css("border-color", "gray");
                        $("#getConfirmNewPassword").css("border-color", "gray");
                    }
                } else {
                    $('#PasswordResult').text('Hasło musi mieć przynajmniej 8 znaków');
                    $("#PasswordResult").css("color", "red");
                    $("#getOldPassword").css("border-color", "gray");
                    $("#getNewPassword").css("border", "2px solid red");
                    $("#getConfirmNewPassword").css("border", "2px solid red");
                }
            } else {
                $('#PasswordResult').text('Potwierdz nowe hasło');
                $("#PasswordResult").css("color", "red");
                $("#getOldPassword").css("border-color", "gray");
                $("#getNewPassword").css("border", "2px solid red");
                $("#getConfirmNewPassword").css("border", "2px solid red");
            }
        }else{
            $('#PasswordResult').text('Wszystkie pola muszą być wypełnione');
            $("#PasswordResult").css("color", "red");
            $("#getOldPassword").css("border", "2px solid red");
            $("#getNewPassword").css("border", "2px solid red");
            $("#getConfirmNewPassword").css("border", "2px solid red");
            }
    });
});