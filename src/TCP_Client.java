import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCP_Client {

    private static int DEFAULT_SERVER_PORT = 8010;
    private static String DEFAULT_SERVER_NAME = "localhost";

    private int server_port;
    private InetAddress servAddress;
    private Socket clientSocket;
    private BufferedReader clientInput;
    private DataOutputStream messageToServer;
    private String message;
    private boolean running = true;

    /**
     * Classe's main method. To run the TCP client under Windows, we need to compile this class first then copy the
     * path in the IDE terminal and paste it to the Windows terminal.
     * @param args arguments for the class such as the port number and server name
     * @throws IOException exception for socket
     */
    public static void main (String[] args) throws IOException {
        TCP_Client tcp_client = new TCP_Client();
        tcp_client.launch();
    }

    /**
     * Constructor used to specify the port number and the server name
     * @param port number of the server port used for socket
     * @param server_name used to get the IP address of the server
     * @throws IOException exception for socket
     */
    public TCP_Client(int port, String server_name) throws IOException {
        this.servAddress = InetAddress.getByName(server_name);
        this.server_port = port;
        clientSocket = new Socket(servAddress, server_port);
    }

    /**
     * Default constructor using the default server port and default server name
     * @throws IOException exception for socket
     */
    public TCP_Client() throws IOException {
        this(DEFAULT_SERVER_PORT, DEFAULT_SERVER_NAME);
    }

    /**
     * Launch the TCP Client
     * @throws IOException exception for socket
     */
    public void launch() throws IOException {
        System.out.println("-- Running TCP Client --");
        while (running) {
            readAndSendMessage();
            displayMessageSent(message, servAddress.getHostName());
        }
    }

    /**
     * Read the message written in the terminal by the user and send it over the socket to the server using TCP protocol
     * @throws IOException
     */
    public void readAndSendMessage() throws IOException {
        clientInput = new BufferedReader(new InputStreamReader(System.in));
        messageToServer = new DataOutputStream(clientSocket.getOutputStream());
        message = clientInput.readLine();
        messageToServer.writeUTF(message);
        messageToServer.flush();

    }

    /**
     * Display the message sent to the server in the terminal
     * @param msg message sent to the server
     * @param server server IP address
     */
    public void displayMessageSent(String msg, String server){
        System.out.println("Message send to " + server + " : " + msg);
    }

}
