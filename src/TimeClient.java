import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TimeClient extends JFrame {
    private JButton runButton;
    private JButton stopButton;
    private JLabel lblTime;
    private JPanel clientPanel;
    private final DatagramSocket socket;
    private final InetAddress localhost;

    public TimeClient() throws IOException {
        this.socket = new DatagramSocket();
        this.localhost = InetAddress.getLocalHost();
        setTitle("Timer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sendMessage(1);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    sendMessage(3);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
        runButton.addActionListener(e ->

        {
            try {
                sendMessage(1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        stopButton.addActionListener(e ->

        {
            try {
                sendMessage(2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        getContentPane().

                add(clientPanel);

        setVisible(true);

        Thread receiveThread = new Thread(() -> {
            while (true) {
                try {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket);
                    String time = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    lblTime.setText(time);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        receiveThread.start();
    }

    public void sendMessage(Integer message) throws IOException {
        byte[] sendData = message.toString().getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, localhost, TimeServer.SERVER_PORT);
        socket.send(sendPacket);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TimeClient();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
