function clearErrors() {    
    for (var loopCounter = 0; 
        loopCounter < document.forms["evensTrouble"].elements.length; 
        loopCounter++) {
        if (document.forms["evensTrouble"].elements[loopCounter]
           .parentElement.className.indexOf("has-") != -1) {
            
            document.forms["evensTrouble"].elements[loopCounter]
               .parentElement.className = "form-group";
        }
    }    
}
function resetForm() {
    clearErrors();
    document.forms["evensTrouble"]["startNum"].value = "";
    document.forms["evensTrouble"]["endNum"].value = "";
    document.forms["evensTrouble"]["stepNum"].value = "";
    document.getElementById("results").style.display = "none";
    document.getElementById("submitButton").innerText = "Display Evens";
    document.forms["evensTrouble"]["startNum"].focus();
}
function validateItems() {
    clearErrors();
    var num1 = +document.forms["evensTrouble"]["startNum"].value;
    var num2 = +document.forms["evensTrouble"]["endNum"].value;
    var num3 = +document.forms["evensTrouble"]["stepNum"].value;   
    //var numResults= document.getElementById("numResults");
    var ar = [];
    if (num1 == "" || isNaN(num1)) {
        alert("Starting Number must be filled in with a number.");
        document.forms["evensTrouble"]["startNum"]
           .parentElement.className = "form-group has-error";
        document.forms["evensTrouble"]["startNum"].focus();
        return false;
    }
   if (num2 == "" || isNaN(num2)) {
       alert("Ending Number must be filled in with a number.");
       document.forms["evensTrouble"]["endNum"]
          .parentElement.className = "form-group has-error"
       document.forms["evensTrouble"]["endNum"].focus();
       return false;
   }
   if (num3 == "" || isNaN(num3)) {
    alert("Step number must be filled in with a number.");
    document.forms["evensTrouble"]["stepNum"]
       .parentElement.className = "form-group has-error"
    document.forms["evensTrouble"]["stepNum"].focus();
    return false;
   }  
   if (num3 < 0) {
    alert("Step number must be greater than zero.");
    document.forms["evensTrouble"]["stepNum"]
       .parentElement.className = "form-group has-error"
    document.forms["evensTrouble"]["stepNum"].focus();
    return false;
   }   
   if (num2 <= num1) {
    alert("The ending number must be greater than the starting number.");
    document.forms["evensTrouble"]["endNum"]
       .parentElement.className = "form-group has-error"
    document.forms["evensTrouble"]["endNum"].focus();
    return false;
   } 

   document.getElementById("results").style.display = "block";
   document.getElementById("resultMessage").textContent = "Here are the even numbers between " + num1 + " and " + num2 + " by " + num3 + "'s:";
    // start with starting num, keep it less than end and increment by step   
   for (var i = num1; i <= num2; i += num3) {    
        if(i % 2 == 0){
            ar.push(i);  
        } 
    }    

    //numResults.innerHTML = ar.join(" <br/> "); 

    document.getElementById("numResults").innerHTML = ar.join(" <br/> ");
    //document.getElementById("numResults").value= ar;
    document.getElementById("submitButton").innerText = "Recalculate";
    
    return false;
} 
