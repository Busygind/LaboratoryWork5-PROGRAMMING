package com.lab5.client;

import java.util.Comparator;
import java.util.Date;

public class Dragon implements Comparable<Dragon> {

    public static final int COUNT_OF_PRIMITIVE_ARGS = 3;
    private static long idCounter = 1;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private int wingspan; //Значение поля должно быть больше 0
    private Color color; //Поле может быть null
    private DragonCharacter character; //Поле не может быть null
    private DragonCave cave; //Поле не может быть null


    private Dragon(String name, Coordinates coordinates, int age, int wingspan, Color color, DragonCharacter character, DragonCave cave) {
        try {
            this.name = name;
            this.coordinates = coordinates;
            this.age = age;
            this.wingspan = wingspan;
            this.color = color;
            this.character = character;
            this.cave = cave;
            this.creationDate = new Date();
            setId();
        } catch (IllegalArgumentException ex) {
            System.out.println("Некорректные параметры дракона: " + ex.getMessage());
        }
    }

    public Dragon() {
        this.creationDate = new Date();
    }

    public static Dragon createInstance(String name, Coordinates coordinates, int age, int wingspan, Color color, DragonCharacter character, DragonCave cave) {
        if (name != null && !name.isEmpty() && coordinates != null && age > 0 && wingspan > 0 && character != null && cave != null) {
            return new Dragon(name, coordinates, age, wingspan, color, character, cave);
        }
        System.out.println("Создание дракона не удалось");
        return null;
    }

    public void setId() {
        this.id = idCounter++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Не переданы координаты или они некорректны, попробуйте снова");
        }
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Некорректный возраст дракона, попробуйте снова");
        }
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setWingspan(int wingspan) {
        if (wingspan <= 0) {
            throw new IllegalArgumentException("Некорректный размах крыльев дракона, попробуйте снова");
        }
        this.wingspan = wingspan;
    }

    public int getWingspan() {
        return this.wingspan;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setCharacter(DragonCharacter character) {
        if (character == null) {
            throw new IllegalArgumentException("Не передан характер дракона, попробуйте снова");
        }
        this.character = character;
    }

    public DragonCharacter getCharacter() {
        return this.character;
    }

    public void setCave(DragonCave cave) {
        if (cave == null) {
            throw new IllegalArgumentException("Не переданы данные о пещере или они некорректны, попробуйте снова");
        }
        this.cave = cave;
    }

    public DragonCave getCave() {
        return this.cave;
    }

    public long getId() {
        return id;
    }

    public void setCreationDate() {
        this.creationDate = new Date();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public int compareTo(Dragon o) {
        return Comparator.comparing(Dragon::getAge).thenComparing(Dragon::getName).thenComparing(Dragon::getWingspan).compare(this, o);
    }

    @Override
    public String toString() {
        return "\nDragon #" + id + "\nname: " + name
                + "\ncreationDate: " + getCreationDate()
                + "\nage: " + age + "\nwingspan: " + wingspan
                + "\ncoordinates: " + coordinates.toString() + "\ncolor: " + color
                + "\ncharacter: " + character + "\ncave: " + cave.toString() + "\n========================";
    }
}
