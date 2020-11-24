function validate(){
    var login = document.forms["loginForm"]["login"].value;
    var pass = document.forms["loginForm"]["pass"].value;

       if(login == ""){
              document.forms["loginForm"]["login"].style.background = "red";
            return false;
       }
       if(pass == ""){
              document.forms["loginForm"]["login"].style.background = "red";
            return false;
       }
}