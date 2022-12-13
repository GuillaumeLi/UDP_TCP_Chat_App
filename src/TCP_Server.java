import java.io.*;
import java.net.*;

public class TCP_Server {

    private static int DEFAULT_PORT = 8010;

    private int port;
    private ServerSocket tcpSocket;
    private Socket server_socket;
    private InputStream input;
    private DataInputStream reader;
    private OutputStream answer;
    private PrintWriter writer;
    private boolean running = true;

    /**
     * Classe's main method. With the command "ncat -t localhost 8010" in an other terminal, we can connect to
     * the server as client. -t stands for TCP.
     * @param args arguments for the class such as the port number and IP address
     * @throws IOException exception for socket, data sent and received
     */
    public static void main (String[] args) throws IOException {
        TCP_Server tcp_server = new TCP_Server();
        tcp_server.launch();
        /** With the command "ncat -t localhost 8010" in a other terminal, we can connect to the server as client.**/
        /** -t stands for TCP **/
    }

    /**
     * Default constructor using the default port number
     * @throws IOException
     */
    public TCP_Server() throws IOException {
        this(DEFAULT_PORT);
    }

    /**
     * Constructor used to specify the port number
     * @param port number used for the socket
     * @throws IOException exception when creating the socket
     */
    public TCP_Server(int port) throws IOException {
        this.port = port;
        tcpSocket = new ServerSocket(this.port);
    }

    /**
     * Launch the TCP server
     * @throws IOException exception for the socket
     */
    public void launch () throws IOException {
        System.out.println("-- Running TCP Server at " + InetAddress.getLocalHost() + "--");
        server_socket = tcpSocket.accept();
        while (running) {
            readMessage();
            serverResponse();
        }
    }

    /**
     * Read the message received by the client through TCP protocol
     * @throws IOException exception for the socket
     */
    public void readMessage() throws IOException {
        input = server_socket.getInputStream();
        reader = new DataInputStream(input);
        String message = reader.readUTF();
        displayMessage(message, server_socket.getInetAddress());
    }

    /**
     * Send a message to the client to acknowledge the message reception
     * @throws IOException exception for the socket
     */
    public void serverResponse() throws IOException {
        answer = server_socket.getOutputStream();
        writer = new PrintWriter(answer,true);
        writer.println("Message received by the server");
    }

    /**
     * Display the message received in the terminal
     * @param msg message received by the client
     * @param client client IP address
     */
    public void displayMessage(String msg, InetAddress client){
        System.out.println("Message received from " + client + " : " + msg);
    }

}
