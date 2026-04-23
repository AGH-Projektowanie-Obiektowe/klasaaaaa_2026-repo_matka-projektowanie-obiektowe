package secureNetLegacyApi;

public class EventBusController {
    private SessionToken activeSession;
    private int currentChannel = -1;
    private ConnectionNode connection;

    public EventBusController(ConnectionNode connection) {
        this.connection = connection;
    }

    public void registerSession(SessionToken session) {
        this.activeSession = session;
    }

    public void subscribeToChannel(int channelId) {
        this.currentChannel = channelId;
        System.out.println("[Legacy API] Subskrypcja kanału nasłuchowego nr: " + channelId);
    }

    public void dispatch(LegacyCommand command) {
        if (connection == null || !connection.isConnected()) {
            throw new IllegalStateException("[Legacy API Error] Brak aktywnego połączenia sieciowego!");
        }
        if (activeSession == null || !activeSession.isValid()) {
            throw new SecurityException("[Legacy API Error] Brak autoryzacji! Wyciek pamięci lub nieważny token.");
        }
        if (currentChannel != 4) {
            throw new IllegalArgumentException("[Legacy API Error] Zły kanał! Komendy alarmowe to kanał 4.");
        }

        // Symulacja wykonania komendy
        byte[] payload = command.getRawData();
        if (payload.length == 2 && payload[0] == 0x01 && payload[1] == (byte) 0xFF) {
            System.out.println("[Legacy API] SUKCES: Alarm został uzbrojony.");
        } else if (payload.length == 2 && payload[0] == 0x02 && payload[1] == 0x00) {
            System.out.println("[Legacy API] SUKCES: Odczytano stan czujnika dymu (Brak dymu).");
        } else {
            System.out.println("[Legacy API] BŁĄD: Nierozpoznany pakiet bajtów.");
        }
    }
}