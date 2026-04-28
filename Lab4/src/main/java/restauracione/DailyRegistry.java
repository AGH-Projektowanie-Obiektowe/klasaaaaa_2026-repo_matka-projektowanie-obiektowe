package restauracione;

import java.util.ArrayList;
import java.util.List;

public class DailyRegistry {
    private List<Order> completedOrders;

    public DailyRegistry() {
        this.completedOrders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        completedOrders.add(order);
    }

    public List<Order> getCompletedOrders() {
        return new ArrayList<>(completedOrders);
    }

    public double getDailyRevenue() {
        // TODO: Zaimplementuj obliczanie całkowitego przychodu dnia
        throw new UnsupportedOperationException("getDailyRevenue() nie jest jeszcze zaimplementowana");
    }

    public int getTotalOrdersCount() {
        return completedOrders.size();
    }

    @Override
    public String toString() {
        return "Rejestr Dzienny: " + completedOrders.size() + " zamówień";
    }
}
