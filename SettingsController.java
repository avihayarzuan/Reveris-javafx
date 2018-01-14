package reversiApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SettingsController {

  public ChoiceBox<Integer> sizeChoice;
  public ColorPicker firstPlayerColor;
  public ColorPicker secondPlayerColor;

  public Button returnButton;
  public Button saveButton;

  private ObservableList<Integer> options =
      FXCollections.observableArrayList(4, 6, 8, 10, 12, 14, 16, 18, 20);

  @FXML
  private void initialize() {
    sizeChoice.setValue(8);
    sizeChoice.setItems(options);
  }

  public void handleReturn() {
    Stage stage = (Stage) returnButton.getScene().getWindow();
    stage.close();
  }

  public void handleSave() { // need to change this to save to file
    Color first = firstPlayerColor.getValue();
    Color second = secondPlayerColor.getValue();
    int s = sizeChoice.getValue();
    System.out.println(first);
    System.out.println(second);
    System.out.println(s);
    
  }


}
