package com.lab5.client;

import java.util.Date;
import java.util.HashSet;

public class CollectionOfDragons {
    private HashSet<Dragon> dragons;
    private Date creationDate;
//TODO проверить работает ли ссылка на общую коллекцию
    public CollectionOfDragons() {
        dragons = new HashSet<Dragon>();
        creationDate = new Date();
    }

    public void addDragon(Dragon dragon) {
        dragon.setId(dragons.size() + 1); //айди дракона зависит от текущего количества драконов
        dragons.add(dragon);
    }

    public void clear() {
        dragons.clear();
    }

    public HashSet<Dragon> getDragons() {
        return dragons;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void showInfo() {
        System.out.println("Collection type: " + dragons.getClass()
                        + " initialization date: " + creationDate
                        + " count of dragons: " + dragons.size());
    }
}
