package com.lab5.client;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashSet;

/**
 * Класс коллекции, содержащий текущую коллекцию <b>dragons</b>,
 * отвечает за генерацию ID для новых элементов и за все действия,
 * связанные с коллекцией.
 */
public class CollectionOfDragons {

    private static int idCounter = 1;
    private File outFile;
    private HashSet<Dragon> dragons;
    private Date creationDate;

    public CollectionOfDragons() {
        dragons = new HashSet<>();
        creationDate = new Date();
    }

    public CollectionOfDragons(File outFile) {
        this.outFile = outFile;
        dragons = new HashSet<>();
        creationDate = new Date();
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
    public File getOutFile() {
        return outFile;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    /**
     * Метод, добавляющий полученного дракона в коллекцию
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
     * Метод, удаляющий дракона из коллекции по полученному ID,
     * если таковой существует
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

    /**
     * Метод, выводящий пользователю информацию о коллекции
     */
    public void showInfo() {
        System.out.println("Collection type: " + dragons.getClass()
                + " initialization date: " + creationDate
                + " count of dragons: " + dragons.size());
    }
}
