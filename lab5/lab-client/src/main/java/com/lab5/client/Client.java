package com.lab5.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    /**
     * @throws IOException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        //TODO реализация проверки количества аргументов
//        if (args.length == 0) {
//            System.out.println("Неверное количество аргументов, попробуйте еще раз. Необходимый элемент: файл с расширением .xml");
//            System.exit(0);
//        }
        XMLParser parser = new XMLParser();
        File file = new File("C:\\Users\\Дмитрий\\JavaProjects\\LaboratoryWork5\\lab5\\lab-client\\src\\main\\Dragons.xml");

        CollectionOfDragons collection = new CollectionOfDragons();
        for (Dragon elem : parser.read(new FileInputStream(file))) {
            collection.addDragon(elem);
            if (elem.getCreationDate() == null) {
                elem.setCreationDate();
            }
        }
        collection.setOutFile(new FileOutputStream(file));
        CommandListener cl = new CommandListener(collection);
        cl.commandsReader();
    }
}
