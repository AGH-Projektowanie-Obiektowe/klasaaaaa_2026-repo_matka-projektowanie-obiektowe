package SecureNetLegacyApi;

public class LegacyAlarmFacade {
    private final String IP = "192.168.1.100";
    private final int PORT = 9090;

    public void armAlarm() {
        // Dane binarne dla operacji uzbrojenia: [0x01, 0xFF]
        byte[] payload = new PacketBuilder()
                .appendByte((byte) 0x01)
                .appendByte((byte) 0xFF)
                .getCompiledPacket();

        executeLegacyCommand(payload);
    }

    public boolean isSmokeDetected() {
        // Dane binarne dla sprawdzenia czujnika: [0x02, 0x00]
        byte[] payload = new PacketBuilder()
                .appendByte((byte) 0x02)
                .appendByte((byte) 0x00)
                .getCompiledPacket();

        // W tej uproszczonej wersji zakładamy, że wykonanie komendy bez błędu
        // oznacza brak wykrycia dymu (zgodnie z logiką legacy api).
        executeLegacyCommand(payload);
        return false;
    }

    /**
     * Prywatna metoda pomocnicza realizująca 5-etapowy proces wymagany przez LegacySecureNet.
     */
    private void executeLegacyCommand(byte[] payload) {
        ConnectionNode connection = null;
        SessionToken token = null;

        try {
            // Etap 1: Inicjalizacja połączenia
            connection = new ConnectionNode(IP, PORT);
            connection.handshake();

            // Etap 2: Autoryzacja
            String credentialsHash = CryptoHelper.hash("admin", "1234");
            AuthTokenGenerator authGenerator = new AuthTokenGenerator();
            token = authGenerator.generateToken(credentialsHash);

            // Etap 3: Konfiguracja kolejki zdarzeń
            EventBusController controller = new EventBusController(connection);
            controller.registerSession(token);
            controller.subscribeToChannel(4); // Kanał alarmowy

            // Etap 4: Budowanie i wysłanie akcji
            LegacyCommand command = new LegacyCommand(payload);
            controller.dispatch(command);

        } finally {
            // Etap 5: Obowiązkowe sprzątanie zasobów
            if (token != null) {
                token.invalidateToken();
            }
            if (connection != null && connection.isConnected()) {
                connection.closeConnection();
            }
        }
    }
}