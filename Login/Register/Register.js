// Taking User Input
const username  = document.querySelector("#rusername");
const password = document.querySelector("#rpassword");
const email = document.querySelector("#remail");
const submit = document.querySelector(`#Submit`);

var passSuccess = false;

//Test
const getValues = (username,password,email ) => {
    console.log("username: "+username,"password:"+password,"email:"+email);
    if (username == "" || password == "" || email == "") {
      alert("All fields need to be filled out");
      return false;
    }
    else{
        return passSuccess=true;
    }
}

//this stops the submit button doing its default actions, we then run are get values method which prints out in the console the values.
const rSubmit=(e) =>{
    e.preventDefault();
    getValues(username.value,password.value,email.value);
    if (passSuccess == true){
    alert("Welcome to Circle")
    window.location.href ="http://127.0.0.1:5500/Login/Login.html"
    
    }
    else{
    alert("Registration Unsuccesful")
    }
}

submit.addEventListener("click",rSubmit);