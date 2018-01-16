/*
 * Kfir Ventura Avihay Arzuan
 */

package gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import reversiApp.Cell;
import reversiApp.Cstring;
import reversiApp.DefaultLogic;
import reversiApp.DefineConstants;

/**
 * Board class, extends Gridpane interface use's to control the game flow
 *
 */
public class Board extends GridPane {

  private int size;
  private Map<String, Cell> boardMap = new HashMap<String, Cell>();
  private DefaultLogic logic;

  public Color currentColor = DefineConstants.getFirstColor();
  public LongProperty p = new SimpleLongProperty(1);
  public LongProperty points1 = new SimpleLongProperty(2);
  public LongProperty points2 = new SimpleLongProperty(2);
  public Color c = currentColor;
  

  /**
   * Constructor for the board
   * 
   * @param size the size of the board to initial it.
   */
  public Board(int size) {
    this.size = size;
    initBoard();

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Board.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();

      this.setOnMouseClicked(e -> {
        if (e.getTarget().getClass().equals(Circle.class)) {
          Circle clicked = (Circle) e.getTarget();
          StackPane stack = (StackPane) clicked.getParent();
          int row = GridPane.getRowIndex(stack);
          int col = GridPane.getColumnIndex(stack);
          Map<String, Cell> posMap = logic.getPossibleMoves(currentColor);
          // if the user clicked on one of the 'invisible' circles it won't go inside
          if (posMap.containsKey(Cstring.intToPoint(row, col))) {
            logic.executeOrder66(row, col);
            // draw again the board
            this.draw();
            this.changePlayer();
            this.updateScore();
            posMap = logic.getPossibleMoves(currentColor);
            if (posMap.isEmpty()) {
              Color otherColor = getOtherColor();
              Map<String, Cell> posMapOther = logic.getPossibleMoves(otherColor);
              if (posMapOther.isEmpty()) {
                this.noOneCanMove();
                this.setOnMouseClicked(null);
              } else {
                this.noMove();
              }
            }
          }
        }
      });
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  /**
   * return the map of the board
   * 
   * @return the map of the board
   */
  public final Map<String, Cell> getCellsList() {
    return this.boardMap;
  }

  /**
   * return the size of one dimension of the board
   * 
   * @return int size
   */
  public final int getSize() {
    return this.size;
  }

  /**
   * change the player turns
   */
  private void changePlayer() {
    if (currentColor == DefineConstants.getFirstColor()) {
      currentColor = DefineConstants.getSecondColor();
      // update the label:
      p.set(2);
    } else {
      currentColor = DefineConstants.getFirstColor();
      // update the label:
      p.set(1);
    }
  }

  /**
   * the function returns the color of the not current player
   * 
   * @return Color
   */
  private Color getOtherColor() {
    if (this.currentColor == DefineConstants.getFirstColor()) {
      return DefineConstants.getSecondColor();
    } else {
      return DefineConstants.getFirstColor();
    }
  }

  /**
   * update the score of the players
   */
  public void updateScore() {
    this.setFirstPlayerPoints();
    this.setSecondPlayerPoints();
  }

  /**
   * update the first player points
   */
  public void setFirstPlayerPoints() {
    points1.set(getScore(DefineConstants.getFirstColor()));
  }

  /**
   * update the second player points
   */
  public void setSecondPlayerPoints() {
    points2.set(getScore(DefineConstants.getSecondColor()));
  }

  /**
   * given Color, the method counts the number of disks with the same color
   * 
   * @param color the color to count
   * @return the number of disks
   */
  private int getScore(Color color) {
    int score = 0;
    for (Map.Entry<String, Cell> entry : this.boardMap.entrySet()) {
      if (entry.getValue().isFilledWith(color)) {
        score++;
      }
    }
    return score;
  }

  /**
   * check who wins, or tells if there is a tie
   * 
   * @return String to tell the final announcement
   */
  private String getWinner() {
    int x =
        (getScore(DefineConstants.getFirstColor()) - getScore(DefineConstants.getSecondColor()));
    if (x > 0) {
      return "Player 1 is the winner";
    } else if (x < 0) {
      return "Player 2 is the winner";
    } else {
      return "it is a tie!";
    }
  }

  /**
   * pops alert to screen tells the players the game is over.
   */
  private void noOneCanMove() {
    String over = "Game-Over\n" + "no-more moves possibles\n" + "Player 1 points: "
        + getScore(DefineConstants.getFirstColor()) + "\n" + "Player 2 points: "
        + getScore(DefineConstants.getSecondColor()) + "\n" + this.getWinner();
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("No-One-Can-Move");
    alert.setHeaderText(null);
    alert.setContentText(over);
    alert.showAndWait();
  }

  /**
   * pops alert to screen, tell the current player he can't move
   */
  private void noMove() {
    int turn = this.whichTurn();
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("No-Possible-Moves");
    alert.setHeaderText(null);
    alert.setContentText("Player " + turn + "\nYou have no posibble moves!");
    alert.showAndWait();
    this.changePlayer();
  }

  /**
   * method to tell which turn is it now
   * @return 1 for first player turn, 2 for the second player
   */
  private int whichTurn() {
    if (currentColor == DefineConstants.getFirstColor()) {
      return 1;
    } else {
      return 2;
    }
  }

  /**
   * The method initializes the board.
   */
  private void initBoard() {
    this.logic = new DefaultLogic(boardMap, this.size);
    for (int row = 1; row <= this.size; row++) {
      for (int col = 1; col <= this.size; col++) {
        Cell c = new Cell(this, row, col);
        boardMap.put(Cstring.intToPoint(row, col), c);
      }
    }
    //initialize the first four disks
    boardMap.get(Cstring.intToPoint((this.size / 2), (this.size / 2)))
        .setColor(DefineConstants.getSecondColor());
    boardMap.get(Cstring.intToPoint(((this.size / 2) + 1), ((this.size / 2) + 1)))
        .setColor(DefineConstants.getSecondColor());
    boardMap.get(Cstring.intToPoint((this.size / 2), ((this.size / 2) + 1)))
        .setColor(DefineConstants.getFirstColor());
    boardMap.get(Cstring.intToPoint(((this.size / 2) + 1), (this.size / 2)))
        .setColor(DefineConstants.getFirstColor());
  }

  /**
   * simple method to draw the board
   */
  public void draw() {
    this.getChildren().clear();
    int height = (int) this.getPrefHeight();
    int width = (int) this.getPrefWidth();
    int cellHeight = height / (this.size + 2);
    int cellWidth = width / (this.size + 2);
    for (int i = 0; i < (this.size + 2); i++) {
      for (int j = 0; j < (this.size + 2); j++) {
        if (((i == 0) && (j == 0)) || ((i == 0) && (j == (this.size + 1)))
            || ((i == (this.size + 1)) && (j == 0))
            || ((i == (this.size + 1)) && (j == (this.size + 1)))) {
          Rectangle corner = new Rectangle((cellWidth / 2), (cellHeight / 2), Color.GRAY);
          corner.setStroke(Color.BLACK);
          this.add(corner, j, i);
        } else if ((i == 0) || (i == (size + 1))) {
          Rectangle horizonMargin = new Rectangle(cellWidth, (cellHeight / 2), Color.GRAY);
          horizonMargin.setStroke(Color.BLACK);
          this.add(horizonMargin, j, i);
        } else if ((j == 0) || (j == (size + 1))) {
          Rectangle verticalMargin = new Rectangle((cellWidth / 2), cellHeight, Color.GRAY);
          verticalMargin.setStroke(Color.BLACK);
          this.add(verticalMargin, j, i);
        } else {
          boardMap.get(Cstring.intToPoint(i, j)).draw(cellWidth, cellHeight);
        }
      }
    }
  }
}
