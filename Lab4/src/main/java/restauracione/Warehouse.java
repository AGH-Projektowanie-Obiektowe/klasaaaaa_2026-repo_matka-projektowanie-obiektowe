package restauracione;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static final Map<Ingredient, Integer> ingredientStock = new HashMap<>();
    private static int containerCount = 0;
    private static int sauceCount = 0;
    static {
        ingredientStock.put(Ingredient.FLOUR, 50);
        ingredientStock.put(Ingredient.CHEESE, 30);
        ingredientStock.put(Ingredient.TOMATO_SAUCE, 40);
        ingredientStock.put(Ingredient.MUSHROOMS, 25);
        ingredientStock.put(Ingredient.ONION, 35);
        ingredientStock.put(Ingredient.SALAMI, 20);
        ingredientStock.put(Ingredient.OLIVES, 15);
        containerCount = 100;
        sauceCount = 50;
    }

    public static int getIngredientQuantity(Ingredient ingredient) {
        return ingredientStock.getOrDefault(ingredient, 0);
    }

    public static void addIngredient(Ingredient ingredient, int quantity) {
        ingredientStock.put(ingredient, 
            ingredientStock.getOrDefault(ingredient, 0) + quantity);
    }

    public static boolean removeIngredient(Ingredient ingredient, int quantity) {
        int currentQuantity = ingredientStock.getOrDefault(ingredient, 0);
        if (currentQuantity >= quantity) {
            ingredientStock.put(ingredient, currentQuantity - quantity);
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasEnoughIngredient(Ingredient ingredient, int quantity) {
        return ingredientStock.getOrDefault(ingredient, 0) >= quantity;
    }

    public static int getContainerCount() {
        return containerCount;
    }

    public static void addContainers(int quantity) {
        containerCount += quantity;
    }

    public static boolean removeContainer() {
        if (containerCount > 0) {
            containerCount--;
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasContainers() {
        return containerCount > 0;
    }

    public static int getSauceCount() {
        return sauceCount;
    }

    public static void addSauce(int quantity) {
        sauceCount += quantity;
    }

    public static boolean removeSauce(int quantity) {
        if (sauceCount >= quantity) {
            sauceCount -= quantity;
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasEnoughSauce(int quantity) {
        return sauceCount >= quantity;
    }

    public static void printWarehouseStatus() {
    }

    //to tak o żeby było
    public static void reset() {
        ingredientStock.clear();
        ingredientStock.put(Ingredient.FLOUR, 50);
        ingredientStock.put(Ingredient.CHEESE, 30);
        ingredientStock.put(Ingredient.TOMATO_SAUCE, 40);
        ingredientStock.put(Ingredient.MUSHROOMS, 25);
        ingredientStock.put(Ingredient.ONION, 35);
        ingredientStock.put(Ingredient.SALAMI, 20);
        ingredientStock.put(Ingredient.OLIVES, 15);
        containerCount = 100;
        sauceCount = 50;
    }
}
