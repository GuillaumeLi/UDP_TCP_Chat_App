import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        UDP_Server udp_server = new UDP_Server(Integer.parseInt(args[0]));
        //UDP_Client udp_client = new UDP_Client(Integer.parseInt(args[1]), args[2]);
        udp_server.launch();
        /** With the command "nc -u localhost 8000" in a other terminal, we can connect to the server as client.**/
    }
}
