import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ThreadTimer extends Thread {
    private ModelVector clients;
    private final DatagramSocket socket;
    private final SimpleDateFormat formattedTime;


    public ThreadTimer(ModelVector clients, DatagramSocket socket) {
        this.socket = socket;
        this.clients = clients;
        formattedTime = new SimpleDateFormat("HH:mm:ss");
    }

    public void addClient(ClientModel client) {
        this.clients.add(client);
    }

    public ModelVector getClients() {
        return this.clients;
    }

    public void setClients(ModelVector clients) {
        this.clients = clients;
    }

    public void run() {
        try {
            while (true) {
                synchronized (clients) {
                    Iterator<ClientModel> iterator = clients.iterator();
                    while (iterator.hasNext()) {
                        ClientModel client = iterator.next();
                        switch (client.getMessage()) {
                            case 1:
                                sendTime(client);
                                break;
                            case 2:
                                break;
                            case 3:
                                iterator.remove();
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendTime(ClientModel client) {
        try {
            byte[] sendData = formattedTime.format(new Date()).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getAddress(), client.getPort());
            socket.send(sendPacket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
