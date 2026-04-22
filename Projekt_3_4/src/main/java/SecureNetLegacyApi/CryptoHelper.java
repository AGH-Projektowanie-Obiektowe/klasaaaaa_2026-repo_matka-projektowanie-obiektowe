package SecureNetLegacyApi;

// Plik: CryptoHelper.java
public class CryptoHelper {
    public static String hash(String username, String pin) {
        // Symulacja skomplikowanego algorytmu z 2005 roku
        return "MD5_LEGACY_" + username.toUpperCase() + "_" + pin;
    }
}