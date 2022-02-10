package com.lab5.client;

public class Coordinates {
    static final int MAX_X_VALUE = 603;
    private Integer x; //Максимальное значение поля: 603, Поле не может быть null
    private float y;
    //TODO поправить конструктор
    private Coordinates(Integer x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinates createInstance(Integer x, float y) {
        if (x <= MAX_X_VALUE) {
            return new Coordinates(x, y);
        }
        System.out.println("Координата x некорректна, не удалось инициализировать координаты");
        return null;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        if (x > MAX_X_VALUE) {
            this.x = null;
            System.out.println("Некорректное значение координаты дракона, введите данные о драконе заново");
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
