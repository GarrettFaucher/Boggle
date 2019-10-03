/*
   Garrett Faucher
   CS 110
   This class is for the GUI of the Boggle game.
 */

// A lot of import statements for JavaFX stuff
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class GUI extends Application {

    private Game g = new Game(); // Starting the game
    private Button[] gameButtons = createButtons(g); // Array of button objects for the letters, using createButtons method

    private GridPane gameBoard = new GridPane(); // Creating the GridPane for the game board
    private VBox leftSide, rightSide; // VBoxes for left and right sides of the screen
    private HBox controls; // HBox for the controls below the game board
    private Label word, splitWordsLabel, scoreNumberLabel, selectedLetters; // Different labels for text display
    private String splitWords; // A string of the words found

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method starts the GUI
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        gameBoard.setHgap(20); // Creating gaps in the gameboard between buttons
        gameBoard.setVgap(20);
        gameBoard.setPadding(new Insets(10, 10, 10, 10)); // Padding for aesthetic reasons
        gameBoard = addButtons(gameButtons, gameBoard); // Calling the addButtons method to add buttons to the GridPane

        Button endGame = new Button("End Game"); // End game button
        endGame.setStyle("-fx-pref-width: 100px;"); // Spacing
        endGame.setOnAction(new SelectEnd()); // Adding functionality to the button

        Button submitWord = new Button("Submit Word"); // Submit word button
        submitWord.setStyle("-fx-pref-width: 100px;");
        submitWord.setOnAction(new SelectSubmit());

        selectedLetters = new Label(); // Labeling the score
        selectedLetters.setStyle("-fx-font-size: 16pt; -fx-pref-width: 210px;");
        selectedLetters.setAlignment(Pos.CENTER); // Aligning the selectedLetters label

        controls = new HBox(endGame, submitWord, selectedLetters); // The HBox for below the gameBoard
        controls.setSpacing(20);
        controls.setPadding(new Insets(10, 10, 10, 10));
        controls.setStyle("-fx-font-size: 10pt;");

        leftSide = new VBox(gameBoard, controls); // The VBox for the left side of the board


        word = new Label("Submitted Words");
        word.setAlignment(Pos.CENTER);
        word.setStyle("-fx-font-size: 19pt;");

        splitWords = splitWords(g.getWords());
        splitWordsLabel = new Label(splitWords);
        splitWordsLabel.setStyle("-fx-border-style: solid inside;");
        splitWordsLabel.setPrefWidth(200);
        splitWordsLabel.setPrefHeight(430);

        int score = g.getScore(); // Getting score from game
        scoreNumberLabel = new Label("Score: " + Integer.toString(score)); // The score itself
        scoreNumberLabel.setStyle("-fx-font-size: 20pt; -fx-pref-width: 100px;");
        scoreNumberLabel.setAlignment(Pos.CENTER_LEFT);

        rightSide = new VBox(word, splitWordsLabel, scoreNumberLabel); // VBox for right side of the board


        HBox rootHBox = new HBox(leftSide, rightSide); // The main HBox


        Scene scene = new Scene(rootHBox, 690, 530); // Adding our rootHBox to the scene
        primaryStage.setScene(scene); // Setting the scene to the stage
        primaryStage.setTitle("Boggle"); // Setting the title of the GUI
        primaryStage.show(); // Displaying the GUI
    }

    /**
     * The createButtons method creates the buttons needed for the Boggle game. It initializes the Button object,
     * adds text to it for the letter it corresponds to, and adds functionality when clicked.
     * @param g The game object
     * @return buttons A list of button objects
     */
    public Button[] createButtons(Game g) {
        ArrayList<ArrayList<Tile>> tiles = g.getBoard();
        Button[] buttons = new Button[16];
        int k = 0;
        for (int i = 0; tiles.size() > i; i++) {
            for (int j = 0; tiles.size() > j; j++){
                buttons[k] = new Button(tiles.get(i).get(j).toString()); // Adding the character to the button
                buttons[k].setStyle("-fx-font-size: 25pt; -fx-pref-height: 100px; -fx-pref-width: 100px;");
                buttons[k].setOnAction(new SelectLetter(i, j)); // Adding functionality when clicked
                k++;
            }
        }
        return buttons;
    }

    /**
     * The addButtons method adds Button objects to a GridPane.
     * @param buttons An array of Button objects
     * @param buttonPane A GridPane for the buttons to go into
     * @return buttonPane The GridPane of Button objects
     */
    public GridPane addButtons(Button[] buttons, GridPane buttonPane){
        int b = 0;
        for (int i = 0; 4 > i; i++){
            for (int j = 0; 4 > j; j++){
                buttonPane.add(buttons[b], j, i, 1, 1);
                b++;
            }
        }
        return buttonPane;
    }

    /**
     * The splitWords method splits the words found into their own line to display in the Label
     * @param list An ArrayList of String objects
     * @return str A formatted string
     */
    public String splitWords(ArrayList<String> list){
        String str = " ";
        for (int i = 0; list.size() > i; i++){
            str += list.get(i) + "\n ";
        }
        return str;
    }

    /**
     * The splitLetters method splits the letters of an ArrayList of Tile objects to show the user what has been
     * selected.
     * @param list An ArrayList of Tile objects
     * @return str A formatted string of letters
     */
    public String splitLetters(ArrayList<Tile> list){
        String str = "";
        for (int i = 0; list.size() > i; i++){
            str += list.get(i) + "";
        }
        return str;
    }

    /**
     * The SelectLetter class is for the gameBoard buttons so they have functionality.
     */
    class SelectLetter implements EventHandler<ActionEvent>{
        private int row, col;
        public SelectLetter(int row, int col){
            this.row = row;
            this.col = col;
        }

        /**
         * The handle method is for functionality of the gameBoard buttons
         * @param event
         */
        @Override
        public void handle(ActionEvent event) {
            if (g.isValidSelection(row, col)) {
                g.addToSelected(row, col);
                String myLetters = splitLetters(g.getSelectedTiles());
                selectedLetters.setText(myLetters);
            }
        }
    }

    /**
     * The SelectSubmit class is for the Submit Word button so the user can submit their selection.
     */
    class SelectSubmit implements EventHandler<ActionEvent>{
        /**
         * The handle method is for functionality of the Submit Word button
         */
        @Override
        public void handle(ActionEvent event) {
            try {
                g.testSelected();
                splitWords = splitWords(g.getWords());
                splitWordsLabel.setText(splitWords);
                scoreNumberLabel.setText("Score: " + Integer.toString(g.getScore()));
                selectedLetters.setText("");
                g.clearSelected();
            }

            catch (IOException e) {
                //splitWordsLabel.setText("We out here.");
                //scoreNumberLabel.setText("Score: " + Integer.toString(g.getScore()));
            }
        }
    }

    /**
     * The SelectEnd class is for the End Game button so it has functionality.
     */
    class SelectEnd implements EventHandler<ActionEvent>{

        /**
         * The handle method is for functionality of the End Game button
         */
        @Override
        public void handle(ActionEvent event) {
            System.exit(0);
        }
    }

}
