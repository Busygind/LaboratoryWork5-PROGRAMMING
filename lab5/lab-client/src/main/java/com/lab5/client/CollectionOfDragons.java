package com.lab5.client;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashSet;

public class CollectionOfDragons {

    private static int idCounter = 1;
    private FileOutputStream outFile;
    private HashSet<Dragon> dragons;
    private Date creationDate;

    public CollectionOfDragons(FileOutputStream outFile) {
        this.outFile = outFile;
        dragons = new HashSet<>();
        creationDate = new Date();
    }

    /**
     * @param dragon дракон, которого нужно добавить в коллекцию
     */
    public void addDragon(Dragon dragon) {
        dragon.setId();
        idCounter++;
        dragons.add(dragon);
        System.out.println("Дракон успешно добавлен в коллекцию");
    }

    public void clear() {
        dragons.clear();
    }

    /**
     * @param id id дракона, которого нужно удалить
     */
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

    /**
     * @return HashSet драконов, находящихся в коллекции
     */
    public HashSet<Dragon> getDragons() {
        return dragons;
    }

    /**
     * @return дата создания коллекции
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @return файл, в который производится запись готовой коллекции
     */
    public FileOutputStream getFile() {
        return outFile;
    }
}
