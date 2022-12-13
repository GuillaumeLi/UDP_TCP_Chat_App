import javax.swing.text.DefaultTextUI;
import java.io.IOException;
import java.net.*;

public class UDP_Server {

    private static int DEFAULT_PORT = 8000;

    private int port;
    private DatagramSocket udpSocket;
    private boolean running;

    /**
     * Classe's main method. On Windows if you want to test the server alone, write "ncat -u localhost 8000" in the
     * Windows terminal in order to connect as a client. -u stands for UDP protocol.
     * @param args arguments for the class such as the port number
     * @throws IOException several exceptions for Socket or packets.
     */
    public static void main (String[] args) throws IOException {
        UDP_Server udp_server = new UDP_Server();
        udp_server.launch();
    }

    /**
     * Default constructor using the default port number
     * @throws SocketException
     */
    public UDP_Server() throws SocketException {
        this(DEFAULT_PORT);
    }

    /**
     * Constructor used to specify the port number
     * @param port number of the port used for sockets.
     * @throws SocketException
     */
    public UDP_Server(int port) throws SocketException {
        this.port = port;
        udpSocket = new DatagramSocket(this.port);
    }

    /**
     * Launch the UDP server
     * @throws IOException exception for socket and datagram packets
     */
    public void launch() throws IOException {
        System.out.println("-- Running UDP Server at " + InetAddress.getLocalHost() + "--");
        String msg;

        running = true;

        while (running) {

            byte[] buf = new byte[256];
            DatagramPacket client_packet = new DatagramPacket(buf, buf.length);

            // blocks until a packet is received
            udpSocket.receive(client_packet);
            msg = new String(client_packet.getData()).trim();

            displayMessage(msg,client_packet.getAddress().getHostAddress());
        }
    }

    /**
     * Display the message received in the terminal
     * @param msg message received by the client
     * @param host message's host IP address
     */
    public void displayMessage(String msg,String host){
            System.out.println("Message from " + host + " : " + msg);
        }

}
