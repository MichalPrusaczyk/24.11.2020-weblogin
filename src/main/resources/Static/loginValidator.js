function validate() {
    var login = document.forms["loginForm"]["login"].value;
    var pass = document.forms["loginForm"]["pass"].value;

    var regex = new RegExp(/.{3}.*/);
    var flag = true;

    if(!regex.test(login)) {
        document.forms["loginForm"]["login"].style.background = "#eec4c4";
        flag = false;
    } else {
        document.forms["loginForm"]["login"].style.background = "white";
    }

    if(!regex.test(pass)) {
        document.forms["loginForm"]["pass"].style.background = "#eec4c4";
        flag = false;
    } else {
        document.forms["loginForm"]["pass"].style.background = "white";
    }

    return flag;
}