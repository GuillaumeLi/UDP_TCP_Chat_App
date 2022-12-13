import java.io.Console;
import java.io.IOException;
import java.net.*;

public class UDP_Client {

    private static int DEFAULT_SERVER_PORT = 8000;
    private static int DEFAULT_CLIENT_PORT = 8001;
    private static String DEFAULT_SERVER_NAME = "localhost";

    private int port;
    private InetAddress servAddress;
    private DatagramSocket udpSocket;
    private Console console = System.console();
    private Boolean running = true;
    private String message;

    /**
     * Classe's main method. To run the UDP client under Windows, we need to compile this class first then copy the
     * path in the IDE terminal and paste it to the Windows terminal.
     * We can't run this class on IntelliJ because there is no message in the console for "console.readline()
     * in the launch method.
     * @param args arguments for the class such as the port number and IP address
     * @throws IOException
     */
    public static void main (String[] args) throws IOException {
        UDP_Client udp_client = new UDP_Client();
        udp_client.launch();
    }

    /**
     * Constructor used to define the port number and giving the server name
     * @param port number of the server port used for socket
     * @param serverName used to get the IP address of the server
     * @throws UnknownHostException
     * @throws SocketException
     */
    public UDP_Client(int port, String serverName) throws UnknownHostException, SocketException {
        this.servAddress = InetAddress.getByName(serverName);
        this.port = port;
        udpSocket = new DatagramSocket(DEFAULT_CLIENT_PORT);
    }

    /**
     * Default constructor using the default port number and name of the server
     * @throws SocketException socket exception
     * @throws UnknownHostException exception when getting the address of the server
     */
    public UDP_Client() throws SocketException, UnknownHostException {
        this(DEFAULT_SERVER_PORT, DEFAULT_SERVER_NAME);
    }

    /**
     * Launch the UDP client
     * @throws IOException exception for socket and datagram packets
     */
    public void launch() throws IOException {
        System.out.println("-- Running UDP Client --");
        while(running) {
            message = console.readLine();
            DatagramPacket udp_packet = new DatagramPacket(message.getBytes(), message.getBytes().length, servAddress, port);
            sendPacket(udp_packet);
            displayMessageSent(message, udp_packet.getAddress().getHostName());
        }
    }

    /**
     * Used to send the packet over the socket
     * @param packet packet to be sent in UDP protocol
     * @throws IOException
     */
    private void sendPacket(DatagramPacket packet) throws IOException {
        udpSocket.send(packet);
    }

    /**
     * Display the message sent in the terminal
     * @param msg message sent
     * @param server server's address
     */
    public void displayMessageSent(String msg, String server){
        System.out.println("Message send to " + server + " : " + msg);
    }
}
