package ru.practicum.dinner;
import java.util.ArrayList;
import java.util.HashMap;

public class DinnerConstructor {

    public static final HashMap<String, ArrayList<String>> MENU = new HashMap<>();
    ArrayList<String> dishesNames;

    // Метод проверяет имя блюда на наличие в меню. Если блюдо есть, добавить его второй раз не получится.
    boolean dishIsAbsent(String nameOfDish) {
        boolean dishIsAbsent = true;
        for (String type : MENU.keySet()) {
            for (String dish : MENU.get(type)) {
                if (nameOfDish.equals(dish)) {
                    dishIsAbsent = false;
                    System.out.println("Блюдо уже есть в меню.");
                }
            }
        }
        return dishIsAbsent;
    }

    // Метод проверяет наличие типа блюда в меню. С помощью проверки данным методом реализована логика в putDishToMenu()
    boolean checkType(String type) {
        boolean typeIsExist = false;//
        if (MENU.containsKey(type)) {
            typeIsExist = true;
        }
        return typeIsExist;
    }

    // Простой метод для добавления блюда в соответствующий список.
    void addingDish(String typeOfDish, String nameOfDish, ArrayList<String> dishesNames) {
        dishesNames.add(nameOfDish);
        MENU.put(typeOfDish, dishesNames);
    }

    //Основной метод класса, добавляющий блюдо в меню в зависимости от его типа
    void putDishToMenu(String typeOfDish, String nameOfDish) {
        if (typeOfDish.equals("Первое") || typeOfDish.equals("Суп")) {
            typeOfDish = "Суп";
        }
        if (checkType(typeOfDish)) {
            dishesNames = MENU.get(typeOfDish);
            if (dishIsAbsent(nameOfDish)) {
                addingDish(typeOfDish, nameOfDish, dishesNames);
                System.out.println(MENU);
            }
        } else {
            if (dishIsAbsent(nameOfDish)) {
                ArrayList<String> dishesNames = new ArrayList<>();
                addingDish(typeOfDish, nameOfDish, dishesNames);
                System.out.println(MENU);
            }
        }
    }
}

