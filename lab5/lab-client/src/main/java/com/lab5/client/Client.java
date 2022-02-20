package com.lab5.client;

import com.lab5.client.handlers.CommandListener;
import com.lab5.client.handlers.XMLReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 @author Dmitry Busygin
 */
public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    // TODO сделать javadoc
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
//        String fileName = args[0];
//        File starting = new File(System.getProperty("user.dir")); // Get current user directory
//        File file = new File(starting, fileName); // Initialize file from cmd
        File file = new File("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWork5\\lab5\\lab-client\\src\\main\\Dragons.xml");
        XMLReader reader = new XMLReader(); // Initialize parser
        CollectionOfDragons collection = new CollectionOfDragons(); // Initialize collection
        for (Dragon elem : reader.read(file)) {
            collection.addDragon(elem);
            if (elem.getCreationDate() == null) {
                elem.setCreationDate();
            }
        }
        collection.setOutFile(file);
        CommandListener cl = new CommandListener(collection);
        cl.commandsReader();
    }
}
