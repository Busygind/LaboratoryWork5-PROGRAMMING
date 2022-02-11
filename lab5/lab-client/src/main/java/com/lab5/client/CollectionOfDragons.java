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

    public void addDragon(Dragon dragon) {
        dragon.setId();
        idCounter++;
        dragons.add(dragon);
        System.out.println("Дракон успешно добавлен в коллекцию");
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

    //TODO решить что с этим делать
    //сортировка драконов по их возрасту
//    public List<Dragon> sort() {
//        List<Dragon> sortedDragons = (List<Dragon>) dragons;
//        Dragon buffer;
//        for (int i = 0; i < dragons.size() - 1; i++) {
//            if (sortedDragons.get(i).getAge() > sortedDragons.get(i + 1).getAge()) {
//                buffer = sortedDragons.get(i);
//                sortedDragons.set(i, sortedDragons.get(i + 1));
//                sortedDragons.set(i + 1, buffer);
//            }
//        }
//        return sortedDragons;
//    }

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
