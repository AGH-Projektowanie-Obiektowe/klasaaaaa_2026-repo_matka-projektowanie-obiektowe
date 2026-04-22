package SecureNetLegacyApi;

// Plik: SessionToken.java
public class SessionToken {
    private String tokenValue;
    private boolean isValid;

    public SessionToken(String tokenValue) {
        this.tokenValue = tokenValue;
        this.isValid = true;
    }

    public void invalidateToken() {
        this.isValid = false;
        System.out.println("[Legacy API] Token sesyjny unieważniony.");
    }

    public boolean isValid() {
        return isValid;
    }
}