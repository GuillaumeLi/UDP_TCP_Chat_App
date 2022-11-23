import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;

public class TCP_Server {

    private static int DEFAULT_PORT = 8010;

    private int port;
    private ServerSocket tcpSocket;
    private boolean running;


    // Default constructor
    public TCP_Server() throws IOException {
        this(DEFAULT_PORT);
    }

    public TCP_Server(int port) throws IOException {
        this.port = port;
        tcpSocket = new ServerSocket(this.port);
    }

    public void launch () {
        System.out.println("-- Running Server at " + InetAddress.getLocalHost() + "--");
    }


}
