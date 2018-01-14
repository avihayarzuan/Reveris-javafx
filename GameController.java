/*
 * Kfir Ventura Avihay Arzuan
 */

package reversiApp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameController implements Initializable {

  @FXML
  private HBox root;
  //
  int defSize = DefineConstants.getBoardSize();
  Color firstColor = DefineConstants.getFirstColor();
  Color secondColor = DefineConstants.getSecondColor();
  // layout objects:
  public Label turn;
  public Label player1Points;
  public Label player2Points;
  public Button exitButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    Board board = new Board(defSize);
    board.setPrefWidth(400);
    board.setPrefHeight(400);
    root.getChildren().add(0, board);
    board.draw();

    turn.textProperty()
        .bind(Bindings.createStringBinding(() -> ("Player " + board.p.get()) + " turn", board.p));
    player1Points.textProperty()
        .bind(Bindings.createStringBinding(() -> (" " + board.points1.get()), board.points1));
    player2Points.textProperty()
        .bind(Bindings.createStringBinding(() -> (" " + board.points2.get()), board.points2));

    root.heightProperty().addListener((observable, oldValue, newValue) -> {
      board.setPrefHeight(newValue.doubleValue());
      board.draw();
    });
    root.widthProperty().addListener((observable, oldValue, newValue) -> {
      double boardNewWidth = newValue.doubleValue()-120;
      board.setPrefWidth(boardNewWidth);
      board.draw();
    });
  }

  /**
   * method called when exit button is pressed.
   * change the stage to the mainMenu stage.
   */
  public void handleExit() {
    try {
      Stage stage = (Stage) exitButton.getScene().getWindow();
      AnchorPane start = (AnchorPane) FXMLLoader.load(getClass().getResource("Menu.fxml"));

      Scene sceneStart = new Scene(start, 600, 600);

      sceneStart.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      stage.setScene(sceneStart);
      stage.show();
//      stage.close(); /// delete afterwards
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
