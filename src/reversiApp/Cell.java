/*
 * Kfir Ventura Avihay Arzuan
 */
package reversiApp;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * the class represents properties of every cell in the board
 */
public class Cell {
  private Point point;
  private Color color;
  private GridPane grid;

  /**
   * Constructor.
   * 
   * @param grid the 'style' of gui of the cell
   */
  public Cell(GridPane grid) {
    this.point = new Point(0, 0);
    this.color = DefineConstants.getEmptyColor();
    this.grid = grid;
  }

  /**
   * Constructor.
   * 
   * @param grid the 'style' of gui of the cell
   * @param row - index of the row.
   * @param col - index of the column.
   */
  public Cell(GridPane grid, int row, int col) {
    this.point = new Point(row, col);
    this.color = DefineConstants.getEmptyColor();
    this.grid = grid;
  }

  /**
   * Constructor.
   * 
   * @param grid the 'style' of gui of the cell
   * @param row - index of the row.
   * @param col - index of the column.
   * @param color - cell's color. by default, will be EMPTY.
   */
  public Cell(GridPane grid, int row, int col, Color color) {
    this.point = new Point(row, col);
    this.color = color;
    this.grid = grid;
  }

  /**
   * Constructor.
   * 
   * @param grid the 'style' of gui of the cell
   * @param point - coordinate of the cell (i.e cell[i,j])
   */
  public Cell(GridPane grid, Point point) {
    this.point = new Point(point);
    this.color = DefineConstants.getEmptyColor();
    this.grid = grid;
  }

  /**
   * Constructor.
   * 
   * @param grid the 'style' of gui of the cell
   * @param point - coordinate of the cell (i.e cell[i,j])
   * @param color - cell's color. by default, will be EMPTY.
   */
  public Cell(GridPane grid, Point point, Color color) {
    this.point = new Point(point);
    this.color = color;
    this.grid = grid;
  }

  /**
   * draw the cell as rectangle and a circle on it
   * 
   * @param cellWidth
   * @param cellHeight
   */
  public void draw(int cellWidth, int cellHeight) {
    Rectangle rec = new Rectangle(cellWidth, cellHeight);
    rec.setStroke(Color.BLACK);
    rec.setFill(DefineConstants.getEmptyColor());

    StackPane stack = new StackPane();
    int min = Math.min(cellWidth, cellHeight);
    Circle disk = new Circle((min / 3), color);

    if (!(this.color == DefineConstants.getEmptyColor())) {
      DropShadow ds = new DropShadow();
      ds.setOffsetX(-min / 6);
      ds.setOffsetY(min / 6);
      disk.setEffect(ds);
    }

    stack.getChildren().addAll(rec, disk);

    grid.getChildren().remove(rec);
    grid.add(stack, getCol(), getRow());
  }

  /**
   * @return cell's row index.
   */
  public final int getRow() {
    return this.point.getRow();
  }

  /**
   * @return cell's column index.
   */
  public final int getCol() {
    return this.point.getCol();
  }

  /**
   * @param color - the color to be compared with,
   * @return true if the cell's color is the same as color, else return false.
   */
  public final boolean isFilledWith(Color color) {
    return (this.color == color);
  }

  /**
   * @return true if cell's color is EMPTY. otherwise, return false.
   */
  public final boolean isEmpty() {
    return (this.color == DefineConstants.getEmptyColor());
  }

  /**
   * Getter.
   * 
   * @return cell's color.
   */
  public final Color getPlayer() {
    return this.color;
  }

  /**
   * Setter.
   * 
   * @param color - cell's color may be BLACK, WHITE or EMPTY.
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Getter.
   * 
   * @return cell's color.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Getter.
   * 
   * @return cell's coordinate (i.e "(x,y)")
   */
  public final String toString() {
    return this.point.toString();
  }

  /**
   * copy construcor of the cell class
   * 
   * @param c Cell object
   */
  public void copyFrom(Cell c) {
    this.point = new Point(c.getRow(), c.getCol());
    this.color = c.getPlayer();
  }
}
