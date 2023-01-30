package com.example.laba4;

import javafx.scene.image.Image;

public abstract class Animal {
    // начальные координаты
    public float startX = 0;
    public float startY = 0;
    // конечные координаты
    public float finalX = 0;
    public float finalY = 0;
    // текущие координаты
    public double currentX = 0.0;
    public double currentY = 0.0;
    // скорость движения
    public float speed = 0;
    // таймер движения
    public float timeValue = 0;

    public void doMovement(float time) {
        // увеличение счетчика времени
        this.timeValue += time;
        // расчет максимального расстояния движения
        // для линейного движения - гипотенуза прямоугольного треугольника
        float s = (float) Math.sqrt(Math.pow(this.finalX - this.startX, 2) +
                Math.pow(this.finalY - this.startY, 2));
        // расчет максимального времени движения
        float tmax = s / this.speed;
        // если движение закончилось
        if (this.timeValue >= tmax) {
            this.currentX = this.finalX;
            this.currentY = this.finalY;
            this.startX = this.finalX;
            this.startY = this.finalY;
            this.timeValue = 0;
            return;
        }
        // расчет текущих координат
        this.currentX = this.startX + this.timeValue / tmax * (this.finalX - this.startX);
        this.currentY = this.startY + this.timeValue / tmax * (this.finalY - this.startY);
    }
}

