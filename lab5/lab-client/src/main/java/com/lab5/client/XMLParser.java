package com.lab5.client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class XMLParser {

    public HashSet<Dragon> read(FileInputStream file) throws IOException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("dragon", Dragon.class);
        xStream.alias("set", CollectionOfDragons.class);
        xStream.addImplicitCollection(CollectionOfDragons.class, "dragons");
        StreamToStringConverter converter = new StreamToStringConverter();
        String xmlText = converter.inputStreamToString(file);
        CollectionOfDragons dragons = (CollectionOfDragons) xStream.fromXML(xmlText);
        return dragons.getDragons();
    }

    public void write(FileOutputStream file, CollectionOfDragons dragons) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("dragon", Dragon.class);
        xStream.alias("set", CollectionOfDragons.class);
        xStream.addImplicitCollection(CollectionOfDragons.class, "dragons");
        String xmlText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" + xStream.toXML(dragons.getDragons());
        file.write(xmlText.getBytes(StandardCharsets.UTF_8));
    }
}
