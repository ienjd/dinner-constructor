package ru.practicum.dinner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    public static final HashMap<String, ArrayList<String>> MENU = new HashMap<>();
    Random random = new Random();

    // Метод проверяет имя блюда на наличие в меню. Если блюдо есть, добавить его второй раз не получится.
    boolean checkDishOnAbsent(String nameOfDish) {
        boolean dishIsAbsent = true;
        for (String type : MENU.keySet()) {
            for (String dish : MENU.get(type)) {
                if (nameOfDish.replace("\\s", "").equalsIgnoreCase(dish)) {
                    dishIsAbsent = false;
                    System.out.println("Блюдо уже есть в меню.");
                }
            }
        }
        return dishIsAbsent;
    }

    // Метод проверяет наличие типа блюда в меню. С помощью проверки данным методом реализована логика в putDishToMenu()
    boolean checkTypeOnExist(String type) {
        boolean typeIsExist = MENU.containsKey(type) ? true : false;
        return typeIsExist;
    }

    // Простой метод для добавления блюда в соответствующий список.
    void addingDish(String typeOfDish, String nameOfDish, ArrayList<String> dishesNames) {
        dishesNames.add(nameOfDish);
        MENU.put(typeOfDish, dishesNames);
    }

    /*Основной метод класса, добавляющий блюдо в меню в зависимости от его типа.
    С удивлением обнаружил, что новый switch через лямбда-выражение позволяет обрабатывать булевы переменные.
    Интуитивно раньше пытался подставлять их в switch, рад, что такая возможность наконец-то появилась,
    хоть и понимаю, что на проектах с древним легаси это не будет работать. */
    void putDishToMenu(String typeOfDish, String nameOfDish) {
        if (typeOfDish.equals("Первое") || typeOfDish.equals("Суп")) {
            typeOfDish = "Суп";
        }
        boolean typeIsInMenu = checkTypeOnExist(typeOfDish) && checkDishOnAbsent(nameOfDish);
        switch (typeIsInMenu) {
            case true -> addingDish(typeOfDish, nameOfDish, MENU.get(typeOfDish));
            case false -> addingDish(typeOfDish, nameOfDish, new ArrayList<>());
        }
    }
    // Метод для извлечения случайно позиции типа блюда
    String returnDishFromUserType(String type){
        return (MENU.get(type)).get(random.nextInt(MENU.get(type).size()));
    }
    // Метод для создания комбинаций блюд с использованием returnDishFromUserType
    void createCombinations(int quantity, ArrayList<String> listOfTypes) {
        int counter = 0;
        String result = "";
        while (counter < quantity) {
            System.out.println("Комбинация № " + (counter + 1));
            for (String type : listOfTypes) {
                result = checkTypeOnExist(type) ? returnDishFromUserType(type) : "Такого типа блюд нет";
                System.out.println((counter + 1 ) + ". " + result);
            }
            counter++;
        }
    }
}


