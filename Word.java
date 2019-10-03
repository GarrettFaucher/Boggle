/*
   Garrett Faucher
   CS 110
   This class manages words found from tiles.
*/

import java.util.ArrayList;

public class Word {

    private String word = ""; // Blank string for the word.
    private int points; // Value of points assigned.

    /**
     * This constructor creates a word from an array list of tiles.
     * @param list An array list of Tile objects for forming the word.
     */
    public Word(ArrayList<Tile> list) {
        for(int i = 0; list.size() > i; i++){ // For loop to go through each object in list
            word += list.get(i).getChar();  // Getting the char stored in the tile object and concatenating to end of
                                            // the word string.
        }
    }

    /**
     * The getPoints method determines the length of the word and then returns a given amount of points depending
     * on the length.
     * @return points The amount of points assigned to the word.
     */
    public int getPoints() {
        if (word.length() == 3 || word.length() == 4) { // Determine word length
            points = 1; // Points assigned based on length.
            return points;
        }
        else if (word.length() == 5) {
            points = 2;
            return points;
        }
        else if (word.length() == 6) {
            points = 3;
            return points;
        }
        else if (word.length() == 7) {
            points = 5;
            return points;
        }
        else if (word.length() >= 8) {
            points = 11;
            return points;
        }
        else {
            points = 0;
            return points;
        }
    }

    /**
     * The toString method returns the word that was found.
     * @return word The word found by the constructor.
     */
    public String toString() {
        return word.toUpperCase();
    }

    /**
     * The equals method returns a boolean depending on whether the words are the same.
     * @return word The word found by the constructor.
     */
    public boolean equals(Word w) {
        if(w.toString() == this.toString())
            return true;
        else
            return false;
    }
}