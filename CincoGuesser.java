// Cinco! Word Game
// by Brett Stevenson
// CincoGuesser.java
/* 	This class contains the main funtion, handles the IO and user interface, while 
	tracking the current state of the game.  Much of which involves utilizing functions 
	of the Dictionary class.    */
// Status: Working/Tested.
//

import java.util.Scanner;

public class CincoGuesser {

    String filename = new String("wordlist.txt");
    private Dictionary dict = new Dictionary(filename);
    private int wordLength = dict.getWordLength();
    private int matching, inPlace;
    private int numGuesses;
   
    public CincoGuesser(){ } // constructor

    public static void main(String [] args) {
	CincoGuesser game = new CincoGuesser();
	System.out.println("Cinco with Computer Guessing"); //needed to be in consoleUI()
	System.out.println("by Brett Stevenson");
	System.out.println("Select your five letter word from the list...");
	game.play();
    }

    public void play() {
	// calls the consoleUI function until the program has won or is out of words.
	while(hasWords() && !hasWon())
	    consoleUI();	
    }
    
    private void consoleUI() {  // main game loop and console i/o
	String guess;
	Scanner input = new Scanner(System.in);
	dict.repeatedChars();
	System.out.println("Word Count: " + dict.getWordCount());//test
	guess = dict.getRandomWord();
	System.out.println("I'm guessing: " + guess);
	numGuesses++;
	
	System.out.print("Matching? ");
	matching = input.nextInt();
	while(!rangeCheck(matching)) {
	    System.out.println("**Oops! Please input a valid numeric value.**");
	    System.out.print("Matching? ");
	    matching = input.nextInt();
	}
	
	System.out.print("In-Place? ");
	inPlace = input.nextInt();
	while(!rangeCheck(inPlace) || matching < inPlace) {
	    System.out.println("**Oops! Please input a valid numeric value.**");
	    System.out.print("In-Place? ");
	    inPlace = input.nextInt();
	}
	
	if (inPlace == dict.getWordLength()) {
	    	    System.out.println("I got it in " + numGuesses + " guesses.");
	    //dict.recordResults(numGuesses); //test
	    //double average = dict.performanceAvg(); //test
	    //System.out.println("Average: " + average); //test
	} else {
	    dict.removeWords(guess, matching, inPlace);
	}    
	
	if(!hasWords()) {
	    System.out.println("**There are no words in the list that fit those criteria.**");
	    System.out.println("**        Please review your input and try again.        **");
	    System.exit(0);
	}
    }


    private boolean rangeCheck(int n) {
	// checks that the user input is within the accepted range.
	if (n <= wordLength && n >= 0)
	    return true;
	return false;
    }

    private boolean hasWon() {
	// checks if the program has guessed the correct word.
	if(inPlace == wordLength)
	    return true;
	else
	    return false;
    }

    private boolean hasWords() {
	// checks that there are words left in the provided word list.
	if (dict.getWordCount() > 0)
	    return true;
	return false;
    }

}
