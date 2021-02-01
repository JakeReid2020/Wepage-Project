let carts = document.querySelectorAll('#AddOrder');
const cart = document.querySelector('#Cart');
const Delete = document.querySelector('#Delete');

const itemName  = document.querySelector("#name");
const description = document.querySelector("#description");
const price = document.querySelector("#price");
const id = document.querySelector("#id");
const submit = document.querySelector(`#Submit`);
const StoreItems = document.querySelector('#StoreItems');


//============================================(Counting Basket Items)
let products = [
    {
        "description" : "Pack of 40 Cards",
        "name" : "Maths",
        "price" : "20.00"
    },

    {
        "description" : "Pack of 40 Cards",
        "name" : "Sign Language",
        "price" : "20.00"
    },

    {
        "description" : "Pack of 40 Cards",
        "name" : "Ring Pong",
        "price" : "20.00"
    },

    {
        "description" : "Pack of 40 Cards",
        "name" : "Maths",
        "price" : "20.00"
    },

    {
        "description" : "Pack of 40 Cards",
        "name" : "Sign Language",
        "price" : "20.00"
    },

    {
        "description" : "Pack of 40 Cards",
        "name" : "Ring Pong",
        "price" : "20.00"
    },
];

//=======(CHECKS IF AN ITEM FROM THE LIST HAS BEEN CLICKED AND RUNS METHODS)
for (let i=0; i<carts.length; i++){
    carts[i].addEventListener('click', () => {
        cartNumbers(products[i]);
    })
}

//=======(LOADS THE WEBPAGE STORED NUMBER OF ITEMS)
function onLoadCartNumbers(){
    let productNumbers = localStorage.getItem('cartNumbers');

    if(productNumbers){
        document.querySelector('#Cart span').textContent = productNumbers;
    }
}

//=======(INCREMENTS THE NUMBER OF ITEMS IN THE BASKET)
function cartNumbers(product){
    let productNumbers = localStorage.getItem('cartNumbers');
    productNumbers = parseInt(productNumbers);
    
    if(productNumbers) {
        localStorage.setItem('cartNumbers',productNumbers + 1);
        document.querySelector('#Cart span').textContent = productNumbers + 1;
    }
    else{
        localStorage.setItem('cartNumbrs', 1);
        document.querySelector('#Cart span').textContent = 1; 
    }
    
    setItems(product)
}
function setItems(product){
    Register(product)
}



//=======(LOADS THE WEBPAGE STORED NUMBER OF ITEMS)
onLoadCartNumbers();


//============================================(Craeting An Item)
function Register(product){ 
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
       try{
         console.log("Sending...");
         console.log("The product clicked is", product);
        req.send(JSON.stringify(product));
        console.log("Sending...DONE");
    }
    catch(e){console.log("send failed " +e)
    }
    }

    //============================================(Update An Item)
    const updateItem = (e) => {
      e.preventDefault();
      itemupdated = ({
        "name": itemName.value,
        "description": description.value,
        "price":price.value
      })
      const req = new XMLHttpRequest();
    try{
    req.open("PUT", "http://localhost:8080/item/update/"+ id.value);
    }
    catch(e){console.log("open failed " +e)
    }
    req.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
       try{
         console.log("Sending...");
         console.log("The product clicked is", itemupdated);
        req.send(JSON.stringify(itemupdated));
        console.log("Sending...DONE");
    }
    catch(e){console.log("send failed " +e)
    }
  }
  submit.addEventListener("click", updateItem);
 //============================================(SELECTING ITEM TO DELETE) 
 

 //============================================(Read All Items In Cart)
    function ReadAll(){
    const req = new XMLHttpRequest();
    req.open("GET", "http://localhost:8080/item/readAll");
   req.onload = () => {
          if (req.status === 200 && req.readyState == 4) {
            console.log("Server Responded with: " + req.responseText);
          } 
        };
        req.send();
    };
    
    cart.addEventListener("click", ReadAll);

//============================================(Delete Item In Cart)
    function DeleteItem(){
        const req = new XMLHttpRequest();
        let id = 1;
        req.open("DELETE", "http://localhost:8080/item/delete/" + id);
        req.onload = () => {
          if (req.status === 200 && req.readyState == 4) {
            console.log("Server Responded with: " + req.responseText);
          } 
        };
        req.send();
    }
    Delete.addEventListener("click", DeleteItem);

    