package Javafx.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.tinylog.Logger;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GameController {

    private String playerOneName;
    private String playerTwoName;



    public void setPlayerOneName(String playerOneName) {
        this.playerOneName = playerOneName;
    }
    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    @Inject
    private FXMLLoader fxmlLoader;

    private int numOfClickes = 0;

    private int numOfMovesPlayerOne = 0;
    private int numOfMovesPlayerTwo = 0;

    private int theEmptyBox;

    @FXML
    private Button submitButton;


    @FXML
    private Label messageLabel;

    @FXML
    private javafx.scene.shape.Rectangle  Rec1 , Rec2 , Rec3 , Rec4 , Rec5 ,
            Rec6 , Rec7 , Rec8 , Rec9 , Rec10 , Rec11 , Rec12 ,
            Rec13 , Rec14 , Rec15 ;

    private List<javafx.scene.shape.Rectangle> Boxes;

    private String initText = "Player one";

    @FXML
      private void initialize() {
       emptyBox();

       System.out.println(theEmptyBox);

        submitButton.setDisable(true);

        Platform.runLater(() -> messageLabel.setText(String.format(initText)));

       }


       private int emptyBox(){
           Boxes = List.of(Rec1, Rec2, Rec3, Rec4, Rec5,
                   Rec6, Rec7, Rec8, Rec9, Rec10,
                   Rec11, Rec12, Rec13, Rec14, Rec15);

                   Random r=new Random();
                   theEmptyBox =r.nextInt(Boxes.size());
                   return theEmptyBox;
       }



    @FXML
    public void clickedOnBox(MouseEvent mouseEvent) {

            var row = GridPane.getRowIndex((Node) mouseEvent.getSource());
            var col = GridPane.getColumnIndex((Node) mouseEvent.getSource());
            if (col == null) {
                col = 0;
            }


        if (col == theEmptyBox){
            loseTheGame(row,col);
            winGame();
            Logger.debug("number Of Moves for Player One {}", numOfMovesPlayerOne);
            Logger.debug("number Of Moves for Player Two {}", numOfMovesPlayerTwo);
        }else {
            selectBox(row,col);
        }
    }

    private void winGame(){
        if (messageLabel.getText().equals(initText)){

            submitButton.setDisable(true);
            Platform.runLater(() -> messageLabel.setText(String.format("Player two Won!!")));



        }    else {

            Platform.runLater(() -> messageLabel.setText(String.format(initText + " Won!!")));

            submitButton.setDisable(true);
        }

    }




    private void loseTheGame(int row, int col) {

            Logger.debug("You Lost");
            Boxes.get(col).setFill(Color.RED);
           // Platform.runLater(() -> messageLabel.setText(String.format("YOU LOST")));
            for (int i = 0; i < Boxes.size(); i++){
                Boxes.get(i).setOnMouseClicked(null);
            }

    }

        private void selectBox(int row, int col) {
                 submitButton.setDisable(false);

            if (numOfClickes <2){
                Logger.debug("You still can cse a box");
                numOfClickes ++;
                Boxes.get(col).setFill(Color.GRAY);


            }else {
                submitButton.setDisable(false);
                for (int i = 0; i < Boxes.size(); i++){
                    Logger.error("Invalid click");
                    Boxes.get(col).setFill(Color.BLUE);

                }


            }


        }



    @FXML
    public void submitMyTurn(){



        numOfClickes = 0;
        if (messageLabel.getText().equals(initText)){
            numOfMovesPlayerOne++;
            submitButton.setDisable(true);
            Platform.runLater(() -> messageLabel.setText(String.format("Player two")));
        }
        else {
            numOfMovesPlayerTwo++;
            Platform.runLater(() -> messageLabel.setText(String.format(initText)));
            submitButton.setDisable(true);
        }
        Logger.debug(messageLabel.getText() + " have submitted his turn ");


    }


    @FXML
    public void handelFinish(ActionEvent actionEvent) throws IOException {

        Platform.exit();
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/highscores.fxml"));
//        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
    }


















}
