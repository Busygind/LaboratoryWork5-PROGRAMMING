package com.lab5.client.handlers;

import com.lab5.client.CollectionOfDragons;
import com.lab5.client.Dragon;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLWriter {

    public void write(File file, CollectionOfDragons dragons) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("dragon", Dragon.class);
        xStream.alias("set", CollectionOfDragons.class);
        xStream.addImplicitCollection(CollectionOfDragons.class, "dragons");
        String xmlText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" + xStream.toXML(dragons.getDragons());
        FileWriter writer = new FileWriter(file);
        writer.write(xmlText);
        writer.flush();
        writer.close();
    }

}
