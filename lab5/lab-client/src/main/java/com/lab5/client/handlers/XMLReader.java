package com.lab5.client.handlers;

import com.lab5.client.CollectionOfDragons;
import com.lab5.client.Dragon;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class XMLReader {

    public HashSet<Dragon> read(File file) throws IOException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("dragon", Dragon.class);
        xStream.alias("set", CollectionOfDragons.class);
        xStream.addImplicitCollection(CollectionOfDragons.class, "dragons");
        StringBuilder xmlText = new StringBuilder();
        FileReader reader = new FileReader(file);
        Scanner sc = new Scanner(reader);
        while (sc.hasNextLine()) {
            xmlText.append(sc.nextLine());
        }
        CollectionOfDragons dragons = (CollectionOfDragons) xStream.fromXML(xmlText.toString());
        return dragons.getDragons();
    }

}
