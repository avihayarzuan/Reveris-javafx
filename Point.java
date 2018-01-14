/*
 * Kfir Ventura
 * Avihay Arzuan
 */

package reversiApp;


public class Point {
	/**
	 * Members.
	 */
	private int row;
	private int col;
	
	/**
	 * Constructor.
	 * 
	 * @param row
	 *            - index of row.
	 * @param col
	 *            - index of column.
	 */
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Point(Point point) {
		this.row = point.getRow();
		this.col = point.getCol();
	}

	/**
	 * @return row's index.
	 */
	public final int getRow() {
		return this.row;
	}

	/**
	 * @return column's index.
	 */
	public final int getCol() {
		return this.col;
	}

	/**
	 * @return coordinates in string format: "(row,col)".
	 */
	public final String toString() {
		return Cstring.intToPoint(this.row, this.col);
	}


}