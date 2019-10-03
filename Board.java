/*
   Garrett Faucher
   CS 110
   This class manages and creates the board that will be used for the game of Boggle.
 */

import java.util.ArrayList;
import java.util.Random;

public class Board {

    private final String[][] dieSet = {     //Our set of die in a 2D array.
            {"R", "I", "F", "O", "B", "X"},
            {"I", "F", "E", "H", "E", "Y"},
            {"D", "E", "N", "O", "W", "S"},
            {"U", "T", "O", "K", "N", "D"},
            {"H", "M", "S", "R", "A", "O"},
            {"L", "U", "P", "E", "T", "S"},
            {"A", "C", "I", "T", "O", "A"},
            {"Y", "L", "G", "K", "U", "E"},
            {"Qu", "B", "M", "J", "O", "A"},
            {"E", "H", "I", "S", "P", "N"},
            {"V", "E", "T", "I", "G", "N"},
            {"B", "A", "L", "I", "Y", "T"},
            {"E", "Z", "A", "V", "N", "D"},
            {"R", "A", "L", "E", "S", "C"},
            {"U", "W", "I", "L", "R", "G"},
            {"P", "A", "C", "E", "M", "D"}};


    private ArrayList<ArrayList<Tile>> board = new ArrayList<>(); // Board array list to store the rows
    private ArrayList<Tile> row; // Row array list to store tile objects to make up rows
    private final int DIMENSIONS = 4; // Dimensions of our board

    /**
     * The default constructor creates the board when called. It rolls the die for each tile and adds it to an array list.
     */
    public Board(){
        int[] numberOfDie = new int[DIMENSIONS * DIMENSIONS];
        for(int i = 0; numberOfDie.length > i; i++) // Populating an array with the number of die there are
            numberOfDie[i]= i + 1;

        for (int i = 0; DIMENSIONS > i; i++) { // For loop for the first row
            row = new ArrayList<>(); // Creating a blank array list for the row
            for(int j = 0; DIMENSIONS > j; j++) { // For loop for each spot in the row
                int selectedDie;
                int die = 0;
                do { // Do while loop to make sure the die hasn't been used yet
                    die = rollSixteen();
                    selectedDie = numberOfDie[die-1];
                }
                while(selectedDie == 0);

                numberOfDie[die-1] = 0;

                int dieSide = rollSix(); // Randomly choosing the side the die landed on
                Tile tile = new Tile(dieSet[selectedDie-1][dieSide-1], i, j); // Creating a new tile object
                row.add(tile); // Adding the tile object to the row

            }
            board.add(row); // Adding the new row to the board
        }
    }

    /**
     * The rollSix method rolls a six sided die.
     * @return result The result of rolling a 6 sided die
     */
    public int rollSix() {
        Random rand = new Random();
        int result = rand.nextInt(6) + 1;
        return result;
    }

    /**
     * The rollSixteen method rolls a random number 1-16.
     * @return result The result of rolling 1-16
     */
    public int rollSixteen() {
        Random rand = new Random();
        return rand.nextInt(16) + 1;
    }

    public ArrayList<ArrayList<Tile>> getBoard(){
        return board;
    }

    /**
     * The toString method formats the board into a square to print.
     * @return str The string representing the board.
     */
    public String toString() {
        String str = "";
        for(int i = 0; DIMENSIONS > i ; i++){
            ArrayList<Tile> currentRow = board.get(i);
            for(int j = 0; DIMENSIONS > j; j++){
                if(currentRow.get(j).getChar() != "Qu"){
                    str += " " + currentRow.get(j).getChar() + "  ";
                }
                else if(currentRow.get(j).getChar() == "Qu"){
                    str += currentRow.get(j).getChar() + "  ";
                }
            }
            str += "\n\n";
        }
        return str;
    }

}
