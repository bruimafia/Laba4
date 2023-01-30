package com.example.laba4;

import javafx.scene.image.Image;

import java.util.Timer;

public class Dog extends WalkingAnimal {

    public Image image = new Image("dog1.gif");

    Dog(int startX, int startY, int speed, double heightOfJump) {
        this.startX = startX;
        this.startY = startY;
        this.speed = speed;
        this.heightOfJump = heightOfJump;
    }

}
