/*
   Garrett Faucher
   CS 110
   This class is used for creating tiles of a Boggle game.
*/

public class Tile {

    private String str;
    private int x, y;
    private boolean selected = false;

    /**
     * This constructor creates a tile object with a string input.
     * @param str What is displayed on the tile
     * @param x The vertical location of the die
     * @param y The horizontal location of the die
     */
    public Tile(String str, int x, int y) {

        this.str = str;
        this.x = x;
        this.y = y;

    }

    /**
     * This constructor creates a tile object with a character input.
     * @param ch What is displayed on the tile
     * @param x The vertical location of the die
     * @param y The horizontal location of the die
     */
    public Tile(char ch, int x, int y) {

        this.str = Character.toString(ch); // If char is used it is converted and then set
        this.x = x;
        this.y = y;

    }

    /**
     * The toSelected method changes the tiles value of selected to true.
     */
    public void toSelected(){ selected = true; }

    /**
     * The toString method turns the data of the tile into a string.
     * @return string The formatted string.
     */
    public String toString() { return str; }

    /**
     * The getChar method gives the user the face of the tile.
     * @return str The value of the face of the tile
     */
    public String getChar(){ return str; }

    /**
     * The getX method gives the user the x value of the tile.
     * @return x The x value of the tile
     */
    public int getX(){ return x; }

    /**
     * The getChar method gives the user the y value of the tile.
     * @return y The y value of the tile
     */
    public int getY(){ return y; }

}
