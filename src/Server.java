

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends User {
    private Socket clientSocket;
    private ServerSocket serverSocket;


    public Server() {
        super();
    }

//    InetAddress addr = new InetAddress(192.168.0.1);
//   serverSocket = new ServerSocket(200,0,addr);

    private void createConnection() {
        try {
            InetAddress locIP = InetAddress.getByName("10.62.68.122");
            serverSocket = new ServerSocket(4444, 0, locIP);


            //      serverSocket = new ServerSocket(4444, 4444, InetAddress.getByName("192.168.0.101"));
        } catch (IOException e) {
            System.err.println("Could not listen on port: 9999 ." + e);
            System.exit(1);
        }
    }

    private void closeConnection() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public void connect() {
        createConnection();

        try {
            clientSocket = serverSocket.accept();

            System.out.println("Client connected! "
                    + "IP: "
                    + clientSocket.getInetAddress()
                    + ", port: "
                    + clientSocket.getPort());


        } catch (IOException e) {
            System.err.println("Accept failed. " + e);
            System.exit(1);
        }
    }


    @Override
    public void disconnect() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.err.println(e);
        }

        closeConnection();
    }


    @Override
    public Socket getSocket() {
        return clientSocket;
    }

    @Override
    public String toString() {
        return new String("Server");
    }
}