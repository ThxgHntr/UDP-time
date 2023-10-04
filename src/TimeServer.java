import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TimeServer {

    public static final int SERVER_PORT = 7000;

    public static void main(String[] args) throws Exception {
        ModelVector clients = new ModelVector();
        DatagramSocket socket = new DatagramSocket(SERVER_PORT);
        ThreadTimer timer = new ThreadTimer(clients, socket);
        timer.start();
        System.out.println("running");
        while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            int message = Integer.parseInt(new String(receivePacket.getData(), 0, receivePacket.getLength()));
            ClientModel client = new ClientModel(receivePacket.getAddress(), receivePacket.getPort(), message);

            if (!timer.getClients().contains(client)) {
                timer.addClient(client);
            } else {
                clients = timer.getClients();
                clients.set(clients.indexOf(client), client);
                timer.setClients(clients);
            }
            System.out.println(clients);
        }
    }
}