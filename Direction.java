package reversiApp;

/*
 * Kfir Ventura
 * Avihay Arzuan
 */

///#include "CellMap.h"

public enum Direction {
	stay(0), up(1), down(-1);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, Direction> mappings;

	private static java.util.HashMap<Integer, Direction> getMappings() {
		if (mappings == null) {
			synchronized (Direction.class) {
				if (mappings == null) {
					mappings = new java.util.HashMap<Integer, Direction>();
				}
			}
		}
		return mappings;
	}

	private Direction(int value) {
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue() {
		return intValue;
	}

	public static Direction forValue(int value) {
		return getMappings().get(value);
	}
}