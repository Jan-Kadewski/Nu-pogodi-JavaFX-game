package Project;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class GameView extends Application {
    GameModel gameModel;
    Scene scene;
    Group root;

    public GameView() {
        gameModel = new GameModel();
    }

    @Override
    public void start(Stage stage) throws Exception {
        createRootGroupWithAddedElementsToScene();
        setCustomSettingsForMainMenuStage(stage);
    }


    public void setCustomSettingsForMainMenuStage(Stage stage) {
        KeyCombination kc = new KeyCodeCombination(KeyCode.Q,
                KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
        Runnable rn = () -> {
            System.out.println("tu tez");
            stage.close();
            System.out.println("dziala");
        };
        scene.getAccelerators().put(kc, rn);
        stage.setTitle("Nu pogodi");
        stage.setScene(scene);
        stage.getIcons().add(new Image("Project/StaticResources/Assets/wolf.png"));
        stage.show();
    }

    public void createRootGroupWithAddedElementsToScene() {
        root = new Group();
        scene = new Scene(root, 1000, 600);
//        scene.setFill(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE ,new Stop(0, Color.web("#81c483")),new Stop(1,Color.web("#fcc200"))));
        addGrassToGame();
    }

    public void addGrassToGame() {
        for (int i = 0; i < scene.getWidth(); i += 15) {
            Image image = new Image("Project/StaticResources/Assets/grass.png");
            ImageView imageView = new ImageView(image);
            imageView.setX(i);
            imageView.setY(582);
            root.getChildren().add(imageView);
        }
        addRampToGame(150, 70);
        addRampToGame(300, 70);
    }

    public void addRampToGame(int heightLine, int widthLine) {
        Line horizontalLine = new Line();
        horizontalLine.setStartX(0);
        horizontalLine.setEndX(widthLine);
        horizontalLine.setStartY(heightLine);
        horizontalLine.setEndY(heightLine);
        horizontalLine.setStrokeWidth(4);

        root.getChildren().add(horizontalLine);

        Line obliqueLine = new Line();
        obliqueLine.setStartX(widthLine);
        obliqueLine.setEndX(widthLine + 150);
        obliqueLine.setStartY(heightLine);
        obliqueLine.setEndY(heightLine + 100);
        obliqueLine.setStrokeWidth(4);
        root.getChildren().add(obliqueLine);
    }


}
