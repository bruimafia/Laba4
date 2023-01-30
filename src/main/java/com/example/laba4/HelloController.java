package com.example.laba4;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;

import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private Button btn_addDog, btn_addCrane, btn_addTuna;
    @FXML
    private TextField tf_coorXDog, tf_coorYDog, tf_coorXCrane, tf_coorYCrane, tf_coorXTuna, tf_coorYTuna;
    @FXML
    private Spinner dogSpeed, craneSpeed, tunaSpeed, dogJump;
    Canvas canvas = new Canvas(850, 300);
    Canvas canvasAnimal = new Canvas(850, 300);
    GraphicsContext sky = canvas.getGraphicsContext2D();
    GraphicsContext lake = canvas.getGraphicsContext2D();
    GraphicsContext ground = canvas.getGraphicsContext2D();
    GraphicsContext gcDog = canvasAnimal.getGraphicsContext2D();
    GraphicsContext gcCrane = canvasAnimal.getGraphicsContext2D();
    GraphicsContext gcTune = canvasAnimal.getGraphicsContext2D();

    // test
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pane.getChildren().clear();
        pane.getChildren().add(canvas);
        pane.getChildren().add(canvasAnimal);

        sky.setFill(Color.LIGHTBLUE);
        sky.fillRect(0, 0, 850, 150);

        lake.setFill(Color.CYAN);
        lake.fillRect(0, 150, 450, 150);

        ground.setFill(Color.GREEN);
        ground.fillRect(450, 150, 400, 150);

        pane.sceneProperty().addListener(new ChangeListener<Scene>() {

            @Override
            public void changed(ObservableValue<? extends Scene> observableValue, Scene scene, Scene t1) {

                    pane.getScene().setOnKeyPressed((KeyEvent ke) -> {
                        if (ke.getCode().equals(KeyCode.RIGHT)) {
                            System.out.println("dfhk");
                        }
                    });

            }

        });

    }

    int currentXDog, currentYDog;

    List<Dog> dogList = new ArrayList<>();

    public void onAddDog() {
        int startDogX = Math.max(Integer.parseInt(tf_coorXDog.getText()), 450);
        int startDogY = 140;
        Dog dog = new Dog(startDogX, startDogY,
                (int) Double.parseDouble(dogSpeed.getValue().toString()), Double.parseDouble(dogJump.getValue().toString()));
        dogList.add(dog);
        gcDog.drawImage(dog.image, startDogX, startDogY, 40, 30);
        dog.currentX = startDogX;
        dog.currentY = startDogY;
        canvasAnimal.setOnMousePressed(mouseEvent -> {
            moveDog((int) mouseEvent.getX(), (int) mouseEvent.getY());
        });

//        for (Image i : dogList) {
//            int startDogX = Math.max(Integer.parseInt(tf_coorXDog.getText()), 150);
//            int startDogY = 150;
////
//            gcDog.drawImage(i, startDogX, startDogY, 40, 30);
//            currentXDog = startDogX;
//            currentYDog = startDogY;
//            System.out.println(i.getUrl());
////            canvasAnimal.setOnMousePressed(mouseEvent -> {
////                moveDog((int) mouseEvent.getX(), (int) mouseEvent.getY());
////            });
//        }





    }

    List<Crane> craneList = new ArrayList<>();
    int currentXCrane, currentYCrane;

    public void onAddCrane() {
        int startCraneX = Integer.parseInt(tf_coorXCrane.getText());
        int startCraneY = Math.min(Integer.parseInt(tf_coorYCrane.getText()), 150);
        Crane crane = new Crane(startCraneX, startCraneY, (int) Double.parseDouble(craneSpeed.getValue().toString()));
        craneList.add(crane);
        gcCrane.drawImage(crane.image, startCraneX, startCraneY, 40, 40);
        crane.currentX = startCraneX;
        crane.currentY = startCraneY;
        canvasAnimal.setOnMousePressed(mouseEvent -> {
            moveCrane((int) mouseEvent.getX(), (int) mouseEvent.getY());
        });
    }


    int currentXTune, currentYTune;
    List<Tuna> tunaList = new ArrayList<>();

    public void onAddTuna() {
        int startTunaX = Math.min(Integer.parseInt(tf_coorXTuna.getText()), 450);
        int startTunaY = Math.max(Integer.parseInt(tf_coorYTuna.getText()), 150);
        Tuna tuna = new Tuna(startTunaX, startTunaY,
                (int) Double.parseDouble(tunaSpeed.getValue().toString()));
        tunaList.add(tuna);
        gcTune.drawImage(tuna.image, startTunaX, startTunaY, 20, 20);
        tuna.currentX = startTunaX;
        tuna.currentY = startTunaY;
        canvasAnimal.setOnMousePressed(mouseEvent -> {
            moveTuna((int) mouseEvent.getX(), (int) mouseEvent.getY());
        });
    }


    Timer timerDrawDog;
    public void moveDog(int finalX, int finalY) {
        timerDrawDog = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                int stepX = (int) Double.parseDouble(dogSpeed.getValue().toString());
                for (Dog dog : dogList) {
                    if (dog.currentX >= finalX)
                        timerDrawDog.cancel();

                    dog.currentX += stepX;
                    gcDog.clearRect(dog.currentX, dog.currentY, 40, 30);
                    gcDog.drawImage(dog.image, dog.currentX, dog.currentY, 40, 30);
                }
            }
        };
        timerDrawDog.schedule(task, 1000L, 100L);
    }


//    Timer timerDrawDog;
//
//    public void moveDog(int finalX, int finalY) {
//        timerDrawDog = new Timer();
//        TimerTask task = new TimerTask() {
//
//            public void run() {
//                int stepX = (int) Double.parseDouble(dogSpeed.getValue().toString());
//                for (Dog dog : dogList) {
//                    if (dog.currentX < finalX)
//                        dog.currentX += stepX;
//                        timerDrawDog.cancel();
//
////                    dog.currentX += stepX;
//                    gcDog.clearRect(dog.currentX, dog.currentY, 40, 30);
//                    gcDog.drawImage(dog.image, dog.currentX, dog.currentY, 40, 30);
//                }
//            }
//        };
//        timerDrawDog.schedule(task, 1000L, 100L);
//    }

    Timer timerDrawCrane;

    public void moveCrane(int finalX, int finalY) {
        timerDrawCrane = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                int stepX = (int) (Double.parseDouble(craneSpeed.getValue().toString()) / 2);
                for (Crane crane : craneList) {
                    if (crane.currentX >= finalX)
                        timerDrawCrane.cancel();

                    gcCrane.clearRect(crane.currentX, crane.currentY, 40, 40);
                    crane.currentX += stepX;
                    gcCrane.drawImage(crane.image, crane.currentX, crane.currentY, 40, 40);
                }
            }
        };
        timerDrawCrane.schedule(task, 1000L, 100L);
    }

    Timer timerDrawTuna;

    public void moveTuna(int finalX, int finalY) {

        timerDrawTuna = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                int stepX = (int) Double.parseDouble(tunaSpeed.getValue().toString());

                for (Tuna tuna : tunaList) {
                    if (tuna.currentX >= finalX)
                        timerDrawTuna.cancel();

                    gcTune.clearRect(tuna.currentX, tuna.currentY, 25, 20);
                    tuna.currentX += (stepX * 0.7);
                    tuna.currentY+=Math.sin(tuna.currentX)*10;

                    gcTune.drawImage(tuna.image, tuna.currentX, tuna.currentY, 25, 20);
                }
            }
        };
        timerDrawTuna.schedule(task, 1000L, 1000L);
    }
}
