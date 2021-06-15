package Project;


import javafx.scene.Group;
import javafx.scene.control.Button;

public class MainMenu {
    Button startGame;
    Button highScore;
    Button exit;
    Group root;
    Button backToMenu;

    public MainMenu() {
        initMainMenuButtons();
        CreateRootWithMainMenuButttons();
    }

    public void initMainMenuButtons() {
        startGame = new Button("Start Game");
        startGame.setTranslateX(250);
        startGame.setTranslateY(80);
        startGame.setPrefSize(100, 30);
        highScore = new Button("High score");
        highScore.setTranslateX(250);
        highScore.setTranslateY(180);
        highScore.setPrefSize(100, 30);
        exit = new Button("Exit");
        exit.setTranslateX(250);
        exit.setTranslateY(280);
        exit.setPrefSize(100, 30);

    }

    public void CreateRootWithMainMenuButttons() {
        root = new Group();
        root.getChildren().addAll(startGame, highScore, exit);
    }
}

