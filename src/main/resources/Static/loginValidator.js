function validate(){
    var login = document.forms["loginForm"]["login"].value;
    var pass = document.forms["loginForm"]["pass"].value;

        var flag = true;

       if(login == ""){
              document.forms["loginForm"]["login"].style.background = "red";
                flag = false;
       }
       if(pass == ""){
              document.forms["loginForm"]["login"].style.background = "red";
                flag = false;
       }

       return flag;
}