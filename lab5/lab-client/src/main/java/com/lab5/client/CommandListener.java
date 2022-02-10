package com.lab5.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandListener {
    private static Map<String, Method> commands = new HashMap<>();
    private CollectionOfDragons collection;

    public CommandListener(CollectionOfDragons collection) {
        this.collection = collection;
        for (Method method : CommandListener.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);
                commands.put(command.name(), method);
            }
        }
    }

    @Command(name = "help",
            args = "",
            countOfArgs = 0,
            desc = "Доступные пользователю команды",
            aliases = {})
    private void help() {
        StringBuilder sb = new StringBuilder("Список команд: \n");
        for (Method m : this.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Command.class)) {
                Command com = m.getAnnotation(Command.class);
                sb.append(com.name()).append(" ")
                        .append(com.args()).append(" - ")
                        .append(com.desc()).append("\n");
            }
        }
        System.out.println(sb);
    }

    @Command(name = "info",
            args = "",
            countOfArgs = 0,
            desc = "Вывести информацию о коллекции",
            aliases = {})
    private void info() {
        System.out.println("Information about collection: ");
        collection.showInfo();
    }

    @Command(name = "add",
            args = "name, coordX, coordY, age, wingspan, color, character, caveDepth, caveNumberOfTreasures",
            countOfArgs = Dragon.COUNT_OF_ARGS,
            desc = "Вывести информацию о коллекции",
            aliases = {})
    private void add(String dragonName, String x, String y, String age, String wingspan,
                     String color, String character, String depth, String numOfTreasures) {
        String name = dragonName.substring(0, 1).toUpperCase() + dragonName.substring(1); //Делаем имя с большой буквы
        Dragon dragon = Dragon.createInstance(name, Coordinates.createInstance(Integer.parseInt(x), Float.parseFloat(y)), Integer.parseInt(age),
                                Integer.parseInt(wingspan), Color.valueOf(color.toUpperCase()), DragonCharacter.valueOf(character.toUpperCase()),
                                DragonCave.createInstance(Double.parseDouble(depth), Integer.parseInt(numOfTreasures)));
        if (dragon != null) {
            collection.addDragon(dragon);
        }
    }

    @Command(name = "remove_by_id",
            args = "id",
            countOfArgs = 1,
            desc = "Вывести информацию о коллекции",
            aliases = {})
    private void removeById(String id) {
        collection.removeById(Long.parseLong(id));
    }

    @Command(name = "exit",
            args = "",
            countOfArgs = 0,
            desc = "Выход из программы без сохранения",
            aliases = {})
    private void exit() {
        System.exit(0);
    }

    @Command(name = "clear",
            args = "",
            countOfArgs = 0,
            desc = "Очищение коллекции",
            aliases = {})
    private void clear() {
        collection.clear();
        if (collection.getDragons().isEmpty()) {
            System.out.println("Collection successful cleared");
        } else {
            System.out.println("Something went wrong, try again.");
        }
    }

    @Command(name = "save",
            args = "",
            countOfArgs = 0,
            desc = "Сохранение коллекции в файл",
            aliases = {})
    private void save() throws IOException {
        XMLParser writer = new XMLParser();
        writer.write(collection.getFile(), collection);
    }

    @Command(name = "show",
            args = "",
            countOfArgs = 0,
            desc = "Показать всех драконов в коллекции",
            aliases = {})
    private void show() {
        System.out.println(collection.getDragons());
    }

    public void commandsReader() throws InvocationTargetException, IllegalAccessException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine().toLowerCase();
            String[] inputLine = line.split(" ");
            String commandName = inputLine[0];
            String[] commandArgs = Arrays.copyOfRange(inputLine, 1, inputLine.length);
            Method method = commands.get(commandName);

            Command command = method.getAnnotation(Command.class);
            if (commandArgs.length != command.countOfArgs()) {
                System.out.println("Неверное количество аргументов. Необходимо: " + command.countOfArgs());
            } else {
                method.invoke(this, commandArgs);
            }
        }

    }
}
