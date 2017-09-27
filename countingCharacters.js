function countingCharacters(stringToCount){
    // The length property has been mentioned in 
    // the Logging Letter Exercise in Lesson 4
    console.log (stringToCount + " has " + 
              stringToCount.length + " characters.");
}

function countingCharacters2(stringToCount, characterToFind){
    var characterCount = 0;
    for (var characterPosition = 0; 
           characterPosition < stringToCount.length; 
           characterPosition++){
        if (stringToCount[characterPosition] == characterToFind){
            characterCount++;
        }
    }
    console.log("String to search in: " + stringToCount);
    console.log("Character to find: " + characterToFind);
    console.log("Number of times the character appears: " + 
                  characterCount);
}

function countingCharacters3(str, search){    //entering xxaxxxbxx and xx
    var count = 0;
    var numChars = search.length;           //search.length = 2 as xx
    
    var lastIndex = str.length - numChars;  //str.length - numChar equals 9-2 so 7
    for (var index = 0; index <= lastIndex; index++){ //As long as index less than 7, add 1
       var current = str.substring(index, index + numChars); //to start substring (0, 2) - substring does letters starting at first placement and stopping before second. so starting at 0 and stopping at 1
        if (current == search){            //current is xx
            count++;                //add to count
        }
    }
  
  console.log("String to search in: " + str);
  console.log("Characters to find: " + search);
  console.log("Number of times the character appears: " + count);    
}  

function countingCharacters3(str, search){    
    var count = 0;
    var numChars = search.length;
    
    var lastIndex = str.length - numChars;
    for (var index = 0; index <= lastIndex; index++){
       var current = str.substring(index, index + numChars);
        if (current == search){            
            count++;
        }
    }
    return count;
}