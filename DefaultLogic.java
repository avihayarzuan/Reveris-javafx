package reversiApp;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/*
 * Kfir Ventura Avihay Arzuan
 */

public class DefaultLogic {
  /**
   * Members.
   */
  private GridPane grid = new GridPane();
  private Cell currentCell = new Cell(this.grid);
  private Color currentColor;
  private Map<String, Cell> cellMap;
  private int boardSize;

  /**
   * Constructor.
   * 
   * @param board - the game's board.
   * @param currentColor - EMPTY.
   */
  public DefaultLogic(Map<String, Cell> boardMap, int size) {
    this.cellMap = boardMap;
    this.boardSize = size;
  }

  /**
   * The method calculates all possible moves for player
   * 
   * @param currentColor - the current player's color.
   * @return all possible moves of current player.
   */
  public Map<String, Cell> getPossibleMoves(Color currentColor) {
    // delete possibleMoves;
    Map<String, Cell> possibleMoves = new HashMap<String, Cell>();
    this.currentColor = currentColor;

    // Iterate over all cells
    for (Map.Entry<String, Cell> entry : this.cellMap.entrySet()) {

      // Check for possible moves ONLY if cell is empty
      if (entry.getValue().isEmpty()) {
        this.currentCell = entry.getValue();

        // if ANY direction is a possible move, ADD current cell into
        // the map
        if (isPossibleMove(1, 1) || isPossibleMove(-1, -1) || isPossibleMove(1, -1)
            || isPossibleMove(-1, 1) || isPossibleMove(1, 0) || isPossibleMove(0, 1)
            || isPossibleMove(-1, 0) || isPossibleMove(0, -1)) {
          int row;
          int col;
          row = entry.getValue().getRow();
          col = entry.getValue().getCol();
          possibleMoves.put(Cstring.intToPoint(row, col), entry.getValue());
        }
      }
    }
    // return all possible moves
    return possibleMoves;
  }

  /**
   * The method flips all possible opponent's tiles. The name of the method is a reference to the
   * Star Wars Saga.
   * 
   * @param row - row's index to start from.
   * @param col - col's index to start from.
   */
  public void executeOrder66(int row, int col) {
    this.currentCell = this.cellMap.get(Cstring.intToPoint(row, col));
    this.cellMap.get(Cstring.intToPoint(row, col)).setColor(this.currentColor);

    for (int i = Direction.down.getValue(); i <= Direction.up.getValue(); i++) {
      for (int j = Direction.down.getValue(); j <= Direction.up.getValue(); j++) {
        if (isPossibleMove(i, j)) {
          flip(i, j);
        }
      }
    }
  }


  /**
   * Sub method that checks if the cell is out of boundaries.
   * 
   * @param row - row's index.
   * @param col - column's index.
   * @return true if the cell is out of boundaries, otherwise return false.
   */
  private boolean cellOutOfBounds(int row, int col) {
    return ((row < 1) || (col < 1) || (row > this.boardSize) || (col > this.boardSize));
  }

  /**
   * Sub method that changes the color of all cells in the current direction.
   * 
   * @param rowDirection - row's direction.
   * @param colDirection - column's direction.
   */
  private void flip(int rowDirection, int colDirection) {
    int row = this.currentCell.getRow() + rowDirection;
    int col = this.currentCell.getCol() + colDirection;

    do {
      if (this.cellMap.get(Cstring.intToPoint(row, col))
          .getColor() == DefineConstants.getSecondColor()) {
        this.cellMap.get(Cstring.intToPoint(row, col)).setColor(DefineConstants.getFirstColor());
      } else {
        this.cellMap.get(Cstring.intToPoint(row, col)).setColor(DefineConstants.getSecondColor());
      }
      row += rowDirection;
      col += colDirection;
    } while (!(this.cellMap.get(Cstring.intToPoint(row, col)).getColor() == (this.currentColor)));
  }

  /**
   * Sub method that check's if the cell should be added as a possible move.
   * 
   * @param rowDirection - the direction of the row to be checked.
   * @param colDirection - the direction of the column to be checked.
   * @return true if cell is a possible move of the current player, else false.
   */
  private boolean isPossibleMove(int rowDirection, int colDirection) {
    int row = currentCell.getRow();
    int col = currentCell.getCol();
    boolean isFirstCell = true;

    do {
      row += rowDirection;
      col += colDirection;

      if (cellOutOfBounds(row, col)) {
        return false;
      }

      if (cellMap.get(Cstring.intToPoint(row, col)).getColor() == (this.currentColor)) {
        if (isFirstCell) {
          return false;
        }
        return true;
      }

      if (cellMap.get(Cstring.intToPoint(row, col)).isEmpty()) {
        return false;
      }

      isFirstCell = false;
    } while (true);
  }

}
