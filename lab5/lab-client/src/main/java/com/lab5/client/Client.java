package com.lab5.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    //TODO реализовать toString у всех классов, которые увидит пользователь
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        XMLParser parser = new XMLParser();
        CollectionOfDragons collection = new CollectionOfDragons();
        FileInputStream file = new FileInputStream("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWork5\\lab5\\lab-client\\src\\main\\Dragons.xml");
        //TODO не забыть удалить эту хуйню
        for (Dragon elem : parser.read(file)) {
            collection.addDragon(elem);
        }
        CommandListener cl = new CommandListener(collection);
        cl.commandsReader();
    }
}
