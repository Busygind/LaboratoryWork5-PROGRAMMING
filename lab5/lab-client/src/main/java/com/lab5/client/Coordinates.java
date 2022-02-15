package com.lab5.client;

public class Coordinates {
    static final int MAX_X_VALUE = 603;
    private Integer x; //Максимальное значение поля: 603, Поле не может быть null
    private float y;

    public Coordinates(Integer x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {

    }

    //TODO создание экземпляра координат, если понадобится снова приватный конструктор
//    public static Coordinates createInstance(Integer x, float y) {
//        if (x <= MAX_X_VALUE) {
//            return new Coordinates(x, y);
//        }
//        System.out.println("Координата x некорректна, не удалось инициализировать координаты");
//        return null;
//    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        if (x > MAX_X_VALUE) {
            throw new IllegalArgumentException("Некорретное значение координаты х");
        } else {
            this.x = x;
        }
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}
