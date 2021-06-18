package Project;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.text.Position;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainMenuController {
    MainMenu mainMenu;
    Scene highScoreScene;
    GameController gameController;
    FileManipulation fileManipulation;
    Button backToMenu;
    Main main;
    public MainMenuController() {
        mainMenu = new MainMenu();
        backToMenu = new Button("Back");
        exitProgram();
        try {
            fileManipulation = new FileManipulation();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                HBox HBox = new HBox();
                Label label = new Label("Latest results");
                backToMenu.setPrefSize(100,30);
                label.setTranslateX(150);
                label.setPrefSize(150,20);
                List<String> lines = fileManipulation.readHighRankFromFile();
                ListView listView = new ListView();
                for(int i=0;i<lines.size();i++){
                    listView.getItems().add(lines.get(i));
                }
                listView.setMinSize(350,350);
                listView.setTranslateY(30);
                HBox.getChildren().addAll(backToMenu,label,listView);
                highScoreScene = new Scene(HBox,500,500);
                stage.setScene(highScoreScene);
                stage.setTitle("Latest score");
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

    public void backToMenuFromHighScore(Stage stage){
        backToMenu.setOnAction(e->{
            if(e.getSource() == backToMenu){
                stage.close();
                main = new Main();
                try {
                    main.start(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
