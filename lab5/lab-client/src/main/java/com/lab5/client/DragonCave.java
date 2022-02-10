package com.lab5.client;

public class DragonCave {
    private double depth;
    private int numberOfTreasures; //Значение поля должно быть больше 0
    //TODO поправить конструктор
    public DragonCave() {

    }

    public DragonCave(double depth, int numberOfTreasures) {
        if (numberOfTreasures <= 0) {
            throw new IllegalArgumentException("Некорректное количество существ в пещере, попробуйте снова");
        }
        this.depth = depth;
        this.numberOfTreasures = numberOfTreasures;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public void setNumberOfTreasures(int numberOfTreasures) {
        if (numberOfTreasures <= 0) {
            throw new IllegalArgumentException("Некорректное количество существ в пещере, попробуйте снова");
        }
        this.numberOfTreasures = numberOfTreasures;
    }

    public double getDepth() {
        return depth;
    }

    public int getNumberOfTreasures() {
        return numberOfTreasures;
    }

    @Override
    public String toString() {
        return "depth: " + depth + " numbers of treasures: " + numberOfTreasures;
    }
}
