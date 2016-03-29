// Cinco! Word Game
// by Brett Stevenson
// Dictionary.java
// Date Modified: 03/28/2016
/* 	This class reads words from a text file and stores them in an ArrayList, which it then
	modifies according to the user input. This class also provides a random guess, as well as
	other supplementary information needed by the CincoGuesser class about the word list.
	Furthermore, since words with repeating characters aren't currently supported 
	"secret words" it features a function to remove these invalid words.   			       */
// Status: Working/Tested.
//

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

class Dictionary {

    private ArrayList <String> wordList = new ArrayList<>();
    private final int WORDLENGTH = 5;

    public Dictionary(String filename) {  // constructor: reads words from the supplied word list
        try {
            Scanner scan = new Scanner (new File(filename));
            while(scan.hasNext())
                wordList.add(scan.next());
            scan.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("The necessary file \'" + filename + "\' could not found!");
        }
        removeRepeatChars();
    }

    public int getWordLength() {return WORDLENGTH;} // returns word length.
    public int getWordCount() {return wordList.size();} // returns the remaining # of words.

    public String getRandomWord() {  // returns a randomly selected word from the word list.
        Random rndm = new Random();
        int randomIndex = rndm.nextInt(wordList.size());
        return wordList.get(randomIndex);
    }

    public void removeWords(String guess, int matching, int inPlace) {
        // removes words from the list that don't fit the given criteria
        for(int i = wordList.size()-1; i >= 0; i--) {
            String listWord = wordList.get(i);
            int matchCount = countMatching(guess, listWord);
            int placeCount = countInPlace(guess, listWord);
            if(matchCount != matching || placeCount != inPlace)
                wordList.remove(i);
        }
    }

    private int countMatching(String guess, String listWords) {
        // returns # of matching chars (helper of removeWords)
        int matching = 0;
        char [] guessChar = guess.toCharArray();
        char [] list = listWords.toCharArray();
        for (int i = 0; i < WORDLENGTH; i++)
            for (int j = WORDLENGTH-1; j >= 0; j--)
                if (guessChar[i] == list[j])
                    matching++;
        return matching;
    }

    private int countInPlace(String guess, String listWords) {
        // returns # of in-place chars (helper of removeWords)
        int inPlace = 0;
        char [] guessChar = guess.toCharArray();
        char [] list = listWords.toCharArray();
        for(int i = 0; i < WORDLENGTH; i++)
            if(guessChar[i] == list[i])
                inPlace++;
        return inPlace;
    }

    public void removeRepeatChars() { // removes words with repeated characters
        for(int i = wordList.size()-1; i >= 0; i--) {
            String word = wordList.get(i);
            if(countMatching(word, word) > 5) // matching > 5 = repeated chars
                wordList.remove(i);
        }
    }

}
