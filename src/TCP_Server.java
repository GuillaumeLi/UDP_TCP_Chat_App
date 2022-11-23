import java.io.*;
import java.net.*;

public class TCP_Server {

    private static int DEFAULT_PORT = 8010;

    private int port;
    private ServerSocket tcpSocket;
    private Socket socket;
    private InputStream input;
    private BufferedReader reader;
    private OutputStream answer;
    private PrintWriter writer;
    private boolean running = true;

    public static void main (String[] args) throws IOException {
        TCP_Server tcp_server = new TCP_Server();
        tcp_server.launch();
        /** With the command "ncat -t localhost 8010" in a other terminal, we can connect to the server as client.**/
        /** -t stands for TCP **/
    }

    // Default constructor
    public TCP_Server() throws IOException {
        this(DEFAULT_PORT);
    }

    public TCP_Server(int port) throws IOException {
        this.port = port;
        tcpSocket = new ServerSocket(this.port);
    }

    public void launch () throws IOException {
        System.out.println("-- Running TCP Server at " + InetAddress.getLocalHost() + "--");
        while (running) {
            socket = tcpSocket.accept();
            readMessage();
            serverResponse();
            socket.close();
        }
    }

    public void displayMessage(String msg, InetAddress client){
        System.out.println("Message received from " + client + " : " + msg);
    }

    public void readMessage() throws IOException {
        input = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(input));
        String message = reader.readLine();
        displayMessage(message, socket.getInetAddress());
    }

    public void serverResponse() throws IOException {
        answer = socket.getOutputStream();
        writer = new PrintWriter(answer,true);
        writer.println("Message received by the server");
    }

}
