/*
 * Kfir Ventura Avihay Arzuan
 */
package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsController {

  // layout members:
  public ChoiceBox<Integer> sizeChoice;
  public ColorPicker firstPlayerColor;
  public ColorPicker secondPlayerColor;
  public Button returnButton;
  public Button saveButton;

  // parameters to enter the choiceBox:
  private ObservableList<Integer> options =
      FXCollections.observableArrayList(4, 6, 8, 10, 12, 14, 16, 18, 20);

  @FXML
  private void initialize() {
    sizeChoice.setValue(8);
    sizeChoice.setItems(options);
  }

  /**
   * method to run when return button is pressed the method closes the stage
   */
  public void handleReturn() {
    Stage stage = (Stage) returnButton.getScene().getWindow();
    stage.close();
  }

  /**
   * the method save the the values given from screen to file settings.txt
   */
  public void handleSave() { // need to change this to save to file
    Color first = firstPlayerColor.getValue();
    Color second = secondPlayerColor.getValue();
    int size = sizeChoice.getValue();
    SettingsFileHandler.toFile(size, first, second);
  }


}
