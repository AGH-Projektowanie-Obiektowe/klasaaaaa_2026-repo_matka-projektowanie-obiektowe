package reservations;

import trip.SpaceTrip;

public class ScienceSpaceReservation {
    private final SpaceTrip trip;
    private final boolean grantApproved;
    private double price;

    public ScienceSpaceReservation(SpaceTrip trip, boolean grantApproved) {
        this.trip = trip;
        this.grantApproved = grantApproved;
        this.price = grantApproved ? 25000.0 : 50000.0;
    }

    public void confirmReservation() {
        System.out.println("Zarezerwowano czas badawczy na orbicie: " + trip.getDestination());
    }

    public double getPrice() {
        return price;
    }

    public void adjustPrice(double adjustment) {
        price += adjustment;
    }

    public String getSummary() {
        return "Typ: NAUKOWIEC, Grant: " + grantApproved + ", Koszt: " + getPrice();
    }
}