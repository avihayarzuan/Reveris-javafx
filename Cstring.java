/*
 * Kfir Ventura
 * Avihay Arzuan
 */

package reversiApp;


public class Cstring {
	/**
	 * The method converts two int values into a string point format "(x,y)".
	 * 
	 * @param x
	 *            - x coordinate
	 * @param y
	 *            - y coordinate
	 * @return "(x,y)"
	 */
	public static String intToPoint(int x, int y) {

		String str = "";

		String rowString = Integer.toString(x);
		String colString = Integer.toString(y);

		return str + '(' + rowString + ',' + colString + ')';
	}
}