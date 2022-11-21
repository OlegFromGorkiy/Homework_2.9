package ProductList;

import java.util.*;

public class Recipe {
    private String name;
    private double cost;
    Map<Product, Integer> products = new HashMap<>();

    public Recipe(String name, Collection<Product> products) {
        this.name = name;
        for (Product p : products) {
            this.products.put(p, 1);
        }
        cost = mathCost();
    }

    public Recipe(String name) {
        this.name = name;
    }

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product) && products.get(product) == quantity) {
            throw new RuntimeException("Продукт уже добавлен в рецепт!");
        } else {
            products.put(product, quantity);
            cost = mathCost();
        }
    }

    public void changeProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.remove(product);
            Product p = product;
            System.out.println("Введите новый вес продукта.");
            p.setWeight(inputDouble());
            System.out.println("Введите новую стоимость килограмма продукта.");
            p.setCost(inputDouble());
            this.addProduct(p, quantity);
        } else System.out.println("Продукта нет в рецепте!");
    }

    private double mathCost() {
        double d = 0;
        for (Map.Entry<Product, Integer> me : products.entrySet()) {
            d += me.getKey().getCost() * me.getKey().getWeight() * me.getValue();
        }
        return d;
    }

    private double inputDouble() {
        double d;
        Scanner console = new Scanner(System.in);
        while (true) {
            try {
                d = console.nextDouble();
                if (d <= 0) throw new RuntimeException("Значение может быть строго больше нуля!");
                console.close();
                return d;
            } catch (InputMismatchException e1) {
                System.out.println("Вы ввели не число! Попробуйте снова.");
            } catch (RuntimeException e2) {
                System.out.println(e2.getMessage() + " Попробуйте снова.");
            }
        }
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;

        Recipe recipe = (Recipe) o;

        return name.equals(recipe.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        String str1 = "Рецепт " + getName() + ":";
        if (products.isEmpty()) str1 += "\nПуст!";
        StringBuilder sb = new StringBuilder(str1);
        for (Map.Entry<Product, Integer> me : products.entrySet()) {
            sb.append("\n").append(me.getKey().toString());
            sb.append(" в количестве ").append(me.getValue()).append(" шт.");
        }
        sb.append("\n").append(String.format("Суммарная стоимость: %.2f рублей.", cost));
        return sb.toString();
    }
}
