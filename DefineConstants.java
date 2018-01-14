/*
 * Kfir Ventura Avihay Arzuan
 */

package reversiApp;

import javafx.scene.paint.Color;


public class DefineConstants {
  private static int BOARD_SIZE = 8;
  private static Color EMPTY = Color.BROWN;
  private static Color FIRST_COLOR = Color.BLACK;
  private static Color SECOND_COLOR = Color.WHITE;

  public DefineConstants(int size, Color first, Color second) {
    BOARD_SIZE = size;
    FIRST_COLOR = first;
    SECOND_COLOR = second;
    if (FIRST_COLOR == Color.BROWN) {
      if (SECOND_COLOR == Color.LIGHTGRAY) {
        EMPTY = Color.WHITE;
      } else {
        EMPTY = Color.LIGHTGRAY;
      }
    } else if (SECOND_COLOR == Color.BROWN) {
      if (FIRST_COLOR == Color.LIGHTGRAY) {
        EMPTY = Color.WHITE;
      } else {
        EMPTY = Color.LIGHTGRAY;
      }
    }
  }

  /**
   * static method to run parameters from it
   * 
   * @return board size
   */
  public static int getBoardSize() {
    return BOARD_SIZE;
  }

  /**
   * static method to run parameters from it
   * 
   * @return the empty cell color
   */
  public static Color getEmptyColor() {
    return EMPTY;
  }

  /**
   * static method to run parameters from it
   * 
   * @return first player color
   */
  public static Color getFirstColor() {
    return FIRST_COLOR;
  }

  /**
   * static method to run parameters from it
   * 
   * @return second player color
   */
  public static Color getSecondColor() {
    return SECOND_COLOR;
  }

}
