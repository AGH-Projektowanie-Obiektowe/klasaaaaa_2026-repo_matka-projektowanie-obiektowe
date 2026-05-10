package BigBoyz;

import LilKlaski.User;

import java.time.LocalDate;
import java.time.Period;

public class VipLoungeAccessValidator {

    // Metoda z wysokim CC, korzystająca z wielu pól, zagnieżdżeń i ukrytej zależności czasowej
    public boolean canAccessVipLounge(User user) {
        if (user == null || user.getProfile() == null || user.getRegistrationDate() == null) {
            return false;
        }

        // Ukryte CC w klasie Period i problem niestabilnego testu przez LocalDate.now()
        int accountAgeYears = Period.between(user.getRegistrationDate(), LocalDate.now()).getYears();

        if (accountAgeYears >= 1 && user.getAge() >= 18) {
            return user.getProfile().getLoyaltyPoints() >= 1000;
        }

        return false;
    }
}