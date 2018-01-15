import java.io.BufferedReader;
import java.io.Reader;

enum Settings {
    player1_color, player2_color, opening_color, board_size
}

public class SettingsReader {
    private static final String P1_COLOR = "player1_color:";
    private static final String P2_COLOR = "player2_color:";
    private static final String OPENING_COLOR = "opening_color:";
    private static final String B_SIZE = "board_size:";

    public static String[] fromReader(Reader fileReader) {
        // Buffer line reader
        BufferedReader lineReader = new BufferedReader(fileReader);

        // Current read line
        String oneLine = null;

        String[] settings = new String[4];

        settings[Settings.board_size.ordinal()] = "kaka";

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

                // If contains opening_color,
                // Add it to the settings list.
                if (oneLine.contains(OPENING_COLOR)) {
                    // Remove 'opening_color' from line.
                    oneLine = oneLine.replace(OPENING_COLOR, "");

                    // Add opening color into settings list.
                    settings[Settings.opening_color.ordinal()] = oneLine;
                }
            }
        } catch (Exception e) {
            System.out.println("Could not read line(s) from file.");
        }

        return settings;
    }
}
