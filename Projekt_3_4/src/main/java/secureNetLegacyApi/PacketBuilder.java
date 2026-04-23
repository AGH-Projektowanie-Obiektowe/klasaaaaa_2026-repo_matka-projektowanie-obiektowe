package secureNetLegacyApi;

import java.util.ArrayList;
import java.util.List;

public class PacketBuilder {
    private List<Byte> buffer = new ArrayList<>();

    public PacketBuilder appendByte(byte b) {
        buffer.add(b);
        return this;
    }

    public byte[] getCompiledPacket() {
        byte[] packet = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            packet[i] = buffer.get(i);
        }
        return packet;
    }
}