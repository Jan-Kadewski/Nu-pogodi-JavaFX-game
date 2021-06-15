package Project;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

public class GameView extends Application {
    GameModel gameModel;
    Scene scene;
    Group root;
    Main main;
    Button btnRightTop;
    Button btnLeftTop;
    Button btnRightDown;
    Button btnLeftDown;

    public GameView() {
        gameModel = new GameModel();
        root = new Group();
        scene = new Scene(root, 1000, 600);
        btnRightTop = new Button();
        btnLeftTop = new Button();
        btnRightDown = new Button();
        btnLeftDown = new Button();
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
            stage.close();
            main = new Main();
            try {
                main.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        scene.getAccelerators().put(kc, rn);
        scene.setFill(Color.LIGHTGRAY);
        stage.setTitle("Nu pogodi");
        stage.setScene(scene);
        stage.getIcons().add(new Image("Project/StaticResources/Assets/wolf.png"));
        stage.show();
    }

    public void createRootGroupWithAddedElementsToScene() {

//      1000x600  scene.setFill(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE ,new Stop(0, Color.web("#81c483")),new Stop(1,Color.web("#fcc200"))));
        addGrassToGame();
        addLeftRamps(170, 200, 320, 250);
        addLeftRamps(290, 200, 320, 370);
        addRightRamps(170, 200, 320, 250);
        addRightRamps(290, 200, 320, 370);
        addBushTopRightCorner();
        addBushOnTheGround();
        addCloudToGame();
        buttonControls();
        addChickensOnTheLeftSide(10, 60);
        addChickensOnTheLeftSide(10, 190);
        addChickensOnTheRightSide((int) (scene.getWidth() - 80), 60);
        addChickensOnTheRightSide((int) (scene.getWidth() - 80), 190);
        createEgg();
    }

    public void addGrassToGame() {
        for (int i = 0; i < scene.getWidth(); i += 15) {
            Image image = new Image("Project/StaticResources/Assets/grass.png");
            ImageView imageView = new ImageView(image);
            imageView.setX(i);
            imageView.setY(582);
            root.getChildren().add(imageView);
        }
    }

    public void addLeftRamps(int heightLine, int widthLine, int verticalWidthLine, int verticalHeightLine) {
        Line horizontalLine = new Line();
        Line verticalLine = new Line();
        horizontalLine.setStartX(0);
        horizontalLine.setEndX(widthLine);
        horizontalLine.setStartY(heightLine);
        horizontalLine.setEndY(heightLine);
        horizontalLine.setStrokeWidth(4);
        horizontalLine.setStroke(Color.SADDLEBROWN);
        verticalLine.setStartX(verticalWidthLine);
        verticalLine.setEndX(verticalWidthLine);
        verticalLine.setStartY(verticalHeightLine);
        verticalLine.setEndY(verticalHeightLine + 30);
        verticalLine.setStrokeWidth(4);
        verticalLine.setStroke(Color.SADDLEBROWN);

        Line obliqueLine = new Line();
        obliqueLine.setStartX(widthLine);
        obliqueLine.setEndX(widthLine + 150);
        obliqueLine.setStartY(heightLine);
        obliqueLine.setEndY(heightLine + 100);
        obliqueLine.setStrokeWidth(4);
        obliqueLine.setStroke(Color.SADDLEBROWN);

        root.getChildren().add(horizontalLine);
        root.getChildren().add(verticalLine);
        root.getChildren().add(obliqueLine);

    }

    public void addRightRamps(int heightLine, int widthLine, int verticalWidthLine, int verticalHeightLine) {
        Line horizontalLine = new Line();
        Line verticalLine = new Line();
        horizontalLine.setStroke(Color.SADDLEBROWN);
        verticalLine.setStroke(Color.SADDLEBROWN);

        horizontalLine.setStartX(scene.getWidth());
        horizontalLine.setEndX(scene.getWidth() - widthLine);
        horizontalLine.setStartY(heightLine);
        horizontalLine.setEndY(heightLine);
        horizontalLine.setStrokeWidth(4);
        verticalLine.setStartX(scene.getWidth() - verticalWidthLine);
        verticalLine.setEndX(scene.getWidth() - verticalWidthLine);
        verticalLine.setStartY(verticalHeightLine);
        verticalLine.setEndY(verticalHeightLine + 30);
        verticalLine.setStrokeWidth(4);
        Line obliqueLine = new Line();
        obliqueLine.setStroke(Color.SADDLEBROWN);

        obliqueLine.setStartX(scene.getWidth() - widthLine);
        obliqueLine.setEndX(scene.getWidth() - widthLine - 150);
        obliqueLine.setStartY(heightLine);
        obliqueLine.setEndY(heightLine + 100);
        obliqueLine.setStrokeWidth(4);
        root.getChildren().add(horizontalLine);
        root.getChildren().add(verticalLine);
        root.getChildren().add(obliqueLine);
    }

    public void addBushTopRightCorner() {
        Image image = new Image("Project/StaticResources/Assets/rightCornetTree.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(scene.getWidth() - 50);
        imageView.setY(0);
        root.getChildren().add(imageView);
    }

    public void addBushOnTheGround() {
        Image imageLeft = new Image("Project/StaticResources/Assets/bush_ground.png");
        ImageView imageViewLeft = new ImageView(imageLeft);
        imageViewLeft.setX(-85);
        imageViewLeft.setY(450);
        root.getChildren().add(imageViewLeft);

        Image imageRight = new Image("Project/StaticResources/Assets/bush_ground.png");
        ImageView imageViewRight = new ImageView(imageRight);
        imageViewRight.setX(830);
        imageViewRight.setY(450);
        root.getChildren().add(imageViewRight);
    }

    public void addCloudToGame() {
        Image image = new Image("Project/StaticResources/Assets/cloud.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(0);
        root.getChildren().add(imageView);
        TranslateTransition transale = new TranslateTransition();
        transale.setNode(imageView);
        transale.setDuration(Duration.millis(3000));
        transale.setCycleCount(TranslateTransition.INDEFINITE);
        transale.setByX(200);
        transale.setAutoReverse(true);
        transale.play();
    }

    public void addChickensOnTheLeftSide(int x, int y) {
        Image imageChicken = new Image("Project/StaticResources/Assets/chicken2.png");
        ImageView imageView = new ImageView(imageChicken);
        imageView.setX(x);
        imageView.setY(y);
        root.getChildren().add(imageView);
        TranslateTransition transale = new TranslateTransition();
        transale.setNode(imageView);
        transale.setDuration(Duration.millis(900));
        transale.setCycleCount(TranslateTransition.INDEFINITE);
        transale.setByX(50);
        transale.setAutoReverse(true);
        transale.play();
    }

    public void addChickensOnTheRightSide(int x, int y) {
        Image imageChicken = new Image("Project/StaticResources/Assets/chicken.png");
        ImageView imageView = new ImageView(imageChicken);
        imageView.setX(x);
        imageView.setY(y);
        root.getChildren().add(imageView);
        TranslateTransition transale = new TranslateTransition();
        transale.setNode(imageView);
        transale.setDuration(Duration.millis(900));
        transale.setCycleCount(TranslateTransition.INDEFINITE);
        transale.setByX(-50);
        transale.setAutoReverse(true);
        transale.play();
    }

    public void buttonControls() {

        Image imgLeftDown = new Image("Project/StaticResources/Assets/arrowLeftDown.png");
        Image imgLeftTop = new Image("Project/StaticResources/Assets/arrowLeftTop.png");
        Image imgRightDown = new Image("Project/StaticResources/Assets/arrowRightDown.png");
        Image imgRightTop = new Image("Project/StaticResources/Assets/arrowRightTop.png");
        ImageView viewLeftDown = new ImageView(imgLeftDown);
        ImageView viewLeftTop = new ImageView(imgLeftTop);
        ImageView viewRightDown = new ImageView(imgRightDown);
        ImageView viewRightTop = new ImageView(imgRightTop);
        viewLeftDown.setFitHeight(24);
        viewLeftDown.setPreserveRatio(true);
        btnLeftDown.setGraphic(viewLeftDown);
        viewLeftTop.setFitHeight(24);
        viewLeftTop.setPreserveRatio(true);
        btnLeftTop.setGraphic(viewLeftTop);
        viewRightDown.setFitHeight(24);
        viewRightDown.setPreserveRatio(true);
        btnRightDown.setGraphic(viewRightDown);
        viewRightTop.setFitHeight(24);
        viewRightTop.setPreserveRatio(true);
        btnRightTop.setGraphic(viewRightTop);
        btnRightTop.setTranslateX(750);
        btnRightTop.setTranslateY(450);
        btnLeftTop.setTranslateX(200);
        btnLeftTop.setTranslateY(450);
        btnRightDown.setTranslateX(750);
        btnRightDown.setTranslateY(530);
        btnLeftDown.setTranslateX(200);
        btnLeftDown.setTranslateY(530);
        root.getChildren().addAll(btnLeftTop, btnRightTop, btnLeftDown, btnRightDown);
    }


    public void showPointsNumber() {
        Label points = new Label();
        points.setText(String.valueOf(GameModel.points));
        root.getChildren().add(points);
    }

    public void createEgg(){
        Image eggImage = new Image("Project/StaticResources/Assets/egg2.png");
        ImageView eggImageView = new ImageView(eggImage);
        eggImageView.setX(450);
        eggImageView.setY(200);

        RotateTransition rotate = new RotateTransition(Duration.millis(5500),eggImageView);
        rotate.setFromAngle(0);
        rotate.setToAngle(-360);

        Path path = new Path();
        path.getElements().add(new MoveTo(790,155));
        LineTo line1 = new LineTo(650,250);
        LineTo line2 = new LineTo(550,759);
        path.getElements().addAll(line1,line2);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(5500));
        pathTransition.setNode(eggImageView);
        pathTransition.setPath(path);
//        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        pathTransition.setCycleCount(550);
//        pathTransition.setAutoReverse(false);
//        pathTransition.play();

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rotate,pathTransition);
        parallelTransition.setAutoReverse(false);
        parallelTransition.play();

        root.getChildren().add(eggImageView);




    }

}


