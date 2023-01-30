package com.example.laba4;

import javafx.scene.image.Image;

public class Tuna extends WaterfowlAnimal{
    public Image image = new Image("tuna.gif");
    Tuna(int startX,int startY,int speed){
        this.startX=startX;
        this.startY=startY;
        this.speed=speed;
    }
}
