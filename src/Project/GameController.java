package Project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.function.Predicate;

public class GameController {
    GameView gameView;
    ResultDialogBox resultDialogBox;
    Runnable myRunnable;
    ImageView imageLeftDown;
    ImageView imageRightTop;
    ImageView imageRightDown;
    ImageView imageLeftTop;
    Image image;

    public GameController() {
        gameView = new GameView();
        moveCharacterToRightTop();
        moveCharacterToLeftTop();
        moveCharacterToLeftDown();
        moveCharacterToRightDown();
        image = new Image("Project/StaticResources/Assets/TopRight");
        imageRightTop = new ImageView(image);
        resultDialogBox = new ResultDialogBox();
//        timer();
        Thread timer = new Thread(myRunnable);
        timer.start();
        gameView.gameModel.healthPoint = 0;

//        if (gameView.gameModel.healthPoint == 0) {
//
//        }

    }

    public void timer() {
        myRunnable = new Runnable() {
            boolean flag = false;

            public void run() {
                while (!flag) {
                    GameModel.seconds++;
                    System.out.println(GameModel.seconds);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (resultDialogBox != null) {
                    flag = true;
                }
            }
        };
    }

    public void moveCharacterToRightTop() {
        gameView.btnRightTop.setOnAction(e -> {
            if (e.getSource() == gameView.btnRightTop) {
                gameView.root.getChildren().removeAll(imageLeftDown, imageLeftTop, imageRightDown, imageRightTop);
                imageRightTop.setX(440);
                imageRightTop.setY(250);
                gameView.root.getChildren().add(imageRightTop);

            }
        });
    }

    public void moveCharacterToLeftTop() {
        gameView.btnLeftTop.setOnAction(ev -> {
            if (ev.getSource() == gameView.btnLeftTop) {
                gameView.root.getChildren().removeAll(imageRightTop, imageLeftDown, imageRightDown, imageLeftTop);
                Image image = new Image("Project/StaticResources/Assets/topLeft");
                imageLeftTop = new ImageView(image);
                imageLeftTop.setX(305);
                imageLeftTop.setY(250);
                gameView.root.getChildren().add(imageLeftTop);
            }
        });
    }

    public void moveCharacterToLeftDown() {
        gameView.btnLeftDown.setOnAction(ev -> {
            if (ev.getSource() == gameView.btnLeftDown) {
                gameView.root.getChildren().removeAll(imageRightTop, imageLeftTop, imageRightDown, imageLeftDown);
                Image image = new Image("Project/StaticResources/Assets/downLeft");
                imageLeftDown = new ImageView(image);
                imageLeftDown.setX(315);
                imageLeftDown.setY(320);
                gameView.root.getChildren().add(imageLeftDown);
            }
        });
    }

    public void moveCharacterToRightDown() {
        gameView.btnRightDown.setOnAction(ev -> {
            if (ev.getSource() == gameView.btnRightDown) {
                gameView.root.getChildren().removeAll(imageRightTop, imageLeftDown, imageLeftTop, imageRightDown);
                Image image = new Image("Project/StaticResources/Assets/downRight");
                imageRightDown = new ImageView(image);
                imageRightDown.setX(440);
                imageRightDown.setY(320);
                gameView.root.getChildren().add(imageRightDown);

            }
        });
    }



}