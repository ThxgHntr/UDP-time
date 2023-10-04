import java.net.InetAddress;

public class ClientModel {

    private final InetAddress address;
    private final int port;
    private final int message;

    public int getMessage() {
        return message;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    ClientModel(InetAddress address, int port, int message) {
        this.address = address;
        this.port = port;
        this.message = message;
    }

}
