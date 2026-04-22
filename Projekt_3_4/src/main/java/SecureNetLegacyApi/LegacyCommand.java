package SecureNetLegacyApi;

// Plik: LegacyCommand.java
public class LegacyCommand {
    private byte[] rawData;

    public LegacyCommand(byte[] rawData) {
        this.rawData = rawData;
    }

    public byte[] getRawData() {
        return rawData;
    }
}