package SecureNetLegacyApi;

// Plik: ConnectionNode.java
public class ConnectionNode {
    private String ipAddress;
    private int port;
    private boolean isConnected = false;

    public ConnectionNode(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void handshake() {
        System.out.println("[Legacy API] Nawiązywanie połączenia z " + ipAddress + ":" + port + "...");
        this.isConnected = true;
    }

    public void closeConnection() {
        if (!isConnected) {
            throw new IllegalStateException("[Legacy API Error] Nie można zamknąć nieotwartego połączenia!");
        }
        System.out.println("[Legacy API] Połączenie zamknięte. Porty zwolnione.");
        this.isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }
}