package com.lab5.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class XMLParser {

//    public void parseFile(File file, CollectionOfDragons collection) {
//        try {
//            // Создается построитель документа
//            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            // Создается дерево DOM документа из файла
//            Document document = documentBuilder.parse(file);
//
//            // Получаем корневой элемент
//            Node root = document.getDocumentElement();
//
//            // Просматриваем все подэлементы корневого - т.е. книги
//            NodeList dragons = root.getChildNodes();
//            for (int i = 0; i < dragons.getLength(); i++) {
//                Node dragon = dragons.item(i);
//                // Если нода не текст, то это книга - заходим внутрь
//                if (dragon.getNodeType() != Node.TEXT_NODE) {
//                    NodeList dragonProperties = dragon.getChildNodes();
//                    for (int j = 0; j < dragonProperties.getLength(); j++) {
//                        Node dragonProperty = dragonProperties.item(j);
//                        // Если нода не текст, то это один из параметров книги - печатаем
//                        if (dragonProperty.getNodeType() != Node.TEXT_NODE) {
//                            collection.addDragon(initDragon(dragonProperty));
//                        }
//                    }
////                    System.out.println("========================");
//                }
//            }
//        } catch (ParserConfigurationException | SAXException | IOException ex) {
//            ex.printStackTrace(System.out);
//        }
//    }
//
//    public Dragon initDragon(Node node) {
//        Dragon nextDragon = new Dragon();
//        switch (node.getNodeName()) {
//            case "name":
//                nextDragon.setName(node.getChildNodes().item(0).getTextContent());
////                System.out.println(nextDragon.getName());
//                break;
//            case "age":
//                nextDragon.setAge(Integer.parseInt(node.getChildNodes().item(0).getTextContent()));
////                System.out.println(nextDragon.getAge());
//                break;
//            case "wingspan":
//                nextDragon.setWingspan(Integer.parseInt(node.getChildNodes().item(0).getTextContent()));
////                System.out.println(nextDragon.getWingspan());
//                break;
//            case "color":
//                nextDragon.setColor(Color.valueOf(node.getChildNodes().item(0).getTextContent()));
////                System.out.println(nextDragon.getColor());
//                break;
//            case "coordinates":
//                nextDragon.setCoordinates(initCoordinates(node));
////                System.out.println(nextDragon.getCoordinates());
//                break;
//            case "character":
//                nextDragon.setCharacter(DragonCharacter.valueOf(node.getChildNodes().item(0).getTextContent()));
////                System.out.println(nextDragon.getCharacter());
//                break;
//            case "cave":
//                nextDragon.setCave(initCave(node));
////                System.out.println(nextDragon.getCave());
//                break;
//            default:
//                System.out.println("HUY");
//        }
//        return nextDragon;
//    }
//
//    private DragonCave initCave(Node node) {
//        DragonCave cave = new DragonCave();
//        NodeList subProperties = node.getChildNodes();
//        for (int i = 0; i < subProperties.getLength(); i++) {
//            Node subProperty = subProperties.item(i);
//            if (subProperty.getNodeType() != Node.TEXT_NODE) {
//                switch (subProperty.getNodeName()) {
//                    case "depth":
//                        cave.setDepth(Double.parseDouble(subProperty.getChildNodes().item(0).getTextContent()));
//                        break;
//                    case "numbers-of-treasures":
//                        cave.setNumberOfTreasures(Integer.parseInt(subProperty.getChildNodes().item(0).getTextContent()));
//                        break;
//                    default:
//                        System.out.println("В файле некорректно указаны данные об одной из пещер, попробуйте еще раз");
//                }
//            }
//        }
//        return cave;
//    }
//
//    private Coordinates initCoordinates(Node node) {
//        Coordinates coordinates = new Coordinates();
//        NodeList subProperties = node.getChildNodes();
//        for (int i = 0; i < subProperties.getLength(); i++) {
//            Node subProperty = subProperties.item(i);
//            if (subProperty.getNodeType() != Node.TEXT_NODE) {
//                switch (subProperty.getNodeName()) {
//                    case "x":
//                        coordinates.setX(Integer.parseInt(subProperty.getChildNodes().item(0).getTextContent()));
//                        break;
//                    case "y":
//                        coordinates.setY(Float.parseFloat(subProperty.getChildNodes().item(0).getTextContent()));
//                        break;
//                    default:
//                        System.out.println("Ошибка данных в файле, попробуйте еще раз");
//                }
//            }
//        }
//        return coordinates;
//    }
//TODO Почистить парсер от мусора
    public HashSet<Dragon> read(FileInputStream file) throws IOException {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("dragon", Dragon.class);
        xStream.alias("dragons", CollectionOfDragons.class);
        xStream.addImplicitCollection(CollectionOfDragons.class, "dragons");
        StreamToStringConverter converter = new StreamToStringConverter();
        String xmlText = converter.inputStreamToString(file);
        CollectionOfDragons dragons = (CollectionOfDragons) xStream.fromXML(xmlText);
        return dragons.getDragons();
    }
    //TODO отдебажить парсинг и проверить что на выходе нормальный сет драконов
}
