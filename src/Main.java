import ProductList.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
    }

    static void task1() {
        System.out.println("Задание 1");
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.put("Unknown", 1230123456789L);
        phoneBook.remove("Unknown");
        for (int i = 0; i < 20; i++) {
            phoneBook.put(generateName(), generateNumber());
        }
        phoneBook.printInfo();
        System.out.println("\n");
    }

    static void task2() {
        System.out.println("Задание 2");
        List<Product> products = new LinkedList<>();
        products.add(new Product("Молоко", 100, 0.5));
        products.add(new Product("Яйца", 150, 0.9));
        products.add(new Product("Масло сливочное", 80, 0.5));
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe("Recipe", products);
        Recipe recipe2 = new Recipe("New recipe", products);

        try {
            addRecipe(recipes, recipe1);
            addRecipe(recipes, recipe2);
            recipe1.addProduct(products.get(1), 3);
            recipe1.addProduct(products.get(0), 1);
        } catch (RuntimeException e) {
            System.out.println("Обработана ошибка: " + e.getMessage());
        }
        for (Recipe r : recipes) {
            System.out.println(r);
        }
        System.out.println("\n");
    }

    static void task3() {
        System.out.println("Задание 3");
        Map<String, Integer> map = new HashMap<>();
        addMethod(map, "aaa", 4);
        addMethod(map, "bbb", 2);
        addMethod(map, "ccc", 3);
        try {
            addMethod(map, "aaa", 1);
            addMethod(map, "aaa", 1);
        } catch (RuntimeException e) {
            System.out.println("Обработана ошибка: " + e.getMessage());
        }
        for (Map.Entry<String, Integer> me : map.entrySet()) {
            System.out.println(me.getKey() + " - " + me.getValue());
        }
        System.out.println("\n");
    }

    static void task4() {
        System.out.println("Задание 4");
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(generateName(), generateList());
        }

        for (String s : map.keySet()) {
            System.out.println(s + " -> " + Arrays.toString(map.get(s).toArray()));
        }
        System.out.println("*****");
        Map<String, Integer> newMap = new HashMap<>();
        for (String s : map.keySet()) {
            int summ = 0;
            for (Integer i : map.get(s)) {
                summ += i;
            }
            newMap.put(s, summ);
        }
        for (String s : newMap.keySet()) {
            System.out.println(s + " -> " + newMap.get(s));
        }
        System.out.println("\n");
    }

    static void task5() {
        System.out.println("Задание 5");
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put(generateName(), i);
        }
        for (Map.Entry<String, Integer> me : map.entrySet()) {
            System.out.println(me.getKey() + ":" + me.getValue());
        }
        System.out.println("\n");
    }

    static void addMethod(Map<String,Integer> map, String key, Integer value) {
        if (map.containsKey(key) && map.get(key).equals(value)) {
            throw new RuntimeException("This mam contains this pair");
        } else map.put(key, value);
    }

    static String generateName() {
        Random rnd = new Random();
        char[] ch = new char[5];
        for (int i = 0; i < 5; i++) {
            ch[i] = (char) (97 + rnd.nextInt(26));
        }
        return new String(ch);
    }

    static String generateNumber() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7 + rnd.nextInt(7); i++) {
            sb.append(rnd.nextInt(10));
        }
        return sb.toString();
    }

    static List<Integer> generateList() {
        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(rnd.nextInt(1001));
        }
        return list;
    }

    public static void addRecipe(Set<Recipe> recipes, Recipe recipe) {
        if (!recipes.add(recipe)) throw new RuntimeException("Такой рецепт уже в коллекции");
    }
}