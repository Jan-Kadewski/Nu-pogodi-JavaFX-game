package Project;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
    GameView gameView;
    ResultDialogBox resultDialogBox;
    Runnable myRunnable;
    ImageView imageLeftDown;
    ImageView imageRightTop;
    ImageView imageRightDown;
    ImageView imageLeftTop;
    Image image;
    Runnable eggRunnable;
    ImageView egg1 = null;
    ImageView egg3 = null;

    int boxX;
    int boxY;

    public GameController() {
        gameView = new GameView();
        rightTopButtonListener();
        rightDownButtonListener();
        leftTOpButtonListener();
        leftDownButtonListener();
        initData();
//        generateEgs();
        generateEgg();
        moveByKeyboard();
        timer();
        Thread timer = new Thread(myRunnable);
        Thread myEggThread = new Thread(eggRunnable);
        myEggThread.start();
        timer.start();
        boxX = 0;
        boxY = 0;
    }

    public void initData() {
        GameModel.healthPoint = 4;
        GameModel.points = 0;
        image = new Image("Project/StaticResources/Assets/TopRight");
        imageRightTop = new ImageView(image);
    }

    public void rightTopButtonListener() {
        gameView.btnRightTop.setOnAction(e -> {
            if (e.getSource() == gameView.btnRightTop) {
                moveCharacterToRightTop();
                //TODO: TEN fragment przekleić, gdy jajko wpadnie do koszyka
//                GameModel.points++;
//                gameView.gameScore.setText(String.valueOf(GameModel.points));
            }
        });
    }

    public void leftTOpButtonListener() {
        gameView.btnLeftTop.setOnAction(ev -> {
            if (ev.getSource() == gameView.btnLeftTop) {
                moveCharacterToLeftTop();
            }
        });
    }

    public void leftDownButtonListener() {
        gameView.btnLeftDown.setOnAction(ev -> {
            if (ev.getSource() == gameView.btnLeftDown) {
                moveCharacterToLeftDown();
            }
        });
    }

    public void rightDownButtonListener() {
        gameView.btnRightDown.setOnAction(ev -> {
            if (ev.getSource() == gameView.btnRightDown) {
                moveCharacterToRightDown();
            }
        });
    }

    public void moveCharacterToRightDown() {
        gameView.root.getChildren().removeAll(imageRightTop, imageLeftDown, imageLeftTop, imageRightDown);
        Image image = new Image("Project/StaticResources/Assets/downRight");
        imageRightDown = new ImageView(image);
        imageRightDown.setX(420);
        imageRightDown.setY(326);
        gameView.root.getChildren().add(imageRightDown);
        boxX = 0;
        boxY = 0;
    }

    public void moveCharacterToLeftDown() {
        gameView.root.getChildren().removeAll(imageRightTop, imageLeftTop, imageRightDown, imageLeftDown);
        Image image = new Image("Project/StaticResources/Assets/downLeft");
        imageLeftDown = new ImageView(image);
        imageLeftDown.setX(325);
        imageLeftDown.setY(326);
        gameView.root.getChildren().add(imageLeftDown);
        boxX = 360;
        boxY = 415;

//        Line line = new Line(360, 415, 390, 430);
//        line.setStroke(Color.BLACK);
    }

    public void moveCharacterToLeftTop() {
        gameView.root.getChildren().removeAll(imageRightTop, imageLeftDown, imageRightDown, imageLeftTop);
        Image image = new Image("Project/StaticResources/Assets/topLeft");
        imageLeftTop = new ImageView(image);
        imageLeftTop.setX(305);
        imageLeftTop.setY(250);
        gameView.root.getChildren().add(imageLeftTop);
        System.out.println(imageLeftTop);
        boxX = 0;
        boxY = 0;

    }

    public void moveCharacterToRightTop() {
        gameView.root.getChildren().removeAll(imageRightTop, imageLeftDown, imageLeftTop, imageRightDown);
        gameView.root.getChildren().removeAll(imageLeftDown, imageLeftTop, imageRightDown, imageRightTop);
        imageRightTop.setX(440);
        imageRightTop.setY(250);
        gameView.root.getChildren().add(imageRightTop);
        boxX = 0;
        boxY = 0;
    }


    public void moveByKeyboard() {
        gameView.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                        moveCharacterToRightTop();
                        break;
                    case DOWN:
                        moveCharacterToLeftTop();
                        break;
                    case LEFT:
                        moveCharacterToLeftDown();
                        break;
                    case RIGHT:
                        moveCharacterToRightDown();
                        break;
                }
            }
        });
    }

//
//    public void generateEgs() {
//
//        eggRunnable = new Runnable() {
//            @Override
//            public void run() {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(500);
//                            generateEgg();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//            }
//
//            ;
//
//        };
//
//    }

    ;

    public void generateEgg() {
//        int randomNumber = (int) Math.floor(Math.random() *4) +1;
        int randomNumber = 3;
        switch (randomNumber) {
            case 1:
                egg1 = gameView.createEgg(790, 155, 620, 270, 620, 759, 5500);
                break;

            case 2:
                gameView.createEgg(200, 155, 370, 270, 370, 759, 5500);
                break;

            case 3:
                egg3 = gameView.createEgg(200, 270, 370, 385, 370, 759, 6000);
                break;

            case 4:
                gameView.createEgg(790, 270, 620, 390, 650, 759, 5500);
                break;

        }
    }
    public void timer() {
        myRunnable = new Runnable() {
            boolean flag = false;

            public void run() {
                while (!flag) {
                    GameModel.seconds++;
                    System.out.println(GameModel.seconds);
                    try {
                        if (egg3 != null) {
                            int minXEgg = (int) egg3.getBoundsInParent().getMinX();
                            int minYEgg = (int) egg3.getBoundsInParent().getMinY();

//                            System.out.println(minXEgg + " minXEgg ");
//                            System.out.println(minYEgg + " minYEgg ");


                            if (boxY < minYEgg + 10
                                    && boxY + 10 > minYEgg
                                    && boxX + 5 > minXEgg - 20
                                    && boxX + 5 < minXEgg + 20) {
//                                System.out.println(boxY + " boxY");
//                                System.out.println(boxX + " boxX");
                                Platform.runLater(() -> {
                                    gameView.root.getChildren().remove(egg3);
                                    GameModel.points++;
                                    gameView.gameScore.setText(String.valueOf(GameModel.points));
                                    egg3 = null;
                                    generateEgg();

                                });
                            } else if (minYEgg > 499 && minYEgg < 505) {
                                Platform.runLater(() -> {
                                    gameView.root.getChildren().remove(egg3);
                                    generateEgg();
                                    if (GameModel.healthPoint == 4) {
                                        gameView.root.getChildren().remove(gameView.life4);
                                    } else if (GameModel.healthPoint == 3) {
                                        gameView.root.getChildren().remove(gameView.life3);
                                    } else if (GameModel.healthPoint == 2) {
                                        gameView.root.getChildren().remove(gameView.life2);
                                    } else if (GameModel.healthPoint == 1) {
                                        gameView.root.getChildren().remove(gameView.life1);
                                        new ResultDialogBox();
                                        gameView.stage.close();
                                    }
                                    GameModel.healthPoint--;
                                    System.out.println(GameModel.healthPoint);

                                });

                            }
                        }
                        Thread.sleep(50);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                    if (GameModel.healthPoint == 0) {

                        Platform.runLater( () -> {

                        } );

                        flag = true;

                    }
                }

            }
        };
    }

}
