function validateData() {
    var name = document.forms["dataForm"]["name"].value;
    var surname = document.forms["dataForm"]["surname"].value;

    var regex = new RegExp(/[A-Z]{1}[A-Za-z]*/);
    var flag = true;

    if(!regex.test(name)) {
        document.forms["dataForm"]["name"].style.background = "#eec4c4";
        flag = false;
    } else {
        document.forms["dataForm"]["name"].style.background = "white";
    }

    if(!regex.test(surname)) {
        document.forms["dataForm"]["surname"].style.background = "#eec4c4";
        flag = false;
    } else {
        document.forms["dataForm"]["surname"].style.background = "white";
    }

    return flag;
}

function validatePass() {
    var currentPass = document.forms["passForm"]["currentPass"].value;
    var newPass = document.forms["passForm"]["newPass"].value;
    var newPass2 = document.forms["passForm"]["newPass2"].value;

    var regex = new RegExp(/.{3}.*/);
    var flag = true;

    if(!regex.test(currentPass)) {
        document.forms["passForm"]["currentPass"].style.background = "#eec4c4";
        flag = false;
    } else {
        document.forms["passForm"]["currentPass"].style.background = "white";
    }

    if(!regex.test(newPass)) {
        document.forms["passForm"]["newPass"].style.background = "#eec4c4";
        flag = false;
    } else {
        document.forms["passForm"]["newPass"].style.background = "white";
    }

    if(newPass != newPass2) {
        document.forms["passForm"]["newPass2"].style.background = "#eec4c4";
        flag = false;
    } else {
        document.forms["passForm"]["newPass2"].style.background = "white";
    }

    return flag;
}