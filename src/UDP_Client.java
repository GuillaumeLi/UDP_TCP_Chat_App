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


    public static void main (String[] args) throws IOException {
        UDP_Client udp_client = new UDP_Client();
        udp_client.launch();
        /** To run the UDP client under windows, we need to compile this class first then copy the path in the terminal
         * and paste it to the Windows terminal. We can also do it in the other way **/
    }

    public UDP_Client(int port, String serverName) throws UnknownHostException, SocketException {
        this.servAddress = InetAddress.getByName(serverName);
        this.port = port;
        udpSocket = new DatagramSocket(DEFAULT_CLIENT_PORT);
    }

    public UDP_Client() throws SocketException, UnknownHostException {
        this(DEFAULT_SERVER_PORT, DEFAULT_SERVER_NAME);
    }

    public void launch() throws IOException {
        System.out.println(servAddress.toString());
        System.out.println("-- Running UDP Client --");
        String message;
        while(running) {
            message = console.readLine();
            DatagramPacket udp_packet = new DatagramPacket(message.getBytes(), message.getBytes().length, servAddress, port);
            sendPacket(udp_packet);
            displayMessageSent(message, udp_packet.getAddress().getHostName());
        }
    }

    private void sendPacket(DatagramPacket packet) throws IOException {
        udpSocket.send(packet);
    }

    public void displayMessageSent(String msg, String server){
        System.out.println("Message send to " + server + " : " + msg);
    }
}
