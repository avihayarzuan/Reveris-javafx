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
    if (FIRST_COLOR == Color.BROWN || SECOND_COLOR == Color.BROWN) {
      
    }
//    EMPTY = empty;
  }
  
  public static int getBoardSize() {
    return BOARD_SIZE;
  }
  
  public static Color getEmptyColor() {
    return EMPTY;
  }
  
  public static Color getFirstColor() {
    return FIRST_COLOR;
  }
  
  public static Color getSecondColor() {
    return SECOND_COLOR;
  }
  
}
