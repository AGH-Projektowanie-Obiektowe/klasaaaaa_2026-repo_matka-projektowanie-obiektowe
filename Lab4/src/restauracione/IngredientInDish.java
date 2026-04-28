package restauracione;

public class IngredientInDish {
    private Ingredient ingredient;
    private double weightInUnits;

    public IngredientInDish(Ingredient ingredient, double weightInUnits) {
        this.ingredient = ingredient;
        this.weightInUnits = weightInUnits;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getWeight() {
        return weightInUnits;
    }
}