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
            args = "{name age wingspan}",
            countOfArgs = Dragon.COUNT_OF_PRIMITIVE_ARGS,
            desc = "Добавить элемент в коллекцию",
            aliases = {})
    private void add(String dragonName, String age, String wingspan) {
        String name = dragonName.substring(0, 1).toUpperCase() + dragonName.substring(1); //Делаем имя с большой буквы
        Dragon dragon = Dragon.createInstance(name, inputCoordinates(), Integer.parseInt(age),
                                Integer.parseInt(wingspan), inputColor(), inputCharacter(),
                                DragonCave.createInstance(inputDepth(), inputNumOfTreasures()));
        if (dragon != null) {
            collection.addDragon(dragon);
        }
    }

    @Command(name = "remove_by_id",
            args = "{id}",
            countOfArgs = 1,
            desc = "Удалить элемент из коллекции по его ID",
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
        System.out.println("Дракон успешно сохранен в коллекцию");
    }

    @Command(name = "show",
            args = "",
            countOfArgs = 0,
            desc = "Показать всех драконов в коллекции",
            aliases = {})
    private void show() {
        System.out.println(collection.getDragons());
    }

    // TODO разобраться почему при вводе неверного х он потом нулл
    private void inputX(Coordinates coordinates) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите координату x (целое число): ");
        try {
            coordinates.setX(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Число имеет неверный формат");
            inputX(coordinates);
        } catch (IllegalArgumentException e) {
            System.out.println("Число не входит в допустимый диапазон");
            inputX(coordinates);
        }
    }

    private void inputY(Coordinates coordinates) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите Y(число с плавающей точкой): ");
        try {
            coordinates.setY(Float.parseFloat(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
            inputY(coordinates);
        }
    }

    private Coordinates inputCoordinates() {
        System.out.println("Введите координаты:");
        Coordinates newCoordinates = new Coordinates();
        inputX(newCoordinates);
        inputY(newCoordinates);
        return newCoordinates;
    }

    private Color inputColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите цвет дракона, доступные цвета: " + Arrays.toString(Color.values()) + ", для драконов с неопознанным цветом используйте null: ");
        String inputString = scanner.nextLine().toUpperCase();
        if (inputString.equals("NULL")) {
            return null;
        }
        try {
            return Color.valueOf(inputString);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода, такого цвета не существует");
            inputColor();
        }
        return null;
    }

    private DragonCharacter inputCharacter() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите настроение дракона, доступные настроения: " + Arrays.toString(DragonCharacter.values()) + ": ");
        try {
            return DragonCharacter.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода, такого настроения не существует");
            inputCharacter();
        }
        return null;
    }

    //TODO переделать по примеру координат
    private Double inputDepth() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите глубину пещеры (число с плавающей точкой): ");
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
            inputDepth();
        }
        return null;
    }

    private Integer inputNumOfTreasures() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество сокровищ (целое число, большее 0): ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода");
            inputNumOfTreasures();
        }
        return null;
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
