package restauracione;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private boolean isPlaced = false;
    private List<Dish> dishes;

    public Order(int id, List<Dish> dishes) {
        this.orderId = id;
        this.dishes = dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public double getTotalPrice() {
        // TODO: Zaimplementuj obliczanie całkowitej ceny zamówienia
        throw new UnsupportedOperationException("getTotalPrice() nie jest jeszcze zaimplementowana");
    }

    public void place() {
        isPlaced = true;
        System.out.println("Zamówienie #" + orderId + " przesłane do kuchni.");
    }

    public void cancel() {
        isPlaced = false;
        System.out.println("Zamówienie #" + orderId + " zostało anulowane.");
    }

    @Override
    public String toString() {
        return "Zamówienie #" + orderId + " (" + dishes.size() + " dań)";
    }
}