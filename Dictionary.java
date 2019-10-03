/*
   Garrett Faucher
   CS 110
   This class manages and creates the dictionary that will be used for the game of Boggle.
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Dictionary {

    private ArrayList<String> dictionaryList = new ArrayList<>(); // Array list of strings for dictionary

    /**
     * This constructor creates a dictionary from a filename.
     * @param filename The name of the file the dictionary will be based on.
     * @throws IOException
     */
    public Dictionary(String filename) throws IOException {

        File dictFile = new File(filename);
        Scanner dictScan = new Scanner(dictFile);

        // Adding strings to dictionary array list as long as the file still has lines
        while (dictScan.hasNextLine())
            dictionaryList.add(dictScan.nextLine());

    }

    /**
     * The isValidWord method determines if an array list of tiles forms a word from the dictionary.
     * @param tiles An array list of tile objects storing a string used to form a word.
     * @return true/false True means the word is valid, false the opposite.
     */
    public boolean isValidWord(ArrayList<Tile> tiles){

        Word w = new Word(tiles); // Getting a word object from the array list of tiles.

        for (int i = 0; dictionaryList.size() > i; i++) { // Loops through dictionary array list
            String compareWord = dictionaryList.get(i).toUpperCase();
            if (compareWord.equals(w.toString().toUpperCase())) // Compares each dictionary string to thr word.
                return true; // Returns true if the word is in the dictionary
        }
        return false;
    }

}
