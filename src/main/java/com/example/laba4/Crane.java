package com.example.laba4;

import javafx.scene.image.Image;

public class Crane extends FlyingAnimal{
    public Image image = new Image("carne.gif");
    Crane(int startX,int startY,int speed){
        this.startX=startX;
        this.startY=startY;
        this.speed=speed;
    }
}

