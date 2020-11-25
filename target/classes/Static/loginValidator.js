function validate(){
    var login = document.forms["loginForm"]["login"].value;
    var pass = document.forms["loginForm"]["pass"].value;

    var regex = new RegExp(/^.{3}.*/);

        var flag = true;

       if(!regex.test(login)){
              document.forms["loginForm"]["login"].style.background = "red";
        flag = false;
       }else{
       document.forms["loginForm"]["login"].style.background = "blue";
       }

       if(!regex.test(pass)){
              document.forms["loginForm"]["login"].style.background = "red";
                flag = false;
       }else{
       document.forms["loginForm"]["login"].style.background = "blue";
       }

       return flag;
}