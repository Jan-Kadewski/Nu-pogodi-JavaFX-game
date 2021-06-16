package Project;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainMenuController {
    MainMenu mainMenu;
    Scene highScoreScene;
    GameController gameController;
    public MainMenuController() {
        mainMenu = new MainMenu();
        exitProgram();
    }

    public void startGameAction(Stage stage) {
        mainMenu.startGame.setOnAction(event -> {
            if (event.getSource() == mainMenu.startGame) {
                stage.close();
                gameController = new GameController();
                try {
                    gameController.gameView.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showHighScore(Stage stage) {
        mainMenu.highScore.setOnAction(event -> {
            if (event.getSource() == mainMenu.highScore) {
                HBox hBox = new HBox();
                FileManipulation.readHighRankFromFile();
                ListView listView = new ListView();
//                listView.getItems().add(gameController.resultDialogBox.fileManipulation.name + "time" + GameModel.seconds +10 );
                hBox.getChildren().add(listView);
                highScoreScene = new Scene(hBox,400,200);
                stage.setScene(highScoreScene);

            }
        });
    }
    public void exitProgram() {
        mainMenu.exit.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == mainMenu.exit) {
                Platform.exit();
            }
        });
    }



}
