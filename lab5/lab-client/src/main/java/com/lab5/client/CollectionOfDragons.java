package com.lab5.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashSet;

public class CollectionOfDragons {
    private static int idCounter = 1;
    private FileInputStream inFile;
    private FileOutputStream outFile;
    private HashSet<Dragon> dragons;
    private Date creationDate;

    public CollectionOfDragons(FileOutputStream outFile, FileInputStream inFile) {
        this.outFile = outFile;
        this.inFile = inFile;
        dragons = new HashSet<>();
        creationDate = new Date();
    }

    public void addDragon(Dragon dragon) {
        dragon.setId(idCounter); //айди дракона зависит от текущего количества драконов
        idCounter++;
        dragons.add(dragon);
    }

    public void clear() {
        dragons.clear();
    }

    public void removeById(long id) {
        for (Dragon dragon : dragons) {
            if (dragon.getId() == id) {
                dragons.remove(dragon);
                System.out.println("Dragon succesfully removed");
            }
        }
    }

    public void showInfo() {
        System.out.println("Collection type: " + dragons.getClass()
                + " initialization date: " + creationDate
                + " count of dragons: " + dragons.size());
    }

    public HashSet<Dragon> getDragons() {
        return dragons;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public FileOutputStream getFile() {
        return outFile;
    }
}
