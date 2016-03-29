// Cinco! Word Game
// by Brett Stevenson
// CincoGuesser.java
// Date Modified: 03/28/2016
/* 	This class contains the main function, handles the IO and user interface, while
	tracking the current state of the game.  Much of which involves utilizing functions
	of the Dictionary class.    										*/
// Status:Working/Tested.
//

import java.util.Scanner;

public class CincoGuesser {

    private Dictionary dict;
    private int wordLength,numGuesses;

    public CincoGuesser() {   // constructor
        String filename = "wordlist.txt";
        dict = new Dictionary(filename);
        wordLength = dict.getWordLength();

    }

    public static void main(String [] args) {
        CincoGuesser game = new CincoGuesser();
        game.play();
    }

    public void play() { consoleUI(); }

    private void consoleUI() {  // main game loop and console i/o
        System.out.println("Cinco! Word Game");
        System.out.println("by Brett Stevenson");
        System.out.println("Select your five letter word from the list...");
        Scanner input = new Scanner(System.in);
        int matching, inPlace = 0;

        while(!isEmpty() && inPlace != wordLength) {
            String guess = dict.getRandomWord();
            System.out.println("I'm guessing: " + guess);
            numGuesses++;

            System.out.print("Matching? ");
            matching = input.nextInt();
            while(!withinRange(matching)) {
                System.out.println("**Oops! Please input a valid numeric value.**");
                System.out.print("Matching? ");
                matching = input.nextInt();
            }
            System.out.print("In-Place? ");
            inPlace = input.nextInt();
            while(!withinRange(inPlace) || matching < inPlace) {
                System.out.println("**Oops! Please input a valid numeric value.**");
                System.out.print("In-Place? ");
                inPlace = input.nextInt();
            }
            if (inPlace != dict.getWordLength())
                dict.removeWords(guess, matching, inPlace);
            else
                System.out.println("I got it in " + numGuesses + " guesses.");
        }
        if(isEmpty()) {
            System.out.println("**There are no words in the list that fit those criteria.**");
            System.out.println("**        Please review your input and try again.        **");
            System.exit(0);
        }
    }


    private boolean withinRange(int n) { return n <= wordLength && n >= 0; }
        // checks that the user input is within the accepted range.

    private boolean isEmpty() { return dict.getWordCount() <= 0; }
        // checks there are words in the word list.
}
