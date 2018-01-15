package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import javafx.scene.paint.Color;

enum Settings {
  player1_color, player2_color, board_size
}


public class SettingsFileHandler {
  private static final String P1_COLOR = "player1_color:";
  private static final String P2_COLOR = "player2_color:";
  private static final String B_SIZE = "board_size:";

  public static void toFile(int size, Color first, Color second) {
    PrintWriter writer;
    try {
      writer = new PrintWriter("settings.txt", "UTF-8");
      writer.println("# for changing colors or sizes, add it without spaces");
      writer.println(P1_COLOR + first);
      writer.println(P2_COLOR + second);
      writer.println(B_SIZE + Integer.toString(size));
      writer.close();
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      System.out.println("cannot save file");
    }
  }

  public static String[] fromReader(Reader fileReader) {
    // Buffer line reader
    BufferedReader lineReader = new BufferedReader(fileReader);

    // Current read line
    String oneLine = null;

    String[] settings = new String[3];


    try {
      while ((oneLine = lineReader.readLine()) != null) {
        if (oneLine.isEmpty() || oneLine.startsWith("#")) {
          continue;
        }

        // If contains 1st player's color,
        // Add it to the settings list.
        if (oneLine.contains(P1_COLOR)) {
          // Remove 'player1_color' from line.
          oneLine = oneLine.replace(P1_COLOR, "");

          // Add 1st player's color into settings list.
          settings[Settings.player1_color.ordinal()] = oneLine;
        }

        // If contains 2nd player's color,
        // Add it to the settings list.
        if (oneLine.contains(P2_COLOR)) {
          // Remove 'player2_color' from line.
          oneLine = oneLine.replace(P2_COLOR, "");

          // Add 2nd player's color into settings list.
          settings[Settings.player2_color.ordinal()] = oneLine;
        }

        // If contains board_size,
        // Add it to the settings list.
        if (oneLine.contains(B_SIZE)) {
          // Remove 'board_size' from line.
          oneLine = oneLine.replace(B_SIZE, "");

          // Add board size into settings list.
          settings[Settings.board_size.ordinal()] = oneLine;
        }
      }
    } catch (Exception e) {
      System.out.println("Could not read line(s) from file.");
    }
    return settings;
  }
}
