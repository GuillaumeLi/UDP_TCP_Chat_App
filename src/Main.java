import java.io.IOException;
import java.net.SocketException;

public class Main {
    public static void main (String[] args) throws IOException {
        UDP_Server udp_server = new UDP_Server(Integer.parseInt(args[0]));
        udp_server.launch();
        /** With the command "nc -u localhost 8000" in a other terminal, we can connect to the server as client.**/
    }
}
