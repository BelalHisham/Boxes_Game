package Javafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import javax.inject.Inject;
import java.io.IOException;

public class OpeningController {

    @Inject
    public FXMLLoader fxmlLoader;
    public Button startButton;

    @FXML
    private TextField playerOneNameTextField;

    @FXML
    private TextField playerTwoNameTextField;

    @FXML
    private Label errorLabelPlayerOne;
    @FXML
    private Label errorLabelPlayerTwo;


    public void startAction(ActionEvent actionEvent) throws IOException {

        if (playerOneNameTextField.getText().isEmpty()) {
            errorLabelPlayerOne.setText("Please enter your name!");
        }
       else if (playerTwoNameTextField.getText().isEmpty()) {
            errorLabelPlayerTwo.setText("Please enter your name!");
        }
       else {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Game.fxml"));
//            fxmlLoader.<Javafx.controllers.GameController>getController().setPlayerOneName(playerOneNameTextField.getText());
//            fxmlLoader.<Javafx.controllers.GameController>getController().setPlayerTwoName(playerTwoNameTextField.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("The user's name is set to {}, loading game scene", playerOneNameTextField.getText());
        }
    }

}
