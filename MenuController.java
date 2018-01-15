/*
 * Kfir Ventura
 * Avihay Arzuan
 */

package reversiApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

  public void handleStartButton() {
    Stage primaryStage = (Stage) (startButton.getScene().getWindow());
    try {
      HBox root = (HBox) FXMLLoader.load(getClass().getResource("Game.fxml"));
      Scene scene = new Scene(root, 600, 400);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setTitle("Reversi");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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

  public void handleQuitButton() {
    Stage stage = (Stage) quitButton.getScene().getWindow();
    stage.close();
  }

}
