import java.io.IOException;
import java.net.*;

public class UDP_Server {

    private int port;
    private DatagramSocket udpSocket;

    private boolean running;

    // Default constructor
    public UDP_Server() throws SocketException {
        this(8000);
    }

    public UDP_Server(int port) throws SocketException {
        this.port = port;
        udpSocket = new DatagramSocket(this.port);
    }

    public void launch() throws IOException {
        System.out.println("-- Running Server at " + InetAddress.getLocalHost() + "--");
        String msg;

        running = true;
        toString();

        while (running) {

            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            // blocks until a packet is received
            udpSocket.receive(packet);
            msg = new String(packet.getData()).trim();

            System.out.println(
                    "Message from " + packet.getAddress().getHostAddress() + ": " + msg);
        }

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public String toString() {
        return "The server is running : " + running;
    }
}
