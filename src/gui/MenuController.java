/*
 * Kfir Ventura
 * Avihay Arzuan
 */

package gui;

import java.io.File;
import java.io.FileReader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import reversiApp.DefineConstants;

public class MenuController {
  //button members
  public Button startButton;
  public Button settingButton;
  public Button quitButton;
  //menu members
  public MenuItem quitMenu;
  public Menu fileMenu;
  public MenuBar menuBar;
  public Menu settingsMenu;
  public MenuItem changeItem;

  /**
   * the method handle to pressing the start button
   * the method write to file and create one if not already exist.
   */
  public void handleStartButton() {
    //reading files part:
    File file = new File("settings.txt");
    FileReader reader = null;
    try {
      reader = new FileReader(file);
      String[] sets = SettingsFileHandler.fromReader(reader);
      int size = Integer.parseInt(sets[Settings.board_size.ordinal()]);
      Color player_1 = Color.web(sets[Settings.player1_color.ordinal()]);
      Color player_2 = Color.web(sets[Settings.player2_color.ordinal()]);
      DefineConstants.setConstants(size, player_1, player_2);
    } catch (Exception e) {
      DefineConstants.setConstants(8, Color.BLACK, Color.WHITE);
    }
    //change the stage to the game state:
    Stage primaryStage = (Stage) (startButton.getScene().getWindow());
    try {
      HBox root = (HBox) FXMLLoader.load(getClass().getResource("Game.fxml"));
      Scene scene = new Scene(root, 600, 400);
      primaryStage.setTitle("Reversi");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * method to handle pressing the settings tab
   * it opens a new window and stop the main window to
   * get changes until the window is closed
   */
  public void handleSettingsButton() {
    try {
      BorderPane set = (BorderPane) FXMLLoader.load(getClass().getResource("Settings.fxml"));
      Scene sceneSet = new Scene(set, 600, 400);
      Stage secondaryStage = new Stage();
      secondaryStage.setScene(sceneSet);
      secondaryStage.initModality(Modality.APPLICATION_MODAL);
      secondaryStage.showAndWait();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * the method activates when the quit button is pressed
   * it closes the window
   */
  public void handleQuitButton() {
    Stage stage = (Stage) quitButton.getScene().getWindow();
    stage.close();
  }

}
