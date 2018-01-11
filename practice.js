function  order(words)  {
    var  wordsArray = words.split(" ");
    var  newArray = [];
    var  num =  0;

    for  (i =  0; i < wordsArray.length; i++) {
        for  (j =  0; j < wordsArray[i].length; j++) {
            var  wordChar = wordsArray[i].charAt(j);
            if  (!isNaN(wordChar)) {
                num =  parseInt(wordChar);
                newArray[(num -  1)] = wordsArray[i];
            }
        };
    }
    return  newArray.join(' ');
} 