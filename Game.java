/*
   Garrett Faucher
   CS 110
   This class manages and creates the game of Boggle.
 */

import java.io.IOException;
import java.util.ArrayList;

public class Game{

    private final String DICT = "dict.txt";
    private ArrayList<int[]> selected = new ArrayList<>();
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<String> words = new ArrayList<>();
    private final int MAX = 4, INIT = -2;
    private int score = 0;
    private int prevRow = INIT, prevCol = INIT;
    private Board board;

    /**
     * The Game constructor creates a new board when a new game is started.
     */
    public Game(){
        board = new Board();
    }

    /**
     * The isValidSelection method determines if the coordinates that were selected are valid.
     * @param row The row number
     * @param col The column number
     * @return true/false True is the selection is valid
     */
    public boolean isValidSelection(int row, int col){

        // Check if selection is valid at all
        if(row > MAX || col > MAX || row < 0 || col < 0)
            return false;

        // Check if previously selected
        if(isSelected(row, col))
            return false;

        // True if first selection
        if(prevCol == INIT && prevRow == INIT){
            prevCol = col;
            prevRow = row;
            return true;
        }

        // True if adjacent
        if((row == prevRow-1 || row == prevRow+1) && col == prevCol ||
           (col == prevCol-1 || col == prevCol+1) && row == prevRow)
        {
            prevCol = col;
            prevRow = row;
            return true;
        }

        // True if diagonal
        if((row == prevRow-1 && col == prevCol-1) ||
           (row == prevRow-1 && col == prevCol+1) ||
           (row == prevRow+1 && col == prevCol-1) ||
           (row == prevRow+1 && col == prevCol+1))
        {
                prevCol = col;
                prevRow = row;
                return true;
        }

    return false; // False otherwise
    }

    /**
     * The addToSelected method adds a set of coordinates to an array
     * @param row The row number
     * @param col The column number
     */
    public void addToSelected(int row, int col){
        int[] coordinates = {row, col};
        selected.add(coordinates);
    }

    /**
     * The removeFromSelected method removes a set of coordinates from the selected array
     * @param row The row number
     * @param col The column number
     */
    public void removeFromSelected(int row, int col){
        for(int i = 0; i < selected.size(); i++){
            if(row == selected.get(i)[0] && col == selected.get(i)[1]){
                selected.remove(i);
            }
        }
    }

    /**
     * The getSelectedTiles method turns a set of selected coordinates to be an array of tiles based on the layout
     * of the previously generated board.
     * @return tiles An array list of tile objects
     */
    public ArrayList<Tile> getSelectedTiles(){

        tiles.clear(); // Clearing the tiles before repopulating the array

        if(selected.size() == 0) { // Checking to make sure there is something to create an array from
            tiles.clear();
            return tiles;
        }

        ArrayList<ArrayList<Tile>> myBoard = board.getBoard();
        for(int i = 0; selected.size() > i; i++) { // For each set of coordinates in the array
            for(int j = 0; myBoard.size() > j; j++){ // For each row
                for(int k = 0; myBoard.get(j).size() > k; k++){ // For each column
                    if(myBoard.get(j).get(k).getX() == selected.get(i)[0] && // If both row/column and x/y are the same
                       myBoard.get(j).get(k).getY() == selected.get(i)[1]){  // the tile is added to the array.
                        tiles.add(myBoard.get(j).get(k));
                    }
                }
            }
        }
        return tiles;
    }

    /**
     * The clearSelected method resets the selected options
     */
    public void clearSelected(){
        selected.clear();
        prevCol = INIT;
        prevRow = INIT;
    }

    /**
     * The testSelected method checks to see if the selected coordinates form a valid word from the dictionary.
     * @throws IOException
     */
    public void testSelected() throws IOException {
        Dictionary dict = new Dictionary(DICT); // Creating dictionary object
        Word word = new Word(tiles); // Creating word object from tiles

        if(dict.isValidWord(tiles)){ // If the word is in the dictionary it runs
            String myWord = word.toString();
            score += word.getPoints();
            words.add(myWord);
            clearSelected();
        }
    }

    /**
     * The getScore method returns the score of the game
     * @return score An int of the score
     */
    public int getScore(){
        return score;
    }

    /**
     * The isSelected method checks to see if a set of coordinates has already been selected.
     * @param row The row number
     * @param col The column number
     * @return true/false True means coordinates have een selected
     */
    public boolean isSelected(int row, int col){
        for(int i = 0; selected.size() > i; i++){
            if((row == selected.get(i)[0]) && (col == selected.get(i)[1])){ // Checking to see if its the same
                return true;
            }
        }
        return false;
    }

    /**
     * The toString method outputs the board and the current standings of the game.
     * @return str A string with all of the output.
     */
    public String toString(){
        getSelectedTiles();
        String str = "\n";
        str += board.toString();
        str += "\nselected: " + tiles;
        str += "\nwords: " + words;
        str += "\nscore: " + score;

        return str;
    }

    /**
     * The getBoard method returns an 2D ArrayList of Tile objects.
     * @return board object
     */
    public ArrayList<ArrayList<Tile>> getBoard(){
        return board.getBoard();
    }

    /**
     * The getWords function returns an ArrayList of words.
     * @return words An ArrayList of the words.
     */
    public ArrayList<String> getWords() {
        return words;
    }
}
