const username  = document.querySelector("#lusername");
const password = document.querySelector("#lpassword");
const submit = document.querySelector(`#Submit`);

var passSuccess = false;
//this stops the submit button doing its default actions, we then run are get values method which prints out in the console the values.
const rSubmit=(e) =>{
    e.preventDefault();
    getValues(username.value,password.value);
    if (passSuccess == true){
    Login(e);
    alert("Checking Details")
    }
    else{
    alert("Login Unsuccesful")
    }
}

submit.addEventListener("click",rSubmit);

//Checking details have been entered correctly
const getValues = (username,password,email ) => {
    if (username == "" || password == "") {
      alert("All fields need to be filled out");
      return false;
    }
    else{
        return passSuccess=true;
    }
}

function Login(e){
e.preventDefault();
const req = new XMLHttpRequest();
try{
req.open("POST", "http://localhost:8080/customer/login");
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
