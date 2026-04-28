package restauracione;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String name;
    private List<IngredientInDish> ingredients;

    public Dish(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addIngredient(IngredientInDish ingredientInDish) {
        ingredients.add(ingredientInDish);
    }

    public List<IngredientInDish> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public double getPrice() {
        // TODO: Zaimplementuj obliczanie ceny dania (suma cen wszystkich składników)
        throw new UnsupportedOperationException("getPrice() nie jest jeszcze zaimplementowana");
    }

    @Override
    public String toString() {
        return name;
    }
}
