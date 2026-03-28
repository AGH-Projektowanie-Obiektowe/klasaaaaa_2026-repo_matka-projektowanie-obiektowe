package reservations;

import trip.SpaceTrip;

public class ReservationPlanner {

    public ColonistSpaceReservation planColonistTrip(SpaceTripType type, SpaceTrip trip) {
        /*
        * Tu tworzymy wycieczke
        * subsidy to ogólnie jakiś kwit który odejmujemy w środku klasy od ceny
        * Dodatkowe wymagania:
        * - Jeśli celem jest Mars, dodajemy 1000 do subsydium
        * - Jeśli ilość dodatkowych zbiorników tlenu jest większa niż 5, odejmujemy 2000 od subsydium
        * */
        var baseSubsidy = 2000.00;
        ColonistSpaceReservation reservation = new ColonistSpaceReservation(trip, baseSubsidy);

        /*
         * Adjustment do ceny zależy tylko od tego kiedy dokonaliśmy rezerwacji
         * Jak nasza wycieczka odbywa się mniej niż 30 dni od moment rezerwacji (stworzenia jej) - cena rośnie o 70% (nie lubimy tu last minute)
         * Jak nasza wycieczka odbywa się między 30 a 90 dni od moment rezerwacji - cena zostaje taka sama
         * Jak nasza wycieczka odbywa się więcej niż 90 dni od moment rezerwacji - cena spada o 30% (lubimy planowanie z wyprzedzeniem)
         * */
        double adjustment = reservation.getPrice() * 0.7;
        reservation.adjustPrice(adjustment);

        return reservation;
    }

    public ScienceSpaceReservation planScienceTrip(SpaceTripType type, SpaceTrip trip) {

        /*
         * Tu tworzymy wycieczke
         * Wycieczka typu science może dostać grant lub nie
         * Dodatkowe wymagania:
         * Grant jest przyznawany dla wycieczek nie wysokiego ryzyka trwających dłużej niż 30 dni
         * Wycieczka jest wysokiego ryzyka gdy insuranceLimit jest większy niż 1 milion
         * Dodatkowo jak destynacja wycieczki to "DeepSpace" to checmy robić loga do systemu (tutaj po prostu jakiś print)
         * */
        var baseSubsidy = 2000.00;
        ScienceSpaceReservation reservation = new ScienceSpaceReservation(trip, false);

        /*
        * Adjustment do ceny zależy tylko od tego kiedy dokonaliśmy rezerwacji
        * Jak nasza wycieczka odbywa się mniej niż 30 dni od moment rezerwacji (stworzenia jej) - cena rośnie o 70% (nie lubimy tu last minute)
        * Jak nasza wycieczka odbywa się między 30 a 90 dni od moment rezerwacji - cena zostaje taka sama
        * Jak nasza wycieczka odbywa się więcej niż 90 dni od moment rezerwacji - cena spada o 30% (lubimy planowanie z wyprzedzeniem)
        * */
        double adjustment = reservation.getPrice() * 0.7;
        reservation.adjustPrice(adjustment);

        return reservation;
    }
}
