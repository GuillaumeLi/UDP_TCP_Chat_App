import javax.swing.text.DefaultTextUI;
import java.io.IOException;
import java.net.*;

public class UDP_Server {

    private static int DEFAULT_PORT = 8000;

    private int port;
    private DatagramSocket udpSocket;
    private boolean running;

    public static void main (String[] args) throws IOException {
        UDP_Server udp_server = new UDP_Server();
        udp_server.launch();
        /** With the command "ncat -u localhost 8000" in a other terminal, we can connect to the server as client.**/
        /** -u stands for UDP **/
    }

    // Default constructor
    public UDP_Server() throws SocketException {
        this(DEFAULT_PORT);
    }

    public UDP_Server(int port) throws SocketException {
        this.port = port;
        udpSocket = new DatagramSocket(this.port);
    }

    public void launch() throws IOException {
        System.out.println("-- Running UDP Server at " + InetAddress.getLocalHost() + "--");
        String msg;

        running = true;

        while (running) {

            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // blocks until a packet is received
            udpSocket.receive(packet);
            msg = new String(packet.getData()).trim();

            displayMessage(msg,packet.getAddress().getHostAddress());
        }


    }
    public void displayMessage(String msg,String host){
            System.out.println("Message from " + host + " : " + msg);
        }

    /**
     * @param running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * @return
     */
    public boolean isRunning() {
        return running;
    }

    @Override
    public String toString() {
        return "The server is running : " + running;
    }
}
