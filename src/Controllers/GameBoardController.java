package Controllers;

import GameFiles.Board;
import GameFiles.Cell;
import Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;


public class GameBoardController {

    // DECLARATIONS //

    private boolean running = false;
    private Board enemyBoard, playerBoard;

    private int shipsToPlace = 5;

    private boolean enemyTurn = true;

    private Random random = new Random();

    // FIRST TURN BOOLEAN  //

    private boolean enemyTurn() {

        Random randomSTART = new Random();
        return randomSTART.nextBoolean();
    }


    // GAME //

    private Parent createContent() {

        // MAIN WINDOW //

        BorderPane root = new BorderPane();
        root.setPrefSize(1480, 920);

        // TOP TOOLBAR //

        ToolBar toolbar = new ToolBar();
        root.setTop(toolbar);

        // SET TOOLBAR FIELDS //

        Text txt1 = new Text();
        TextField txt2 = new TextField();
        Text txt3 = new Text();
        TextField txt4 = new TextField();
        Text txt5 = new Text();
        TextField txt6 = new TextField();
        Text txt7 = new Text();
        TextField txt8 = new TextField();
        Text txt9 = new Text();
        TextField txt10 = new TextField();
        Text txt11 = new Text();
        TextField txt12 = new TextField();

        txt1.setText("Player Ships");
        txt2.setText("N/A");
        txt2.setDisable(true);
        txt2.setStyle("-fx-opacity: 1;");
        txt3.setText("Enemy Ships");
        txt4.setText("N/A");
        txt4.setDisable(true);
        txt4.setStyle("-fx-opacity: 1;");
        txt5.setText("Player Points");
        txt6.setText("N/A");
        txt6.setDisable(true);
        txt6.setStyle("-fx-opacity: 1;");
        txt7.setText("Enemy Points");
        txt8.setText("N/A");
        txt8.setDisable(true);
        txt8.setStyle("-fx-opacity: 1;");
        txt9.setText("Player Percentage");
        txt10.setText("N/A");
        txt10.setDisable(true);
        txt10.setStyle("-fx-opacity: 1;");
        txt11.setText("Enemy Percentage");
        txt12.setText("N/A");
        txt12.setDisable(true);
        txt12.setStyle("-fx-opacity: 1;");

        Separator separator = new Separator();
        Separator separator2 = new Separator();

        // POPULATE TOOLBAR //

        toolbar.getItems().addAll(txt1,txt2,txt3,txt4,separator,txt5,txt6,txt7,txt8,separator2,txt9,txt10,txt11,txt12);

        // RIGHT SIDE //

        Text TotalShips = new Text("Your fleet:");
        TotalShips.setStyle("-fx-font-weight: bold");

        Text carrierTotal = new Text("Carriers available:");
        Text battleshipTotal = new Text("Battleships available:");
        Text cruiserTotal = new Text("Cruisers available:");
        Text submarineTotal = new Text("Submarines available:");
        Text destroyerTotal = new Text("Destroyers available:");
        TextField carrier = new TextField("1");
        carrier.setDisable(true);
        carrier.setStyle("-fx-opacity: 1;");
        TextField battleship = new TextField("1");
        battleship.setDisable(true);
        battleship.setStyle("-fx-opacity: 1;");
        TextField cruiser = new TextField("1");
        cruiser.setDisable(true);
        cruiser.setStyle("-fx-opacity: 1;");
        TextField submarine = new TextField("1");
        submarine.setDisable(true);
        submarine.setStyle("-fx-opacity: 1;");
        TextField destroyer = new TextField("1");
        destroyer.setDisable(true);
        destroyer.setStyle("-fx-opacity: 1;");
        Text play = new Text("");

        VBox rightvbox = new VBox(10,TotalShips,carrierTotal, carrier, battleshipTotal, battleship, cruiserTotal, cruiser, submarineTotal, submarine, destroyerTotal, destroyer, play);
        rightvbox.setAlignment(Pos.BASELINE_LEFT);
        rightvbox.setStyle("-fx-padding: 16;" + "-fx-border-color: black;");
        root.setRight(rightvbox);

        // LEFT SIDE //



        // ENEMY BOARD //

        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 0) {
                System.out.println("YOU WIN");
                running = false;
            }

            if (enemyTurn)
                enemyMove();
        });

        // PLAYER BOARD //

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if(shipsToPlace==5) {
                if (playerBoard.placeCarrier(new Carrier(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                    --shipsToPlace;
                    carrier.setText("0");
                }
            }
                if(shipsToPlace==4){
                    if(playerBoard.placeBattleship(new Battleship(shipsToPlace,event.getButton()==MouseButton.PRIMARY), cell.x, cell.y)) {
                        battleship.setText("0");
                        --shipsToPlace;
                    }
                }
                if(shipsToPlace==3) {
                    if (playerBoard.placeCruiser(new Cruiser(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                        cruiser.setText("0");
                        --shipsToPlace;
                    }
                }
                if(shipsToPlace==2) {
                    if (playerBoard.placeSubmarine(new Submarine(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                        submarine.setText("0");
                        --shipsToPlace;
                    }
                }
                if(shipsToPlace==1) {
                    if (playerBoard.placeDestroyer(new Destroyer(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
                        destroyer.setText("0");
                        --shipsToPlace;
                    }
                }
                if (shipsToPlace == 0) {
                    play.setText("All set! Fire at Will!");
                    startGame();
                }

        });

        // MAIN BOARD //

        Text txtME= new Text();
        Text txtENEMY = new Text();
        txtME.setText("My Board");
        txtENEMY.setText("Enemy Board");

        VBox vbox = new VBox(40,txtENEMY, enemyBoard, txtME ,playerBoard);
        vbox.setAlignment(Pos.CENTER);

        root.setCenter(vbox);

        return root;
    }

    // ENEMY PLAYSTYLE //

    private void enemyMove() {
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                System.out.println("YOU LOSE");
                infoBox("You lost!", null, "Bad Luck!");
                running = false;
            }
        }
    }

    // PLACE ENEMY SHIPS. THEN GAME STARTS //

    private void startGame() {
        // place enemy ships
        int count = 5;

            int x = random.nextInt(10);
            int y = random.nextInt(10);

            while (count>0){
                   System.out.println(count);
                if (count == 5) {
                    if(enemyBoard.placeCarrier(new Carrier(5, Math.random() < 0.5), x, y)) {
                        count--;
                   //    System.out.println(count);
                    }
                }
                if(count == 4) {
                    if(enemyBoard.placeBattleship(new Battleship(4, Math.random() < 0.5), x, y)) {
                        count--;
                     //   System.out.println(count);
                    }
                }
                if(count==3){
                    if(enemyBoard.placeCruiser(new Cruiser(3, Math.random() < 0.5), x, y)) {
                        count--;
                    //    System.out.println(count);
                    }
                }
                if(count==2){
                    if(enemyBoard.placeSubmarine(new Submarine(3, Math.random() < 0.5), x, y)) {
                        count--;
                    //    System.out.println(count);
                    }
                }
                if(count==1) {
                    if(enemyBoard.placeDestroyer(new Destroyer(2, Math.random() < 0.5), x, y)) {
                        count--;
                     //   System.out.println(count);
                    }
                }
            }

        if (enemyTurn()) {
            infoBox("You go second!", null, "Unlucky, sir!");
            enemyMove();
        }
        else {
            infoBox("You go first!", null, "We got the upper hand!");
        }

        running = true;
    }

    // PLAY BUTTON //

    @FXML
    private void GameScene(ActionEvent event) throws IOException {
        Scene scene2 = new Scene(createContent());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    // INFO BOX POPUP //

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

}