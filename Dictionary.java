// Cinco! Word Game
// by Brett Stevenson
// Dictionary.java
/* 	This class reads words from a text file and stores them in an ArrayList, which it then 
	modifies according to the user input. This class also provides a random guess as well as 
	other supplementary information needed by the CincoGuesser class about the word list. 
	Furthermore, since words with repeating characters aren't currently supported "secret words" 
	it features a function to remove these invalid words.   */
// Status: Working/Tested.
//

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

class Dictionary {

    private ArrayList <String> wordList = new ArrayList<String>();
    final int WORDLENGTH = 5;
    Random random = new Random();

    public Dictionary(String filename) {  // constructor: reads words from the supplied .txt file
	try {
	    Scanner scan = new Scanner (new File(filename));
	    while(scan.hasNext())
     		wordList.add(scan.next());
	    scan.close();
	} 
	catch(FileNotFoundException e) {
	    System.err.println(filename + ": File not found!");
	}
    }
    
    public int getWordLength() {return WORDLENGTH;} // returns word length.
    public int getWordCount() {return wordList.size();} // returns the remaining # of words in the list.
    
    public String getRandomWord() {
	// returns a randomly selected guess from the remaining words.
	int randomInt = random.nextInt(wordList.size());
	String guess = wordList.get(randomInt);
	return guess;
    }
    
    public void removeWords(String guess, int matching, int inPlace) {
	// removes words from the supplied list that don't fit the given criteria
        String secondWord;
	int commonMatch, commonPlace;
	for(int i = wordList.size()-1; i >= 0; i--) {
	    secondWord = wordList.get(i);
	    commonMatch = countMatching(guess, secondWord);
	    commonPlace = countInPlace(guess, secondWord);
	    if (matching != commonMatch || inPlace != commonPlace) {
		wordList.remove(i);
	    }
	}
    }
    
    private int countMatching(String firstWord, String secondWord) {
	// returns # of matching chars (helper of removeWords)
	int commonMatch = 0;
	char [] first = firstWord.toCharArray();
	char [] second = secondWord.toCharArray();
	for (int i = 0; i < WORDLENGTH; i++) 
	    for (int count = WORDLENGTH-1; count >= 0; count--)
		if (first[i] == second[count])
		    commonMatch++;
	return commonMatch;
    }
    
    private int countInPlace(String firstWord, String secondWord) {
	// returns # of in-place chars (helper of removeWords)
	int commonPlace = 0;
	char [] first = firstWord.toCharArray();
	char [] second = secondWord.toCharArray();
	for(int i = 0; i < WORDLENGTH; i++)
	    if(first[i] == second[i])
		commonPlace++;
	return commonPlace;
    }

    public void repeatedChars() {
	// removes all words with repeating characters from the supplied list
	for(int i = wordList.size()-1; i >= 0; i--) {
	    String word = wordList.get(i);
	    if(countMatching(word, word) > 5)
		wordList.remove(i); 	
	}
    }

 }   
    