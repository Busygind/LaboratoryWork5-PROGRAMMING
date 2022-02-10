package com.lab5.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    // TODO сделать javadoc
    // TODO реализовать обработку неверных команд пользователя
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        XMLParser parser = new XMLParser();
        FileInputStream inputFile = new FileInputStream("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWork5\\lab5\\lab-client\\src\\main\\Dragons.xml");
        FileOutputStream outputFile = new FileOutputStream("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWork5\\lab5\\lab-client\\src\\main\\Dragons1.xml");
        CollectionOfDragons collection = new CollectionOfDragons(outputFile, inputFile);
        for (Dragon elem : parser.read(inputFile)) {
            collection.addDragon(elem);
        }
        CommandListener cl = new CommandListener(collection);
        cl.commandsReader();
    }
}
