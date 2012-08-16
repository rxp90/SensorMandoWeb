function passwordMatcher(){
    var correct = true;
    var password = document.getElementById("formCambioPassword:newPasswordInput");
    var check = document.getElementById("formCambioPassword:verifyPasswordInput");
    var passwordVal = password.value;
    var checkVal = check.value;
    
    if(passwordVal == ''){
        password.appendChild('<span class="control-group error">Please enter a password.</span>');
        correct = false;
    }else if (checkVal == '') {
        check.appendChild('<span class="control-group error">Please enter a password.</span>');
        correct = false;
    }else if (passwordVal != checkVal ) {
        var target = document.getElementById('target');
        target.innerHTML = "hola";
        correct = false;
    }
    return correct;
}