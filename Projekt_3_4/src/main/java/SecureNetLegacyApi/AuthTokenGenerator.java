package SecureNetLegacyApi;

public class AuthTokenGenerator {
    public SessionToken generateToken(String hashedCredentials) {
        System.out.println("[Legacy API] Weryfikacja poświadczeń...");
        if (hashedCredentials == null || hashedCredentials.isEmpty()) {
            throw new IllegalArgumentException("[Legacy API Error] Pusty hash poświadczeń!");
        }
        return new SessionToken("TOKEN-" + System.currentTimeMillis());
    }
}
