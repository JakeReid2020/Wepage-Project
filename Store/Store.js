let Addtocart = document.querySelectorAll('#AddOrder');
const cart = document.querySelector('#Cart');

let products = [
    {
        name: 'Maths',
        tag: 'Maths',
        price:20,
        inCart:0
    },

    {
        name: 'Maths',
        tag: 'Maths',
        price:20,
        inCart:0
    },

    {
        name: 'Maths',
        tag: 'Maths',
        price:20,
        inCart:0
    },

    {

    },
]

for (let i=0; i<Addtocart.length; i++){
    Addtocart[i].addEventListener('click', () => {
        console.log("added to cart");
        Register();
    })
}






function Register(e){

    const req = new XMLHttpRequest();
    try{
    req.open("POST", "http://localhost:8080/item/create");
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
        "description" : "Pack of 40 Cards",
        "name" : "Escape",
        "price" : "20.00"
       }
       try{
         console.log("Sending...");
        req.send(JSON.stringify(data));
        console.log("Sending...DONE");
    }
    catch(e){console.log("send failed " +e)
    }
    }

    function ReadAll(){
    const req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/item/readAll");
    req.onload = () => {
      if (req.status === 200 && req.readyState == 4) {
        console.log("Server Responded with: " + req.responseText);
      } else {
        console.log("Oops...");
      }
    };
    req.send();
    }
    cart.addEventListener("click", ReadAll);


function cartNumbers(){
    let productNumebers = localStorage.getItem('cartNumbers');
 
    productNumbers = parseInt(productnumebers)
    
    localStorage.setItem('cartNumbers',1);
}

