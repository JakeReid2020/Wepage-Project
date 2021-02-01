// Taking User Input
const username  = document.querySelector("#rusername");
const password = document.querySelector("#rpassword");
const email = document.querySelector("#remail");
const submit = document.querySelector(`#Submit`);


var passSuccess = false;
//this stops the submit button doing its default actions, we then run are get values method which prints out in the console the values.
const rSubmit=(e) =>{
    e.preventDefault();
    getValues(username.value,password.value,email.value);
    if (passSuccess == true){
    Register(e);
    alert("Welcome to Circle")
    window.location.href ="file:///C:/Users/jaker/Desktop/Wepage-Project/Login/Login.html"
    // This posts the information to my api as a json object.
    }
    else{
    alert("Registration Unsuccesful")
    }
}

submit.addEventListener("click",rSubmit);

//Checking details have been entered correctly
const getValues = (username,password,email ) => {
    if (username == "" || password == "" || email == "") {
      alert("All fields need to be filled out");
      return false;
    }
    else{
        return passSuccess=true;
    }
}

function Register(e){
e.preventDefault();
const req = new XMLHttpRequest();
try{
req.open("POST", "http://localhost:8080/customer/register");
}
catch(e){console.log("open failed " +e)
}
req.onload = () => {
  if (req.status === 201 && req.readyState == 4) {
    console.log("Server Responded with: " + req.responseText);
  } else {
    console.log("Failed to conneect to server");
  }
};
req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

let data = {
    "username" : username.value,
    "password" : password.value,
    "email" : email.value
   }
   try{
     console.log("Sending...");
    req.send(JSON.stringify(data));
    console.log("Sending...DONE");
}
catch(e){console.log("send failed " +e)
}
}
//submit.addEventListener("click", Register);
